(ns play.day8
  (:require [clojure.string :as string]))

;; goddammit

(defn inpt []
  (as-> "input/input-day8.txt" $
      (slurp $)
      (string/split $ #"\n")
      (map #(string/split % #" ") $)
      (apply vector $)))

(defn evaluate-pred [s]
  "return the freaking function"
  (cond
    (= s "<") <
    (= s "<=") <=
    (= s ">") >
    (= s ">=") >=
    (= s "==") =
    (= s "!=") not=))

(defn evaluate-instr [instr]
  "also return the freaking function"
  (cond
    (= instr "inc") +
    (= instr "dec") -))

(defn per-line [env line]
  (let [reg       (first line)
        instr     (first (drop 1 line))
        instr-val (Integer/parseInt (first (drop 2 line)))
        clause    (drop 3 line)
        clause-reg  (nth clause 1)
        clause-pred (nth clause 2)
        clause-num  (Integer/parseInt (nth clause 3))]
    (if ((evaluate-pred clause-pred) (get env clause-reg 0) clause-num)
      (assoc env reg ((evaluate-instr instr) (get env reg 0) instr-val))
      env)))

(defn silver-star []
  (apply max (vals (reduce per-line {} (inpt)))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn vals' [x]
  (if (nil? (vals x))
    (list 0)
    (vals x)))

(defn per-line' [[env m] line]
  (let [reg       (first line)
        instr     (first (drop 1 line))
        instr-val (Integer/parseInt (first (drop 2 line)))
        clause    (drop 3 line)
        clause-reg  (nth clause 1)
        clause-pred (nth clause 2)
        clause-num  (Integer/parseInt (nth clause 3))
        env (if ((evaluate-pred clause-pred) (get env clause-reg 0) clause-num)
              (assoc env reg ((evaluate-instr instr) (get env reg 0) instr-val)) env)]
    [env (max m (apply max (vals' env)))]))

(defn gold-star []
  (last (reduce per-line' [{} 0] (inpt))))
