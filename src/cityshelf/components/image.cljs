(ns ^{:doc "Image component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.image
  (:require [clojure.string :as s]))

;; This component wraps <img> with logic that allows it to
;; default to our own dummy book image if the image fails
;; to load. This will eventually house other image magicks
;; (_e.g._ lazy loading). (EQW 20 Jul 2015)

(declare root-url)

(defn image
  "Image component."
  [options]
  (let [src (:src options)]
    [:img {:className (:className options)
           :src @src
           :onError #(reset! src (str (root-url) "/img/defaultbook_green.png"))}]))

(defn- root-url
  "Helper to get the root URL."
  []
  (let [url (.-href (.-location js/window))]
    (->>
      (s/split url "/")
      (take 3)
      (s/join "/"))))
