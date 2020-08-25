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

; day 3
(def values '(["R998","U367","R735","U926","R23","U457","R262","D473","L353","U242","L930","U895","R321","U683","L333","U623","R105","D527","R437","D473","L100","D251","L958","U384","R655","U543","L704","D759","R529","D176","R835","U797","R453","D650","L801","U437","L468","D841","R928","D747","L803","U677","R942","D851","R265","D684","L206","U763","L566","U774","L517","U337","L86","D585","R212","U656","L799","D953","L24","U388","L465","U656","L467","U649","R658","U519","L966","D290","L979","D819","R208","D907","R941","D458","L882","U408","R539","D939","R557","D771","L448","U460","L586","U148","R678","U360","R715","U312","L12","D746","L958","U216","R275","D278","L368","U663","L60","D543","L605","D991","L369","D599","R464","D387","L835","D876","L810","U377","L521","U113","L803","U680","L732","D449","R891","D558","L25","U249","L264","U643","L544","U504","R876","U403","R950","U19","L224","D287","R28","U914","R906","U970","R335","U295","R841","D810","R891","D596","R451","D79","R924","U823","L724","U968","R342","D349","R656","U373","R864","U374","L401","D102","L730","D886","R268","D188","R621","U258","L788","U408","L199","D422","R101","U368","L636","U543","R7","U722","L533","U242","L340","D195","R158","D291","L84","U936","L570","D937","L321","U947","L707","U32","L56","U650","L427","U490","L472","U258","R694","U87","L887","U575","R826","D398","R602","U794","R855","U225","R435","U591","L58","U281","L834","D400","R89","D201","L328","U278","L494","D70","L770","D182","L251","D44","R753","U431","R573","D71","R809","U983","L159","U26","R540","U516","R5","D23","L603","U65","L260","D187","R973","U877","R110","U49","L502","D68","R32","U153","R495","D315","R720","D439","R264","D603","R717","U586","R732","D111","R997","U578","L243","U256","R147","D425","L141","U758","R451","U779","R964","D219","L151","D789","L496","D484","R627","D431","R433","D761","R355","U975","L983","U364","L200","U578","L488","U668","L48","D774","R438","D456","L819","D927","R831","D598","L437","U979","R686","U930","L454","D553","L77","D955","L98","U201","L724","U211","R501","U492","L495","U732","L511"], ["L998","U949","R912","D186","R359","D694","L878","U542","L446","D118","L927","U175","R434","U473","R147","D54","R896","U890","R300","D537","R254","D322","R758","D690","R231","U269","R288","U968","R638","U192","L732","D355","R879","U451","R336","D872","L141","D842","L126","U584","L973","D940","R890","D75","L104","U340","L821","D590","R577","U859","L948","D199","L872","D751","L368","U506","L308","U827","R181","U94","R670","U901","R739","D48","L985","D801","R722","D597","R654","D606","R183","U646","R939","U677","R32","U936","L541","D934","R316","U354","L415","D930","R572","U571","R147","D609","L534","D406","R872","D527","L816","D960","R652","D429","L402","D858","R374","D930","L81","U106","R977","U251","R917","U966","R353","U732","L613","U280","L713","D937","R481","U52","R746","U203","L500","D557","L209","U249","R89","D58","L149","U872","R331","D460","R343","D423","R392","D160","L876","U981","L399","D642","R525","U515","L537","U113","R886","D516","L301","D680","L236","U399","R460","D869","L942","D280","R669","U476","R683","D97","R199","D444","R137","D489","L704","D120","R753","D100","L737","U375","L495","D325","R48","D269","R575","U895","L184","D10","L502","D610","R618","D744","R585","U861","R695","D775","L942","U64","L819","U161","L332","U513","L461","D366","R273","D493","L197","D97","L6","U63","L564","U59","L699","U30","L68","U861","R35","U564","R540","U371","L115","D595","L412","D781","L185","D41","R207","D264","R999","D799","R421","D117","R377","D571","R268","D947","R77","D2","R712","D600","L516","U389","L868","D762","L996","U205","L178","D339","L844","D629","R67","D732","R109","D858","R630","U470","L121","D542","L751","U353","L61","U770","R952","U703","R264","D537","L569","U55","L795","U389","R836","U166","R585","U275","L734","U966","L130","D357","L260","U719","L647","D606","R547","U575","R791","U686","L597","D486","L774","U386","L163","U912","L234","D238","L948","U279","R789","U300","R117","D28","L833","U835","L340","U693","R343","D573","R882","D241","L731","U812","R600","D663","R902","U402","R831","D802","L577","U920","L947","D538","L192"]))

(defn get-coords [start command cur]
  (let [cnt (range (read-string (subs command 1)))]
    (case (str (first command))
      "L" (for [cnt cnt] {(vector (- (first start) cnt) (second start)) (+ cur cnt)})
      "R" (for [cnt cnt] {(vector (+ (first start) cnt) (second start)) (+ cur cnt)})
      "U" (for [cnt cnt] {(vector (first start) (+ (second start) cnt)) (+ cur cnt)})
      "D" (for [cnt cnt] {(vector (first start) (- (second start) cnt)) (+ cur cnt)})
    )))

(get-coords '(0, 0) "L10" 0)

; get all the coordinates
(defn all-coords [z]
  (loop [commands z
         start '(0, 0)
         cnt 0]
    (if (= (first commands) nil)
      all
      (recur
        (rest commands)
        (last
          (let [n (range (read-string (subs (first commands) 1)))]
            (case (str (first (first commands)))
              "L" (assoc all (for [n n] (vector (- (first start) n) (second start)) (cnt)))
              "R" (assoc all (for [n n] (vector (+ (first start) n) (second start)) (cnt)))
              "U" (assoc all (for [n n] (vector (first start) (+ (second start) n)) (cnt)))
              "D" (assoc all (for [n n] (vector (first start) (- (second start) n)) (cnt))))))
        (inc cnt)))))

(assoc (conj {} (apply hash-map (apply concat (for [x (range 10)] [`(~x 0) x])))) '(1 0) 10)

(defn assoc-if [pred coll k v]
  (if (pred coll k) (assoc coll k v) coll))

(defn assoc-if-new [coll k v]
  (assoc-if (complement contains?) coll k v))

(apply hash-map (seq (for [x (range 10)] ([x 0] 0))))

(repeat 10 '(0 0))

(def fst (all-coords (first values)))


; this assumes starts at (0, 0)
(defn manhattan-dist [x] (+ (Math/abs (first x)) (Math/abs (second x))))

; part one
; actual answer is 221. this gives 218 (why???)
(apply min
       (filter #(not (= % 0))
               (map manhattan-dist matches)))

; 610, as expected
(def values '(["R75","D30","R83","U83","L12","D49","R71","U7","L72"], ["U62","R66","U55","R34","D71","R55","D58","R83"]))
; 410, like expected
(def values '(["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"], ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"]))


(def fst (all-coords (first values)))
(def snd (all-coords (second values)))
(def matches (clojure.set/intersection (set fst) (set snd)))
(defn dist [matches] (for [z matches] (+ (.indexOf fst z) (.indexOf snd z))))
(apply min (filter #(not (= % 0)) (dist matches)))

; 18550 - too high
; 18547 - too high
; 18545 - not right
; 18546 - not right

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
