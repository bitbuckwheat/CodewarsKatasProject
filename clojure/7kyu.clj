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
