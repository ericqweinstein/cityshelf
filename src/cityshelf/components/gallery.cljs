(ns ^{:doc "Gallery component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.gallery
  (:require [cityshelf.components.search-result :refer [search-result]]))

(defn gallery
  "Creates a gallery of search results."
  [books]
  [:div.align-center
   (for [book books]
     ^{:key book} [search-result book])])
