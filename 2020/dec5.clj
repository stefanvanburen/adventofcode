(ns dec5
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def boarding-passes (str/split-lines (slurp "./dec5.txt")))

(defn fb
  ([s]
   (fb s 0 127))
  ([s low high]
   (if (= (count s) 1)
     (case (first s)
       ;; figure out which to return
       \F low
       \B high)
     (case (first s)
       ;; adjust range and recurse
       \F (fb (rest s) low (int (/ (+ low high) 2)))
       \B (fb (rest s) (int (inc (/ (+ low high) 2))) high)))))

(defn rl
  ([s]
   (rl s 0 7))
  ([s low high]
   (if (= (count s) 1)
     (case (first s)
       ;; figure out which to return
       \L low
       \R high)
     (case (first s)
       ;; adjust range and recurse
       \L (rl (rest s) low (int (/ (+ low high) 2)))
       \R (rl (rest s) (int (inc (/ (+ low high) 2))) high)))))

(defn seat-id [boarding-pass]
  (let [row (fb (take 7 boarding-pass))
        col (rl (drop 7 boarding-pass))]
    (+ col (* 8 row))))

(def seat-ids
  (map #(seat-id %) boarding-passes))

(defn part1 []
  ;; get the maximum seat-id value
  (apply max seat-ids))

(defn part2 []
  ;; because the lower seat-ids will still exist in the set, take the
  ;; maximum value seat-id
  (apply max (set/difference
               (set (range (part1)))
               (set seat-ids))))

(= (part1) 818)
(= (part2) 559)
