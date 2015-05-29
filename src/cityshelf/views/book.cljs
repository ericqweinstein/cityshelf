(ns ^{:doc "Client-side view for an individual book."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.views.book
  (:require [cityshelf.components.description :refer [description]]
            [cityshelf.components.map :refer [google-map-for]]
            [cityshelf.components.search :refer [search]]
            [cityshelf.components.reco :refer [reco]]
            [cityshelf.session :as session]))

(declare stores-with-inventory normalize-price)

(defn pluralize
  "Handles pluralization for book availability."
  [n]
  (if (= n 1)
    " bookstore"
    " bookstores"))

(defn good-news-everyone
  "Controls availability language."
  [n]
  (if (> n 0)
    (str "Good news! Your book is currently on shelves at "
         n
         (pluralize n)
         " near you. We recommend you call to reserve your book.")
    "Unfortunately, your book is not currently available in any of the stores we search."))

(defn index
  "The content for an individual book page."
  []
  (let [bk (session/get! :current-title)
        books (val bk)
        available (count (filter #(get % "available") books))
        location {:latitude (session/get! :latitude) :longitude (session/get! :longitude)}
        stores (stores-with-inventory)]
    [:div
     [search]
     [description (first books)]
     [:div#availability
       [:p.copy [:span (good-news-everyone available)]]
       [:ul
        (for [store stores]
          ^{:key (get store "id")} [:li
                         [:p.book-title (str (get store "storeName") (normalize-price (get store "price")))]
                         [:a.copy {:href (get store "phone")} (.substring (get store "phone") 7)]])]]
     [google-map-for location stores]
     [:div#recommendation
       [:h3 "Based on your search, you may want to find more great books locally:"]
       [reco (first (session/get! :results))]]]))

(defn- stores-with-inventory
  "Gets the set of stores that have the current book in stock."
  []
  (let [bk (session/get! :current-title)
        books (val bk)
        stores (session/get! :stores)
        books-with-stores (vec (map merge books stores))]
    (filter #(get % "available") books-with-stores)))

(defn- normalize-price
  "Normalizes price data for books."
  [price]
  (if (re-matches #"\d+\.\d{2}" price)
    (str " $" price)
    " (mail or call for price)"))
