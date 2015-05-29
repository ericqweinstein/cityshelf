(ns ^{:doc "Search button."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.search-button
  (:require [cityshelf.session :as session]))

(declare switch-class)

(defn search-button
  "Creates a search button."
  [query]
  [:button
   {:on-click query
    :class "cs-search-btn activated"}
   [:i {:class (switch-class)}]])

(defn- switch-class
  "Triggers the spinner when searching."
  []
  (if (session/get! :searching)
    "fa fa-circle-o-notch fa-spin fa-lg"
    "fa fa-search fa-lg"))
