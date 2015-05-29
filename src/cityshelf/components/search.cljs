(ns ^{:doc "Search bar component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.search
  (:require [ajax.core :refer [GET]]
            [cityshelf.components.input :refer [input]]
            [cityshelf.components.dropdown :refer [dropdown]]
            [cityshelf.components.search-button :refer [search-button]]
            [cityshelf.session :as session]
            [clojure.string :as s]
            [goog.events :as events]
            [secretary.core :as secretary]))

(declare query default)

(def cities
  {:boston {:latitude 42.3601 :longitude -71.0589}
   :chicago {:latitude 41.8369 :longitude -87.6847}
   :minneapolis {:latitude 44.9778 :longitude -93.2650}
   :new-york {:latitude 40.759710 :longitude -73.974262}
   :portland {:latitude 45.5200 :longitude -122.6819}
   :seattle {:latitude 47.5487 :longitude -122.3331}})

(events/listen js/window "keyup"
  (fn [evt]
    "Triggers the API query if the user
    searches by hitting the return key."
    (let [key-pressed (.-keyCode evt)]
      (if (= key-pressed 13)
        (query)))))

(defn handler
  "Response handler for AJAX requests."
  [response]
  (session/put! :results response)
  (session/put! :searching false)
  (GET "http://api.cityshelf.com/stores/"
       {:params {:latitude (session/get! :latitude)
                 :longitude (session/get! :longitude)}
        :handler (fn [r] (session/put! :stores r))})
  (secretary/dispatch! "/books"))

(defn query
  "Makes a query to the API."
  []
  (session/put! :searching true)
  (let [city (session/get! :city)]
    (session/put! :latitude (:latitude (get cities city)))
    (session/put! :longitude (:longitude (get cities city)))
    (GET "http://api.cityshelf.com/books/"
          {:params {:query (session/get! :query)
                    :latitude (session/get! :latitude)
                    :longitude (session/get! :longitude)}
           :handler handler})))

(defn search
  "Sticky search bar."
  []
  [:div.search
   [:div.container
    [dropdown [(default (session/get! :city)) "Boston" "Chicago" "Minneapolis" "New York" "Portland" "Seattle"]]
    [input "text" (or (session/get! :query) "Title, author or ISBN")]
    [search-button query]]])

;; TODO: Move this into components/dropdown.cljs. (EQW 26 Aug 2015)
(defn- default
  "Sets default value for the dropdown."
  [value]
  (if (nil? value)
    "Select your city"
    (s/join
      (map s/capitalize
        (interpose " "
          (s/split (name value) "-"))))))
