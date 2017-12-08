(ns play.day4)

(require ['clojure.string :as 'str])
(require ['clojure.math.combinatorics :as 'combo])

(defn get-lines [filename]
  (str/split-lines (slurp filename)))

(defn prepare [line]
  (str/split line #" "))

(defn anagram? [a b]
  (= (frequencies a)
     (frequencies b)))

(defn interesting
  "first star"
  [a b]
  (= a b))

(defn interesting'
  "second star"
  [a b]
  (anagram? a b))

(defn get-div-eq [line]
  (filter #(apply interesting' %) (combo/combinations line 2)))

(defn valid? [line]
  (empty? (get-div-eq line)))

(defn get-star [filename]
  (count (filter valid? (map prepare (get-lines filename)))))
