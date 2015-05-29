(ns ^{:doc "Recommendation carousel component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.reco
  (:require [cityshelf.components.search-result :refer [search-result]]))

(defn reco
  "Creates a recommendation carousel."
  [books]
  (let [recos (take 4 books)]
    [:div.align-center
     (for [reco recos]
       ^{:key reco} [search-result reco])]))
