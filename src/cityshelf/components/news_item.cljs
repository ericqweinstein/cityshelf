(ns ^{:doc "News item component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.news-item)

(defn news-item
  "News item component."
  [item]
  (let [date (:date item)
        description (:description item)
        link (:link item)]
    [:div.news-item
     [:time date]
     [:p description]
     [:a {:href (:href link) :target "_blank"} (:text link)]]))
