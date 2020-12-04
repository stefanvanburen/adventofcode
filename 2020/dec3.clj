(ns dec3
  (:require [clojure.string :refer [split-lines]]))

(def input (split-lines (slurp "./dec3.txt")))

(defn part1 []
  (count (filter #(= \# %)
            (for [y (range 0 (count input))
                  :let [x (mod (* y 3) (count (first input)))]]
              (nth (nth input y) x)))))

(defn part2 []
  (reduce *
   (for [[right down] '([1 1] [3 1] [5 1] [7 1] [1 2])]
     (count (filter #(= \# %)
               (for [y (range 0 (count input) down)
                     :let [i (/ y down)
                           x (mod (* i right) (count (first input)))]]
                 (nth (nth input y) x)))))))


(= (part1) 230)
(= (part2) 9533698720)
