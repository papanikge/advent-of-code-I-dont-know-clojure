(defproject play "0.1.0-SNAPSHOT"
  :description "I-dont-know-clojure"
  :license { :name "MIT Public License" }
  :repl-options {
                 :prompt (fn [ns] (str "\u001B[35m[\u001B[34m" ns "\u001B[35m]\u001B[33mcλ:\u001B[m " ))
                 :welcome (println "Hello there mate!")}
  :dependencies [[org.clojure/clojure "1.9.0-RC2"]
                 [com.rpl/specter "1.0.5"]
                 [org.clojure/math.combinatorics "0.0.7"]])
