;; 7th kyu katas

(ns clojure.core)

(def lst [1 9 13 1 99 9 9 13])

(defn last
  "Returns the last element of an ISeq"
  [lst]
  (if (empty? lst)
    nil
    (nth lst (- (count lst) 1))))

(= (last [1 9 13 1 99 9 9 13]) 13)
(= (last '()) nil)
(= (last "\"I suppose I should learn Lisp, but it seems so foreign.\" - Paul Graham, Nov 1983") \3)

;; looks like an elegant solution
;; (def last
;;   (comp first reverse))


;; ==================================================
;; https://www.codewars.com/kata/59f11118a5e129e591000134

(def array [4 5 7 5 4 8])
(def array1 [9,10,19,13,19,13])

;; (defn repeats [array]
;;   (apply + (keys (filter #(= 1 (val %)) (frequencies array)))))

;; or with threading
(defn repeats [array]
  (->> array
       frequencies
       (filter #(= 1 (val %)))
       keys
       (apply +)))
  ;; (apply + (keys (filter #(= 1 (val %)) (frequencies array)))))

(frequencies array) ; {4 2, 5 2, 7 1, 8 1}
(frequencies array1) ; {9 1, 10 1, 19 2, 13 2}

(apply + (keys (filter #(= 1 (val %)) (frequencies array))))

(= (repeats [4 5 7 5 4 8]) 15)
(= (repeats [9,10,19,13,19,13]) 19)
(= (repeats [16,0,11,4,8,16,0,11]) 12)
(= (repeats [5,17,18,11,13,18,11,13]) 22)
(= (repeats [5,10,19,13,10,13]) 24)

;; ==================================================
;; https://www.codewars.com/kata/5680781b6b7c2be860000036


;; (def word "super")
(def word "SuPEr HerO")
(defn vowel? [letter]
  (.contains "aeiouyAEIOUY" letter))

;; one-liner, working and passes the tests
;; (defn vowel-indices [word]
;;   (vec (map inc (keys (sort (apply merge (filter #(vowel? (str (val (first %)))) (map-indexed hash-map word))))))))

;; threaded, working and passes the tests
(defn vowel-indices [word]
  (->> (map-indexed hash-map word)
       (filter #(vowel? (str (val (first %)))))
       (apply merge)
       sort
       keys
       (map inc)
       vec))

;; interesting short sulution
;; (def vowel? (into #{} "aeiouyAEIOUY"))
;;
;; (defn vowel-indices [word]
;;   (->> word
;;        (map-indexed (fn [i c] {:i (inc i) :c c}))
;;        (filter #(-> % :c vowel?))
;;        (map :i)))

;; and even shorter and elegant solution
;; (def vowels #{\a \A \e \E \i \I \o \O \u \U \y \Y})
;; (defn vowel-indices
;;   [word]
;;   (keep-indexed #(when (vowels %2) (inc %1)) word))


(.contains "aeiouyAEIOUY" "B")
(vec "aeiouyAEIOUY") ; [\a \e \i \o \u \y \A \E \I \O \U \Y]
(vowel? "a")
(clojure.string/upper-case "aeiouy") ; "AEIOUY"
(map-indexed vector word) ; ([0 \s] [1 \u] [2 \p] [3 \e] [4 \r])
(map-indexed hash-map word) ; ({0 \s} {1 \u} {2 \p} {3 \e} {4 \r})
(map #(str (val (first %))) (map-indexed hash-map word))
(filter #(vowel? (val (first %)) (map-indexed vector word)))
(filter #(vowel? (str (val (first %)))) (map-indexed hash-map word))
(map keys (filter #(vowel? (str (val (first %)))) (map-indexed hash-map word))) ; ((1) (3))
(apply merge (filter #(vowel? (str (val (first %)))) (map-indexed hash-map word))) ; {1 \u, 3 \e}
(vec (map inc (keys 
                (sort 
                  (apply merge (filter
                                     #(vowel? (str (val (first %))))
                                     (map-indexed hash-map word)))))))

(str \b) ; "b"
(val (first {2 \s})) ; \s

(= (vowel-indices "super") [2,4])
(= (vowel-indices "SuPEr") [2,4])
(= (vowel-indices "SuPEr HerO") [2,4,8,10])

;; ==========================================================================
;; https://www.codewars.com/kata/6129095b201d6b000e5a33f0
;; get back next time

(defn f [x y z]
    0)


(= 20 (f 2 1 1))
(= 46 (f 1 2 3))
(= 54 (f 2 2 2))


;; ==========================================================================
;; https://www.codewars.com/kata/54592a5052756d5c5d0009c3


(defn head [coll]
  (first coll))

(defn tail [coll]
  (rest coll))

(defn init [coll]
  (take (- (count coll) 1) coll))

(defn last_ [coll]
  (last coll))

;; interestinly, it can be done with just defs
;; and there is drop-last func also
;; (def head first)
;; (def tail rest)
;; (def init drop-last)
;; (def last_ last)

(= (head [5 1]) 5)
(= (tail [1]) [])
(= (init [1 5 7 9]) [1 5 7])
(= (last_ [7 2]) 2)

;; ==========================================================================
;; https://www.codewars.com/kata/56e7d40129035aed6c000632/
;; get back next time

(def n [3])

(defn create-lines [n]
  (loop [counter 2 tri {0 [1 0] 1 [1 1 0]}]
    (if (> counter n)
      (map #(*' % %) (get tri n))
      (recur (+' 1 counter)
             (assoc tri
                    counter
                    (loop [num 0 row [1]]
                      (if (> num (-' counter 1))
                        (conj row 0)
                        (recur (+' 1 num)
                               (conj row
                                    (+
                                      (nth (get tri (-' counter 1))
                                           num)
                                      (nth (get tri (-' counter 1))
                                           (+' 1 num))))))))))))

(defn easy-line [n]
  (reduce +' (create-lines n)))
(create-lines 4) ; {1 [1 0], 2 [1 1 0], 3 [1 2 1 0], 4 [1 3 3 1 0]}
#_(create-lines 2) ; (1 16 25 4 0)

(comment
 (create-lines 5) ; {1 [1 0], 2 [1 1 0], 3 [1 2 1 0], 4 [1 3 3 1 0], 5 [1 4 6 4 1 0]}
 (create-lines 5) ; [1 4 6 4 1 0]
 (create-lines 7)
 (def trig {1 [1] 2 [1 1]})
 (nth (get trig 2) 0))

(= (easy-line 7) 3432N)
(= (easy-line 13) 10400600N)
(= (easy-line 17) 2333606220N)
(= (easy-line 19) 35345263800N)

;; ==========================================================================
;; https://www.codewars.com/kata/542f0c36d002f8cd8a0005e5
;; get back next time

(def n 10)

(defn last-chair [n]
  (let [p 1 chairs {}]
    (if (> p n)
      (get chairs (dec p))
      (do))))


(last-chair n)
(= (last-chair 10) 9)

(def chairs (assoc {} (range 1 11) nil))
(println chairs)

;; ==========================================================================
;; https://www.codewars.com/kata/5b94d7eb1d5ed297680000ca/


;; for some reason my solution has been posted to the very top
;; I don't know why, it doesn't look like the Best
(defn solve [dirs]
  (let [vectors (map #(clojure.string/split % #" ") dirs)
        dir-change {"Right" "Left"
                    "Left" "Right"}
        sent-start (concat '("Begin") (reverse
                                        (map #(get dir-change %)
                                             (map #(first %)
                                                  (rest vectors)))))
        sent-end (map #(clojure.string/join " " %)
                      (reverse (map #(rest %) vectors)))]
    (vec (map #(str %1 " " %2) sent-start sent-end))))

;; no, don't think it's easier
;; (defn solve [dirs]
;;   (let [vectors (reverse (map #(clojure.string/split % #" ") dirs))]
;;     println vectors))


    ;;     roads (case (count (first vectors))
    ;;               3 (map last vectors)
    ;;               4 (map #(take-last 2 %) vectors))
    ;;     rs (map last vectors)
    ;;     dir-change (zipmap rs (reverse rs))
    ;;     ;; {:keys [rs] :vals [reverse rs]} rs
    ;;     instrs {"Begin" "Begin"
    ;;              "on" "on"
    ;;              "Road" "Road"
    ;;              "Right" "Left"
    ;;              "Left" "Right"}
    ;;     ins (map #(assoc instrs % nil) rs)]
    ;; (println dir-change)
    ;; ;; (println go)
    ;; (println rs)
    ;; (map #(assoc instrs % nil) rs)
    ;; (map #(assoc instrs %1 %2) (reverse rs))
    ;; (println vectors)
    ;; (println roads)
    ;; (println ins)))


(def coll (map (comp char (partial + 65)) (range 26)))
(println coll)
(let [[?] coll]
  ?)
(let [[_ ?] coll]
  ?)
(def test "Begin on 3rd Blvd") ;;["Begin on 3rd Blvd" "Right on First Road" "Left on 9th Dr"]
(clojure.string/split test #" ")
(let [[?1 ?2 ?3] (clojure.string/split test #" ")]
  [?1 ?2 ?3])
(let [[?1 ?2 ?3] (clojure.string/split test #" ")]
  ?1)
(let [{:keys [?1]} (clojure.string/split test #" ")]
  (?1))

(def a ["A" "B"])
(def b ["C" "D"])
(let [{:keys a}]
  println y)
(def dirs ["first second" "third fourth"])
(println dirs)
(map #(clojure.string/split % #" ") dirs) ; (["first" "second"] ["third" "fourth"])
(map #(clojure.string/split % #" ") ["first second" "third fourth"])

(into {} a b)
(hash-map a b)
(apply hash-map a)
(zipmap a b)

(= (solve ["Begin on Road A"
           "Right on Road B"
           "Right on Road C"
           "Left on Road D"])
   ["Begin on Road D"
    "Right on Road C"
    "Left on Road B"
    "Left on Road A"])
(= (solve ["Begin on 3rd Blvd" "Right on First Road" "Left on 9th Dr"])
   ["Begin on 9th Dr" "Right on First Road" "Left on 3rd Blvd"])
(= (solve ["Begin on Road A"]) ["Begin on Road A"])
(= (solve ["Begin on A" "Left on B" "Right on C" "Right on D" "Right on E"
           "Left on F" "Right on G" "Right on H" "Left on I" "Left on J"])
   ["Begin on J" "Right on I" "Right on H" "Left on G" "Left on F"
    "Right on E" "Left on D" "Left on C" "Left on B" "Right on A"])
