(ns user
  (:require [graph-ql-clojure.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [graph-ql-clojure.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'graph-ql-clojure.core/repl-server))

(defn stop []
  (mount/stop-except #'graph-ql-clojure.core/repl-server))

(defn restart []
  (stop)
  (start))


