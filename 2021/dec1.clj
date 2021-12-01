(ns dec1
  (:require [clojure.string :refer [split-lines]]))

(def input (map #(Integer/parseInt %) (split-lines (slurp "./dec1.txt"))))

(defn part1 []
  (->> (partition 2 1 input) ;; take 2 at a time
       (map (fn [t] (> (second t) (first t))))
       (filter true?)
       (count)))

(defn- sliding-higher? [x]
  (let [[a b c d] x
        prev (+ a b c)
        nxt (+ b c d)]
    (> nxt prev)))

(defn part2 []
  (->> (partition 4 1 input) ;; take 4 at a time
       (map sliding-higher?)
       (filter true?)
       (count)))

(= (part1) 1602)
(= (part2) 1633)
