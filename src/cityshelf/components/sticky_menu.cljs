(ns ^{:doc "Sticky menu component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.sticky-menu
  (:require [cityshelf.components.anchor :refer [anchor]]))

(defn sticky-menu
  "Sticky menu, because what site _doesn't_ have one? :)"
  []
  [:div#menu
   [:div.logo
    [:img {:src "img/cs_logo.svg"}]]
   [anchor "cs-search" "Search"]
   [anchor "cs-bookstores" "Bookstores"]
   [anchor "cs-team" "The Team"]
   [anchor "cs-news" "News"]
   [:a {:href "https://geo.itunes.apple.com/us/app/cityshelf/id1026472160?mt=8" :target "_blank"}
    [:img {:src "img/apple_store.svg"}]]])
