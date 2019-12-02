(ns clj.core)

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
