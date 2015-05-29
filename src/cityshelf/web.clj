(ns ^{:doc "Web server for CityShelf."
      :author "Eric Weinstein <eric.q.weinstein@gmail.com>"}
  cityshelf.web
  (:require [cheshire.core :as json]
            [cityshelf.views.index :as home]
            [clj-http.client :as client]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [compojure.handler]
            [ring.adapter.jetty :refer [run-jetty]]))

(def x-recruiting-header
  "If you're reading this, you should get in touch with us: dev@cityshelf.com")

(defroutes routes
  "Our myriad routes."

  (GET "/" []
    {:headers {"X-Recruiting" x-recruiting-header
               "Content-Type" "text/html; charset=utf-8"}
     :body (home/index "CityShelf")})

  ;; Handles subscribing users to the e-mail list via MailChimp.
  (POST "/subscribe"
        {:keys [headers params body] :as request}
        (let [api-req {:apikey ""
                       :id ""
                       :email {:email (:email params)}}
              api-rsp (client/post ""
                                   {:body (json/generate-string api-req)
                                    :content-type :json
                                    :accept :json})]))

  (route/files "/" {:root "resources/public"})

  (route/not-found
    {:headers {"X-Recruiting" x-recruiting-header
               "Content-Type" "text/html; charset=utf-8"}
     :body (home/index "CityShelf")}))

(def handler
  "Handler helper function."
  (compojure.handler/api routes))

(defn -main
  "Starts the web server."
  [& args]
  (let [port (Integer. (get (System/getenv) "PORT" "8081"))]
    (run-jetty #'handler {:port port})))
