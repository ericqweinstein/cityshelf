(ns ^{:doc "Site footer."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.footer)

(defn footer
  "Site footer."
  []
  [:div.footer
  [:p [:a {:href "mailto:info@cityshelf.com"} "info@cityshelf.com"]
   (str " / " (.getFullYear (js/Date.)) " CityShelf™")]])
