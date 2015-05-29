(ns ^{:doc "Client-side CityShelf application."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"
      :figwheel-always true}
  cityshelf.core
  (:require [cityshelf.session :as session]
            [cityshelf.views.book :as book]
            [cityshelf.views.home :as home]
            [cityshelf.views.search :as search]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent.core :as reagent]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.history.Html5History
           goog.Uri))

(declare mount-root)

(enable-console-print!)
(println "If you're reading this, you should get in touch with us: dev@cityshelf.com")

(defroute "/"
  []
  (session/put! :current-view home/index)
  (mount-root))

(defroute "/books" {:as params}
  (session/put! :current-view search/index)
  (mount-root)
  (.scrollTo js/window 0 0)
  (.pushState js/history {} "" "/books"))

(defroute "/books/:city/:isbn"
  [city isbn]
  (session/put! :current-view book/index)
  (mount-root)
  (.scrollTo js/window 0 0)
  (.pushState js/history {} "" (str "/books/" city "/" isbn)))

(defn- current-view
  "Retrieves the current view."
  []
  (session/get! :current-view))

(defn- hook-browser-navigation!
  "Enables browser history in a SPA environment.
  Obviates hash-based (e.g. /#/books) routing."
  []
  (let [history (doto (Html5History.)
                  (events/listen
                    EventType/NAVIGATE
                    (fn [event]
                    (secretary/dispatch! (.-token event))))
                  (.setUseFragment false)
                  (.setPathPrefix "")
                  (.setEnabled true))]
    (events/listen js/document "click"
      (fn [e]
        (let [path (.getPath (.parse Uri (.-href (.-target e))))
              title (.-title (.-target e))]
          (when (secretary/locate-route path)
            (. history (setToken path title))))))))

(defn- mount-root
  "Mounts the CityShelf root component."
  []
  (reagent/render
    [(current-view)]
    (.getElementById js/document "app")))

(defn- init!
  "Starts the client-side application."
  []
  (hook-browser-navigation!)
  (mount-root))

(init!)
