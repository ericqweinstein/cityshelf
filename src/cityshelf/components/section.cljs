(ns ^{:doc "Section component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.section)

(defn section
  "Creates a horizontal section for a view."
  [heading content]
  [:div.section
   [:h2 heading]
   content])
