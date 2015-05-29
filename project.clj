(defproject cityshelf "1.0.0"
  :description "CityShelf: A search aggregator for independent bookstores."
  :url "http://www.cityshelf.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cheshire "5.6.1"]
                 [cljs-ajax "0.5.5"]
                 [clj-http "3.1.0"]
                 [cljsjs/react "15.1.0-0"]
                 [compojure "1.5.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.9.36"]
                 [reagent "0.5.1"]
                 [ring/ring-core "1.5.0"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [secretary "1.2.3"]]
  :main ^:skip-aot cityshelf.web
  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.3.3"]
            [lein-haml-sass "0.2.7-SNAPSHOT"]]
  :profiles {:dev {
                   :plugins [[codox "0.8.10"]
                             [lein-ancient "0.5.5"]
                             [lein-bikeshed "0.1.8"]
                             [lein-kibit "0.0.8"]]}}
  :aliases {"lint" ^{:doc "Lint all the things!"}
            ["do" "ancient," "kibit," ["bikeshed", "-m120", "-v"]]}
  :source-paths ["src"]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src"]
              :figwheel true
              :compiler {:main cityshelf.core
                         :asset-path "js/compiled/out"
                         :output-to "resources/public/js/compiled/cityshelf.js"
                         :output-dir "resources/public/js/compiled/out"
                         :source-map-timestamp true}}
             {:id "prod"
              :source-paths ["src"]
              :compiler {:output-to "resources/public/js/compiled/cityshelf.js"
                         :main cityshelf.core
                         :optimizations :advanced
                         :externs ["resources/public/js/externs.js"]
                         :pseudo-names true
                         :pretty-print false}}]}
  :scss {:src "resources/scss"
         :output-directory "resources/public/css"
         :output-extension "css"}
  :figwheel {
             :http-server-root "public"
             :server-port 3449
             :css-dirs ["resources/public/css"]}
  :min-lein-version "2.0.0")
