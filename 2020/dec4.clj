(ns dec4
  (:require [clojure.string :refer [split replace]]
            [clojure.set :as set]))


(def passports
  (map #(replace % "\n" " ") (split (slurp "./dec4.txt") #"\n\n")))

;; cid: not required
(def required-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"})

(defn part1 []
  ;; yikes
  (count (filter #(set/superset? % required-fields)
           (map #(set %)
                (map #(take-nth 2 %)
                     (map #(split % #" ")
                          (map #(replace % ":" " ") passports)))))))


(= (part1) 192)


(defn byr-valid? [byr]
  (try
    (let [byr-int (Integer/parseInt byr)]
      (<= 1920 byr-int 2002))
    (catch Exception _ false)))

(defn iyr-valid? [iyr]
  (try
    (let [iyr-int (Integer/parseInt iyr)]
      (<= 2010 iyr-int 2020))
    (catch Exception _ false)))

(defn eyr-valid? [eyr]
  (try
    (let [eyr-int (Integer/parseInt eyr)]
      (<= 2020 eyr-int 2030))
    (catch Exception _ false)))

(defn hgt-valid? [hgt]
  (try
    (let [match (re-matches #"([0-9]{0,3})(cm|in)" hgt)
          value (Integer/parseInt (second match))
          unit (last match)]
      (case unit
        "cm" (<= 150 value 193)
        "in" (<= 59 value 76)
        :else false))
    (catch Exception _ false)))

(defn hcl-valid? [hcl]
  (not (nil? (re-matches #"#[a-z0-9]{6}" hcl))))

(defn ecl-valid? [ecl]
  (not (nil? (re-matches #"amb|blu|brn|gry|grn|hzl|oth" ecl))))

(defn pid-valid? [pid]
  (not (nil? (re-matches #"[0-9]{9}" pid))))

(def validation-mapping
  {"byr" byr-valid?
   "iyr" iyr-valid?
   "eyr" eyr-valid?
   "hgt" hgt-valid?
   "hcl" hcl-valid?
   "ecl" ecl-valid?
   "pid" pid-valid?})

(defn all-true? [v]
  (every? true? v))

(defn valid? [m]
  (let [relevant-entries (dissoc m "cid")]
    (and
      (= (set (keys relevant-entries)) required-fields)
      (all-true?
        (for [[k v] (seq relevant-entries)]
          (let [validity-fn (get validation-mapping k)]
            (validity-fn v)))))))

(defn part2 []
  ;; also yikes
  (count (filter true?
          (map #(valid? %)
            (map #(zipmap (take-nth 2 %) (take-nth 2 (rest %)))
              (map #(split % #" ")
                    (map #(replace % ":" " ") passports)))))))

(= (part2) 101)
