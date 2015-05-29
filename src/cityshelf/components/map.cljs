(ns ^{:doc "Google map component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.map
  (:require [reagent.core :as reagent]
            [cityshelf.session :as session]))

(declare stores-with-inventory)

(defn map-component
  "Component for our Google map."
  []
  [:div#map-canvas])

(defn make-marker
  "Creates a pin on the Google map."
  [google-map store]
  (let [i (first store)  ; Index of the store in the vector
        s (second store) ; The store map itself
        _
        (js/google.maps.Marker. (clj->js {"position" (google.maps.LatLng. (get-in s ["map" "center" "latitude"])
                                                                          (get-in s ["map" "center" "longitude"]))
                                          "map" google-map
                                          "title" (get s "storeName")
                                          "icon"  (str "/img/pin-" i ".png")}))]))

(defn map-did-mount
  "Handler for when the map mounts in the DOM."
  [location stores]
  (let [map-canvas (.getElementById js/document "map-canvas")
        latitude (:latitude location)
        longitude (:longitude location)
        map-options (clj->js {"center" (google.maps.LatLng. latitude longitude)
                              "scrollwheel" false
                              "zoom" 11})
        google-map (js/google.maps.Map. map-canvas map-options)
        stores-with-indices (map-indexed (fn [i s] [i s]) stores)]
    (doseq [store stores-with-indices]
      (make-marker google-map store))))

(defn google-map-for
  "Map component for the given location."
  [location stores]
  (reagent/create-class {:reagent-render map-component
                         :component-did-mount #(map-did-mount location stores)
                         :component-did-update #(map-did-mount location (stores-with-inventory))}))

;; Temporarily duplicating this logic from src/cityshelf/views/book.cljs
;; until I resolve the underlying state management issue. (EQW 12 Sep 2015)
(defn- stores-with-inventory
  "Gets the set of stores that have the current book in stock."
  []
  (let [bk (session/get! :current-title)
        books (val bk)
        stores (session/get! :stores)
        books-with-stores (vec (map merge books stores))]
    (filter #(get % "available") books-with-stores)))
