(defproject cld "0.1.1-SNAPSHOT"
  :description "Clojure Language Detection"
  :url "https://github.com/dakrone/cld"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojars.thnetos/langdetect-lib "1.1"]]
  :profiles {:dev {:dependencies [[criterium "0.3.0"]]}}
  :resource-paths ["resources"]
  :warn-on-reflection false)
