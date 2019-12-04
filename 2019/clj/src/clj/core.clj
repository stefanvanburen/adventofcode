(ns clj.core)

; day one
(def values
  (->>
    (slurp "one.txt")
    (clojure.string/split-lines)
    (filter #(> (count %) 0))
    ; convert to integer
    (map read-string)))

(defn fuel [x]
  (- (Math/floor (/ x 3)) 2))

; part one
(def one-one (int (reduce + (map fuel values))))
(= one-one 3159380)

(defn fuel-recur [x]
  (let [cur (fuel x)]
    (if (<= cur 0)
      '()
      (conj (fuel-recur cur) cur))))

; part two
(def one-two (int (reduce + (map #(reduce + %) (map fuel-recur values)))))
(= one-two 4736213)

; day two
(def values (vec (map read-string (clojure.string/split (slurp "two.txt") #","))))

(def start-values (assoc (assoc values 2 2) 1 12) )

(defn parse [thing loc]
  (let [cur (thing loc)]
    (case cur
      1 (recur
          (assoc thing (thing (+ loc 3)) (+ (thing (thing (+ loc 2))) (thing (thing (+ loc 1)))))
          (+ 4 loc))
      2 (recur
          (assoc thing (thing (+ loc 3)) (* (thing (thing (+ loc 2))) (thing (thing (+ loc 1)))))
          (+ 4 loc))
      99 thing)))

; part one
((parse start-values 0) 0)

; part two
(doseq [noun (range 100) verb (range 100)]
  (if (= ((parse (assoc (assoc values 2 verb) 1 noun) 0) 0) 19690720)
    (prn (+ (* 100 noun) verb))))
; day 4

(def input (range 307237 769058))

(defn always-increasing [x]
  (= (map int (seq (str x))) (sort < (map int (seq (str x))))))

(defn has-adjacent-match [x]
  (not (= (seq x) (dedupe x))))

; part 1
(count
  (filter
    #(and
       (always-increasing %)
       (has-adjacent-match (str %))
    input))

; part 2
(count
  (filter
    #(and
       (always-increasing %)
       (has-adjacent-match (str %))
       (has-two-count (combine-adjacent (str %))))
    input))


(defn combine-adjacent [x]
  (->> (partition-by identity x)))

(defn has-two-count [x] (some #(= (count %) 2) x))
