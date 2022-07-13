(ns scramblies-server.core
  (:require
   [luminus.http-server :as http]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [clojure.data.json :as json]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn scramblies
  [_]
  (let [result {:status 200
                :headers {:Content-Type "application/json"}
                :body    "Well, This Works"}]
    (json/write-str result)))

(defroutes scramblies-routes
  (GET "/" [] scramblies)
  (route/not-found "Error, page not found"))

(defn -main [& _]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (println "Starting Scramblies Server")
    (http/start {:port port :handler (wrap-defaults #'scramblies-routes site-defaults)})))