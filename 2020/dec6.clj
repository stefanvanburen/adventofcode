(ns dec6
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def groups
  (map #(str/split-lines %) (str/split (slurp "./dec6.txt") #"\n\n")))

(defn count-yes [group]
  (count (set (str/join group))))

(defn part1 []
  (reduce + (map count-yes groups)))

(defn all-yes [group]
  (count (apply set/intersection (map set group))))

(defn part2 []
  (reduce + (map all-yes groups)))
