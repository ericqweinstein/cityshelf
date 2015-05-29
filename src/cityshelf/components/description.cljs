(ns ^{:doc "Book description component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.description
  (:require [cityshelf.components.image :refer [image]]
            [cityshelf.session :as session]
            [reagent.core :as reagent]))

(defn description
  "Book description component."
  [book]
  (let [source (reagent/atom (get book "img"))]
    [:div.book-container
     [image {:src source :className "book-image"}]
     [:div.book-description
      [:h1.book-title (get book "title")]
      [:h2.book-author (get book "author")]
      [:p.copy {:id "book-copy"} (session/get! :description)]]]))
