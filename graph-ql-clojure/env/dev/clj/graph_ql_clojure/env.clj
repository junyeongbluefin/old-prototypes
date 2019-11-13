(ns graph-ql-clojure.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [graph-ql-clojure.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[graph-ql-clojure started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[graph-ql-clojure has shut down successfully]=-"))
   :middleware wrap-dev})
