(ns play.day10
  (:use [com.rpl.specter]))

(def inpts [157,222,1,2,177,254,0,228,159,140,249,187,255,51,76,30])

(def start-list (range 255))

(defn get-valid-pos [pos ls]
  (mod pos (count ls)))

;; this replaces a list inside another
;; (stick-in [0 1 2 3 4] [:a :b] 0) >> [:a :b 2 3 4]
;; 
;; why is this so hard in lisp? maybe specter could help but I don't think
;; it plays well with `cycle`, and holy smokes this is ugly...
(defn stick-in [stick-here stick-this pos]
  (loop [ar stick-here
         i pos
         y 0]
    (if (< y (count stick-this))
      (recur (assoc ar i (nth stick-this y)) (get-valid-pos (inc i) stick-here) (inc y))
      ar)))

(defn process [[ls pos skip] input]
  (let [sublist (take input (drop pos (cycle ls)))
        next-pos (get-valid-pos (+ pos input skip) ls)]
    [(stick-in ls (reverse sublist) pos) next-pos (inc skip)]))

(defn silver-star []
  (apply * (take 2 (first (reduce process [start-list 0 0] inpts)))))

;;; waaaaaaaaaaaay to slow for the 64 rounds of the gold-star
