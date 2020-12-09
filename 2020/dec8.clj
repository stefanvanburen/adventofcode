(ns dec8)

(def raw-instructions (slurp "./dec8.txt"))

(def instruction-regex
  #"(?<op>nop|acc|jmp) (?<posneg>\+|\-)(?<cnt>\d*)")


(defn parse-instructions [raw-instructions]
  (let [instruction-matcher (re-matcher instruction-regex raw-instructions)
        parsed-instructions (atom [])]
    (while
      (not (nil? (re-find instruction-matcher)))
      (swap! parsed-instructions conj {:op (.group instruction-matcher "op")
                                       :posneg (.group instruction-matcher "posneg")
                                       :cnt (Integer/parseInt (.group instruction-matcher "cnt"))}))
    @parsed-instructions))

(defn handle-instruction [ins current-pos current-acc]
  (case (:op ins)
    "nop" [(inc current-pos) current-acc]
    "acc" (if (= (:posneg ins) "+")
            [(inc current-pos) (+ current-acc (:cnt ins))]
            [(inc current-pos) (- current-acc (:cnt ins))])
    "jmp" (if (= (:posneg ins) "+")
            [(+ current-pos (:cnt ins)) current-acc]
            [(- current-pos (:cnt ins)) current-acc])))


(defn handle-instructions
  ([instructions] (handle-instructions instructions #{} 0 0))
  ([instructions seen-instructions current-pos current-acc]
   (let [[next-pos next-acc] (handle-instruction (nth instructions current-pos) current-pos current-acc)]
     (if (contains? seen-instructions next-pos)
       next-acc
       (handle-instructions
         instructions
         (conj seen-instructions next-pos)
         next-pos
         next-acc)))))

(defn part1 []
  (handle-instructions (parse-instructions raw-instructions)))

(= (part1) 1087)

(defn handle-instructions-2
  ([instructions] (handle-instructions-2 instructions #{} 0 0 false))
  ([instructions seen-instructions current-pos current-acc swapped-op?]
   (let [[next-pos next-acc] (handle-instruction (nth instructions current-pos) current-pos current-acc)]
     (if (contains? seen-instructions next-pos)
       (if swapped-op?
         ;; if we've already swapped the op, return
         ;; TODO: update this so that if we haven't reached the end, FAIL
         next-acc
         ;; otherwise, change the op
         (let [current-instruction (nth instructions current-pos)
               swapped-instructions (assoc instructions current-pos {:op "nop" ; naively assume we have to go from "jmp" to "nop"
                                                                     :posneg (:posneg current-instruction)
                                                                     :cnt (:cnt current-instruction)})]
           (handle-instructions-2
             swapped-instructions
             seen-instructions
             current-pos
             current-acc
             true)))
       (handle-instructions-2
         instructions
         (conj seen-instructions next-pos)
         next-pos
         next-acc
         swapped-op?)))))

(handle-instructions-2 (parse-instructions raw-instructions))
