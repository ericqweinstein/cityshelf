(ns ^{:doc "Dropdown menu."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.dropdown
  (:require [cityshelf.session :as session]
            [clojure.string :as s]))

(declare city-to-keyword)

(defn dropdown
  "Creates a dropdown menu."
  [items]
  [:select
   {:class "form-field"
    :id "dropdown"
    :on-change #(session/put! :city (city-to-keyword (-> % .-target .-value)))}
   (for [item items]
     ^{:key item} [:option item])])

(defn city-to-keyword
  "Helper that converts a city name in the
  dropdown to its key in the application state atom."
  [city]
  (->>
    (s/split city " ")
    (map s/lower-case)
    (interpose "-")
    (s/join)
    (keyword)))
