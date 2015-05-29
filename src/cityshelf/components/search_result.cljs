(ns ^{:doc "Search result component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.search-result
  (:require [ajax.core :refer [GET]]
            [cityshelf.components.image :refer [image]]
            [cityshelf.session :as session]
            [clojure.string :as string]
            [reagent.core :as reagent]
            [secretary.core :as secretary]))

(declare google-handler title-handler truncate)

(def google-books-api-key
  "AIzaSyBltvhtqC0k381yNfg3kJfi7GYjg5w3Etk")

(defn search-result
  "Creates a search result containing book metadata."
  [bk]
  (let [book (val bk)
        source (reagent/atom (str (get (first book) "img")))
        title (str (get (first book) "title"))
        author (str (get (first book) "author"))]
    (fn []
     [:div.search-result {:onClick #(title-handler bk)}
      [image {:src source :className "align-center"}]
      [:p.book-title (truncate title)]
      [:p.book-author (truncate author)]])))

(defn- google-handler
  "Handler for the AJAX request to the Google Books API."
  [r]
  (let [description (str (get (get (first (get r "items")) "volumeInfo") "description"))]
    (session/put! :description description)))

(defn- title-handler
  "Handles navigation to the title view."
  [bk]
  (let [city (name (session/get! :city))
        isbn (key bk)]
    (session/put! :current-title bk)
    (GET "https://www.googleapis.com/books/v1/volumes"
         {:params {:q (first (session/get! :current-title))
                   :key google-books-api-key}
          :handler google-handler})

    (secretary/dispatch! (str "/books/" city "/" isbn))))

(defn- truncate
  "Truncates long titles and author names."
  [text]
  (let [limit 18]
    (if (> (.-length text) limit)
      (str (subs text 0 limit) "...")
      text)))
