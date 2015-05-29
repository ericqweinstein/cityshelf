(ns ^{:doc "Team member bio component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.bio)

(defn bio
  "Team member bio component."
  [person]
  (let [image (:image person)
        twitter (:twitter person)
        fullname (:fullname person)
        title (:title person)]
    [:div.about-section
     [:figure
      [:img {:src image}]
      [:figcaption
       [:a {:href (str "https://twitter.com/" twitter) :target "_blank"} (str "@" twitter)]]]
     [:div.bio
      [:h3.title fullname]
      [:h3.title title ]]]))
