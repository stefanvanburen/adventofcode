(ns dec7
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def rules
  (str/split-lines (slurp "./dec7.txt")))

(defn extract-rule [rule]
  (let [[container contains] (map str/trim (str/split rule #"bags contain"))]
    (if (= contains "no other bags.")
      {container {}}
      (let [c (str/split contains #",")
            e (map #(str/replace % #"bag\w*\.?" "") c)
            trimmed (map #(str/trim %) e)
            data (map (fn [s]
                        (let [[count & rest] (str/split s #" ")]
                          {:count (Integer/parseInt count) :color (str/join " " rest)}))
                      trimmed)]

          {container data}))))

(def extracted-rules (map extract-rule rules))

(defn can-contain? [color rule]
  (let [contained-colors (set (map :color (val (first rule))))]
    (when (contains? contained-colors color)
      (key (first rule)))))

(defn part1
  [holders]
  (let [possible-holders
        (map
          (fn [holder]
            (filter
              #(not (nil? %))
              (map #(can-contain? holder %) extracted-rules)))
          holders)
        new-values (set (flatten possible-holders))]
    ;; if we're finally adding no new values, we're done
    (if (set/subset? new-values holders)
      holders
      (part1 (set/union new-values holders)))))

(count (disj (part1 #{"shiny gold"}) "shiny gold"))

(def merged-rules (into {} extracted-rules))

(defn part2
  ([] (part2 "shiny gold" 1))
  ([color multiplier]
   (let [rules (get merged-rules color)
         ;; recurse
         counts (apply + (map #(part2 (:color %) (* multiplier (:count %))) rules))
         ;; current counts
         current (map #(* multiplier (:count %)) rules)]
     (apply + counts current))))


(part2)
