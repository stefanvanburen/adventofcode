(ns dec2
  (:require [clojure.string :refer [split-lines]]))

(def input (split-lines (slurp "./dec2.txt")))

(defn is-valid [line]
  (let [[low high c s] (rest ; re-matches returns the whole string as
                             ; well as subgroups, so skip the string
                          (re-matches #"(\d+)-(\d+) (\w): (\w+)" line))
        c-frequency (get (frequencies s) (.charAt c 0) 0)
        low (Integer/parseInt low)
        high (Integer/parseInt high)]
      (<= low c-frequency high)))


(defn part1 []
  (count (filter #(true? (is-valid %)) input)))

(= (part1) 546)

(defn is-valid-2 [line]
  (let [[low high c s] (rest ; re-matches returns the whole string as
                             ; well as subgroups, so skip the string
                          (re-matches #"(\d+)-(\d+) (\w): (\w+)" line))
        c (.charAt c 0)
        v1 (get s (dec (Integer/parseInt low)))
        v2 (get s (dec (Integer/parseInt high)))]
    (or (and (= c v1) (not= c v2))
        (and (= c v2) (not= c v1)))))


(defn part2 []
  (count (filter #(true? (is-valid-2 %)) input)))

(= (part2) 275)
