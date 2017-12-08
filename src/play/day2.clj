(ns play.day2)

(require ['clojure.string :as 'str])
(require ['clojure.math.combinatorics :as 'combo])

(defn get-lines [filename]
  (str/split-lines (slurp filename)))

(defn prepare [line]
  (map #(Integer/parseInt %) (str/split line #"\t")))

(defn get-diff [line]
  (let [min (apply min (prepare line))
        max (apply max (prepare line))]
    (- max min)))

(defn first-star [filename]
  (reduce + (map get-diff (get-lines filename))))

(defn interesting-eq?
  [a b]
  (cond
    (>= a b) (zero? (mod a b))
    (< a b)  (zero? (mod b a))
    :else "zero"))

(defn get-div-eq [line]
  (filter #(interesting-eq? (first %) (last %)) (combo/combinations line 2)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;^^^^^^^^^^^^^^^^^ how do I break in the definition of interesting-eq? apply?

(defn get-for-second-star [line]
  (let [tuple (first (get-div-eq (prepare line)))]
    (/ (apply max tuple) (apply min tuple))))

(defn second-star [filename]
  (reduce + (map get-for-second-star (get-lines filename))))
