(ns dec9)

; (def test-input "35
; 20
; 15
; 25
; 47
; 40
; 62
; 55
; 65
; 95
; 102
; 117
; 150
; 182
; 127
; 219
; 299
; 277
; 309
; 576")

(def test-input (slurp "./dec9.txt"))

(def parsed-input
  (map read-string (re-seq #"\d+" test-input)))

(def preamble-length 25)

(def valid (for [i (range (- (count parsed-input) preamble-length))
                 :let [preamble (take preamble-length (drop i parsed-input))
                       target (first (drop (+ i preamble-length) parsed-input))]]
             ;; TODO: this loop creates something like '(:valid :valid ...) or '()
             ;;       it's somewhat silly - could probably be simplified with another construct
             (for [x preamble
                   y preamble
                   :when (and (not= x y) (= (+ x y) target))]
              :valid)))

(defn part1 []
  (first (for [i (range (count valid))
               :when (= (nth valid i) '())]
          (nth parsed-input (+ i preamble-length)))))

(= (part1) 133015568)
