(ns ^{:doc "Client-side search results view."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.views.search
  (:require [cityshelf.components.gallery :refer [gallery]]
            [cityshelf.components.search :refer [search]]
            [cityshelf.session :as session]))

(defn index
  "The content for the search results page."
  []
  [:div
   [search]
   [:h1.subheading "WHICH OF THESE BOOKS ARE YOU LOOKING FOR?"]
   [gallery (first (session/get! :results))]])
