(ns play.day1)

(defn eq
  [elem]
  (= (first elem) (last elem)))

(defn construct-tuples
  [input]
  (partition 2 1 [(first input)] input))

(defn construct-tuples'
  [input]
  (let [x (/ (count input) 2)]
    (partition (+ x 1) 1 (concat input (take x input)))))

(defn for-column
  [input]
  (reduce +
    (map first
      (filter eq (construct-tuples input)))))
;;;;;;;;;;;;;;;;;;^^^^^^^^^^^^^^^^ change this for first/second

(defn day1
  "I don't know anything."
  [input]
  (for-column
    (map #(Integer/parseInt (str %)) (seq input))))
