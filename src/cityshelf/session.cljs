(ns ^{:doc "Manages client-side application state."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.session
  (:require [reagent.core :as reagent]))

(defonce state
  (reagent/atom {}))

(defn get!
  "Retrieves information from the application's state."
  [k & [default]]
  (get @state k default))

(defn put!
  "Updates a key in the application's state."
  [k v]
  (swap! state assoc k v))
