(ns ^{:doc "Client-side home view."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.views.home
  (:require [cityshelf.components.bio :refer [bio]]
            [cityshelf.components.dropdown :refer [dropdown]]
            [cityshelf.components.footer :refer [footer]]
            [cityshelf.components.input :refer [input]]
            [cityshelf.components.mobile-banner :refer [mobile-banner]]
            [cityshelf.components.news-item :refer [news-item]]
            [cityshelf.components.search :refer [search]]
            [cityshelf.components.search-button :refer [search-button]]
            [cityshelf.components.section :refer [section]]
            [cityshelf.components.sticky-menu :refer [sticky-menu]]
            [cityshelf.session :as session]
            [reagent.core :as reagent]
            [secretary.core :as secretary]))

(defn index
  "The content for the home page."
  []
  [:div
   (if (re-find #"iPhone" (aget js/navigator "userAgent"))
     [mobile-banner (reagent/atom {:style {:display "block"}})])
   [sticky-menu]
   [:h1.cta "Find what's in stock"
            [:br]
            "at your neighborhood bookstores."]
   [:a {:name "search" :id "cs-search"}]
   [:div.hero]
   [search]
   [:a {:name "bookstores" :id "cs-bookstores"}]
   [section "Cities with bookstores we now serve" [:div.bookstores
                                                 [:div
                                                  [:img {:src "img/nyc.png"}]
                                                  [:span.city-name "NYC"]]
                                                 [:div
                                                  [:img {:src "img/bos.png"}]
                                                  [:span.city-name "BOS"]]
                                                 [:div
                                                  [:img {:src "img/por.png"}]
                                                  [:span.city-name "POR"]]
                                                 [:div
                                                  [:img {:src "img/sea.png"}]
                                                  [:span.city-name "SEA"]]
                                                 [:div
                                                  [:img {:src "img/msp.png"}]
                                                  [:span.city-name "MSP"]]
                                                 [:div
                                                  [:img {:src "img/chi.png"}]
                                                  [:span.city-name "CHI"]]]]
   [:a {:name "team" :id "cs-team"}]
   [section "Meet the team that makes it all happen"
    [:div
     ;; TODO: Map bio over config data (vector of maps?) (EQW 26 Aug 2015)
     [bio {:image "img/Ben.png"
           :twitter "benpurkert"
           :fullname "Ben Purkert"
           :title "Founder"}]
     [bio {:image "img/Liz.png"
           :twitter "yolizoh"
           :fullname "Liz Oh"
           :title "Creative, CCO"}]
     [bio {:image "img/Eric.png"
           :twitter "ericqweinstein"
           :fullname "Eric Weinstein"
           :title "Technology, CTO"}]
     [bio {:image "img/Javier.png"
           :twitter "javierlopez288"
           :fullname "Javier Lopez"
           :title "Interaction, CIO"}]
     [bio {:image "img/Caitlin.png"
           :twitter "caitjenn7"
           :fullname "Caitlin Smyth"
           :title "Manager"}]]]
   [section "News and word of mouth" [:div
                                      [:div.news
                                       [:a {:name "news" :id "cs-news"} "News"]
                                       [:div
                                        [:p "Read what some nice folks are saying about us."]
                                        [news-item {:date "08.19.2015"
                                                    :description "Searching Indie Bookstore Shelves"
                                                    :link {:href "https://www.pw.org/content/searching_indie_bookstore_shelves" :text "P&W >"}}]
                                        [news-item {:date "01.23.2015"
                                                    :description "CityShelf Makes it Easier for People to Skip Amazon and Buy at Indie Book Stores"
                                                    :link {:href "http://www.fastcoexist.com/3040995/cityshelf-makes-it-easier-for-people-to-skip-amazon-and-buy-at-indie-book-stores" :text "FASTCO >"}}]
                                        [news-item {:date "12.26.2014"
                                                    :description "New App Links Indie Bookstores to Buyers"
                                                    :link {:href "http://nypost.com/2014/12/25/new-app-links-indie-bookstores-to-buyers/" :text "NYPOST >"}}]]]
                                      [:div.updates
                                       [:a {:name "updates"} "Sign Up"]
                                       [:div.subscription
                                        [:form {:action "/subscribe" :method "post" :id "embedded-form" :name "embedded-form"}
                                         [:input#email {:type "email" :name "email" :placeholder "Enter your email for updates"}]
                                         [:button.submit-button {:type "submit" :value "Subscribe" :name "subscribe" :id "subscribe"} "Subscribe"]]
                                        [:a {:name "connect"} "Connect"]
                                        [:div.social-media
                                         [:a {:href "https://twitter.com/CityShelf" :target "_blank"} [:div#twitter]]
                                         [:a {:href "https://www.facebook.com/CityShelf" :target "_blank"} [:div#facebook]]
                                         [:a {:href "https://instagram.com/CityShelf" :target "_blank"} [:div#instagram]]]]]]]
   [footer]])
