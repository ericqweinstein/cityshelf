(ns ^{:doc "Smooth-scrolling anchor tag component."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.components.anchor)

(declare scroll-to firefox?)

(defn anchor
  "Anchor tag component."
  [link-to content]
  [:a {:href (str "#" link-to)
       :onClick (fn [e]
                  (let [body (if (firefox?)
                               (.-documentElement js/document)
                               (.-body js/document))
                        ele (.getElementById js/document link-to)]
                    (.preventDefault e)
                    (scroll-to body (+ (.-offsetTop ele) 75) 500)))}
   content])

(defn- scroll-to
  "Scrolls to the indicated anchor tag."
  [element to duration]
  (when (> duration 0)
    (let [difference (- to (.-scrollTop element))
          per-tick (* (/ difference duration) 10)]
      (js/setTimeout (fn []
                       (set! (.-scrollTop element)
                             (+ (.-scrollTop element) per-tick))
                       (when (not= (.-scrollTop element) to)
                         (scroll-to element to (- duration 10))))
                     10))))

(defn- firefox?
  "Checks if the browser is Firefox, which sets scrollTop
  on the <html> element and not the <body> because it is
  a special snowflake."
  []
  (re-find #"Firefox" (aget js/navigator "userAgent")))
