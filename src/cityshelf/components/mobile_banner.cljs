(ns ^{:doc "Mobile banner component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.mobile-banner
  (:require [reagent.core :as reagent]))

(declare dismiss-banner)

(.initializeTouchEvents js/React true)

(defn mobile-banner
  "Directs users to native apps where available."
  [state]
    [:div#mobile-banner {:style (@state :style) :className "full-bleed"}
      [:img {:src "img/cs-mobile.png"}]
      [:a.subheading {:href "https://geo.itunes.apple.com/us/app/cityshelf/id1026472160?mt=8"
                      :target "_blank"} "Download our app in the app store"]
      [:span {:aria-hidden "true"
              :onClick #(dismiss-banner state)
              :onTouchEnd #(dismiss-banner state)} "×"]])

(defn- dismiss-banner
  "Dismisses the mobile banner when clicked."
  [state]
  (swap! state assoc :style {:display "none"}))
