(ns ^{:doc "Input text field."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.input
  (:require [cityshelf.session :as session]))

(defn input
  "Creates a text input field."
  [input-type placeholder]
  [:input.form-field {:field input-type
                      :placeholder placeholder
                      :on-change #(session/put! :query (-> % .-target .-value))}])
