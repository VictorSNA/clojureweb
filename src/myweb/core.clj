(ns myweb.core
  (:require [ring.adapter.jetty :as jetty]))

(defn myapp [request] "Hello World")

(defn string-response-middleware [handler]
  (fn [request]
    (let [response (handler request)]
      (if (instance? String response)
        {:body response
         :status 200
         :headers {"Content-Type" "text/html"}}
        response))))

(defn -main []
  (jetty/run-jetty (string-response-middleware myapp) {:port 3000}))
