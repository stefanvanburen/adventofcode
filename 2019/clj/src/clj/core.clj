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
