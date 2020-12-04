(ns dec1
  (:require [clojure.string :refer [split-lines]]))

(def input (map #(Integer/parseInt %) (split-lines (slurp "./dec1.txt"))))

(defn part1 []
  (first (for [x input
               y input
               :when (= (+ x y) 2020)]
          (* x y))))

(defn part2 []
  (first (for [x input
               y input
               z input
               :when (= (+ x y z) 2020)]
          (* x y z))))

(= (part1) 793524)
(= (part2) 61515678)
