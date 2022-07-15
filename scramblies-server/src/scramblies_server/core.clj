(ns scramblies-server.core
  (:require
   [luminus.http-server :as http]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :as route]
   [clojure.data.json :as json]
   [clojure.string :refer [split]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn scramble?
  "takes args str1 str2 -[all lowercase] and returns true if a portion of str1 characters can be rearranged to match str2, otherwise returns false"
  [str1 str2]
  (let [[vec-str1 vec-str2]   (map #(split % #"") [str1 str2])
        [freq-str1 freq-str2] (map frequencies [vec-str1 vec-str2])]
    (cond
      (some false? (map #(contains? freq-str1 %) (keys freq-str2))) false
      (some true? (map (fn [[k v]] (> v (freq-str1 k))) freq-str2)) false
      :else true)))

(defn scramblies
  [req]
  (let [{:keys [str1 str2]}   (:params req)
        result                {:status 200
                               :headers {:Content-Type "application/json"}
                               :body    (scramble? str1 str2)}]
    (json/write-str result)))

(defroutes scramblies-routes
  (GET "/" [] scramblies)
  (route/not-found "Error, page not found"))

(defn -main [& _]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    (println "Starting Scramblies Server")
    (http/start {:port port :handler (wrap-defaults #'scramblies-routes site-defaults)})))