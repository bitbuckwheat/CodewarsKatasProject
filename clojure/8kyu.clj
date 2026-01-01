(ns clojure.core
  (:require [clojure.string :as s]))

(def v [\a \b \c])
(defn myfunc [v]
  (map str v))
(myfunc v)
(map str [\a \b])


(def n 8)
;; (defn summation [n]
;;   (reduce + (range 1 (inc n))))
;; (defn summation [n]
;;   (reduce + (range (inc n))))
;; (defn summation [n]
;;   (->> n inc range (reduce +)))
;; (defn summation [n]
;;   (->> (inc n) range (reduce +)))
;; (defn summation [n]
;;   (->> n inc range (apply +)))
(defn summation [n]
  (apply + (range (inc n))))
(summation n)

(def games ["1:2" "2:2" "3:3" "4:4" "2:2" "3:3" "4:4" "3:3" "4:4" "4:3"])
(def win 3)
(def loss 0)
(def tie 1)
(int \2)
;; (def score "4:4")
(defn get_point [score]
  (let [point (- (int (first score)) (int (last score)))]
    (cond
      (pos? point) 3
      (neg? point) 0
      :else 1)))
(defn get_points [games]
  (apply + (map get_point games)))

(get_points games)


(.create js/Ext "Ext.window.Window"
         #js {:title "Новая сделка"
              :width 400 :modal true
              :items #js [{:xtype "form"
                           :items #js [{:xtype "textfield" :fieldLabel "Клиент" :value "ООО Рога"}
                                       {:xtype "numberfield" :fieldLabel "Сумма" :value 100000}]}]
              :buttons #js [{:text "Сохранить"} {:text "Отмена"}]})


(def xs [1 2 3])
(def x 5)
(defn double [x]
  (* 2 x))
(defn maps [xs]
  (map double xs))
(defn maps1 [xs]
  (map #(* 2 %) xs))

(maps xs)
(maps1 xs)

(def lst [5 3 4])
(defn square [x] (* x x))
(defn square-sum [lst]
  (->> lst 
       (map square) 
       (reduce +)))
(square-sum lst)

;; or just with the lambda
(defn square-sum1 [lst]
  (->> lst 
         (map #(* % %)) 
         (reduce +)))

(square-sum1 lst)


(def arr ["i" "want" "to" "travel" "the" "world" "writing" "code" "one" "day"])
;; error when arr is empty
;; (defn star [x]
;;   (str x "***"))
;; (defn star-sort [arr]
;;   (let [s (reduce str (map star (first (sort arr))))]
;;     (subs s 0 (- (count s) 3))))

;; ok when arr is empty, all the tests passed
;; (defn star [x]
;;   (str x "***"))
;; (defn star-sort [arr]
;;   (let [s (reduce str (map star (first (sort arr))))]
;;     (if s
;;       (subs s 0 (- (count s) 3)))))

;; ok when arr is empty, all the tests passed
;; (defn star [x]
;;   (str x "***"))
;; (defn star-sort [arr]
;;   (let [s (reduce str (map star (first (sort arr))))]
;;     (if s
;;       (subs s 0 (- (count s) 3)))))

;; ok when arr is empty, all the tests passed
(defn star [x]
  (str x "***"))
(defn star-sort [arr]
  (let [s (reduce str (map star (first (sort arr))))]
    (if s (drop-last 3 s)))) ;; doesn't work

;; yet another option after watching the answers))
(defn star-sort [arr]
  (reduce str (interpose "***" (first (sort arr)))))

(reduce str (interpose "***" "test"))

(drop-last 3 "testest")
;; (defn star-sort [arr]
;;   (let [s (first (sort arr))]
;;     (-> s
;;         (println s)
;;       (subs 0 (- (count s) 1))
;;       (map star)
;;       (reduce str)
;;       (srt (last s)))))

(star-sort arr)

(count "test")


(def words "yoda doesn't speak like this")
(def words1 "Hello world!")
;; (defn reverce-words [words]
;;   (-> words
;;     (string/split #" ")))

;; (defn reverce-words [words]
;;   (reverse (string/split words #" ")))

;; (defn reverse-words [words]
;;   (-> words
;;     (string/split #" ")
;;     reverse))

(defn reverse-words [words]
  (string/join " " (reverse (string/split words #" "))))
(reverse-words words1)

;; and shorter version, need to alias clojure.string as s
(defn reverse-words [ws]
  (s/join " " (reverse (s/split ws #" "))))

;; someone's solution
;; (ns kata
;;  (:require [clojure.string :refer [joi]]))n split

;;(defn reverse-words [words]
;;  (join " " (rseq (split words #" "))))

;; and my adaptation
;; (ns kata
;;   (:require [clojure.string :refer [join split]]))
;;
;; (defn reverse-words [ws]
;;   (join " " (reverse (split ws #" "))))
;;
;; (ns kata
;;   (require [clojure.string :as s]))

(defn reverse-words1 [ws]
  (->> (s/split ws #" ")
       reverse
       (s/join " ")))

(reverse-words1 words)

(def n 2)
(defn even-or-odd [n]
  (if (even? n) "even" "odd"))
(even-or-odd n)

(def s "Hello")
(def n 5)
;; (defn s-r [n s]
;;   (reduce str (for [x (range 1 n)] s)))
(defn s-r [n s]
  (->> s
       (for [i (range n)])
       (reduce str)))
(s-r n s)



;; (defn recursive-reverse [coll]
;;   (let [acc ["empty"]]
;;     (if (empty? coll)
;;       acc
;;       (comp 
;;         (cons (first coll) acc)
;;         (recur (rest coll))))))
            ;; (cons (first coll) acc))))))

(defn recursive-reverse [coll]
  (letfn [(rev-helper [original-list accumulator]
            (if (empty? original-list)
              accumulator ; Base case: return the built-up accumulator
              (rev-helper (rest original-list) ; Recurse on the tail
                          (cons (first original-list) accumulator))))] ; Add current item to front of accumulator
    (rev-helper coll []))) ; Start with empty accumulator

(recursive-reverse [2 3 4 5 6])
;; (recursive-reverse [])
;;

(defn factorial [n]
  (if (= n 0)
    1
    (* n (factorial (dec n)))))

(factorial 100003N)


;; (def n 104500)

;; (defn no-boring-zeros [n]
;;   (->> n
;;     str
;;     (filter #(not= \0 %))
;;     (reduce str)
;;     Integer/parseInt))

(no-boring-zeros n)
(Integer/parseInt (reduce str (filter #(not= \0 %) (str n))))

(int "146")
(Integer/parseInt "126")

;; working and passing tests
(def n 10004700)
;; (defn no-boring-zeros [n]
;;   (let [s (str n)]
;;     (if (and (= (last s) \0) (> (count s) 1))
;;       (no-boring-zeros (reduce str (butlast s)))
;;       (Integer/parseInt s))))

;; (defn no-boring-zeros [n]
;;   (if (< n 10) n
;;     (if (= (last (str n)) \0)
;;       (no-boring-zeros (/ n 10))
;;       n)))

;; passes the test with Math/abs
;; recursive call can be exchanged wih recur keyword (after Codewars soltions observation)
(defn no-boring-zeros [n]
  (cond
   (< (Math/abs n) 10) n
   (not= (last (str n)) \0) n
   :else (no-boring-zeros (/ n 10))))

    ;; (if (and (= (last (str n)) \0) (> (count (str n)) 1))
    ;;   (no-boring-zeros (/ n 10))
    ;;   n)))

(no-boring-zeros n)
(use clojure.string)
(ends-with? "teststring" "ng")

(/ 4100 10)

(butlast "last")
(reduce str (butlast "last"))

(last "test")
(count "test")


(def year1 1705)
(def year2 705)
(def year3 5)
(def year4 1701)
(def year5 1700)
(def year6 0)
(def year7 1)
(defn century [year]
  (let [res (quot year 100)]
    (if (zero? (rem year 100))
      res
      (inc res))))

;; a bit clever form
(defn cent [year]
  (let [res {0 0}]
    (+ (quot year 100)
       (get res (rem year 100) 1))))

(century year7)
(cent year1)

(Integer/parseInt "05")
(quot year1 100) ; 17
(rem year1 100) ; 5
(quot year2 100) ; 7
(rem year2 100) ; 5
(quot year3 100) ; 0
(rem year3 100) ; 5


(def s "Hi! Hi!")
(def s1 "Hi!\nHi")
(def s2 "!!!!")
;; passed all the tests
;; (defn remove-bang [s]
;;   (str (reduce str (filter #(not= % \!) s)) "!"))
  ;; (str (apply str (filter #(not= % \!) s)) "!"))

;; also passes the tests
(defn remove-bang [s]
  (str (s/replace s "!" "") "!"))

(remove-bang s2)
(str (reduce str (filter #(not= % \!) s)) "!")

(def sg " test ")
(defn do-trimming [string]
  (.trim string))
(do-trimming sg)
(def var 43.5)
;; работает, потому что round - это статический метод класса
(Math/round var)
;; не работает, потому что это не метод инстанса, round - это метод класса
(.round var)
(round var)
(.equals 10 10)
(.hashCode 20)
(.byteValue 44)
(.floatValue 86)

(-> "  hello   "
    clojure.string/trim
    clojure.string/upper-case)

(clojure.java.browse/browse-url "/bin/bash")
(clojure.java.browse/browse-url "http://clojuredocs.org")

(clojure.string/ends-with? "104700" "0")

(def nums "104800")
(if (clojure.string/ends-with? nums "0")
  (clojure.string/replace nums "0" "")
  nums)

(-> nums
    reverse
    Integer/parseInt)

(Integer/parseInt (clojure.string/reverse (str (Integer/parseInt (clojure.string/reverse nums)))))

;; (def rev-and-int
;;   (comp
;;     Integer/parseInt
;;     clojure.string/reverse))

(def example-transducer
  (map inc))

(def transforms
  (comp
    (map inc)
    (filter even?)))

;; (def rev-and-int
;;   (comp
;;     clojure.string/upper-case
;;     clojure.string/reverse))

(def rev-and-int
  (comp #(Integer/parseInt %) clojure.string/reverse))

(-> nums
  clojure.string/reverse
  Integer/parseInt
  str
  clojure.string/reverse
  Integer/parseInt)

(-> nums
    rev-and-int
    str
    rev-and-int)


(def rev-int
  (comp
    #(Integer/parseInt %)
    clojure.string/reverse
    str))

(def nn -1049000)
;; (defn no-boring-zero [nn]
;;   (if (pos? n)
;;     (-> n rev-int rev-int)
;;     (-> n (* -1) rev-int rev-int (* -1))))

(defn no-boring-zero [nn]
  (if (pos? nn)
    (nth (iterate rev-int nn) 2)
    ;; (-> nn
    ;;   (iterate rev-int)
    ;;   (nth 2))
    (-> nn
        (* -1)
        (nth (iterate rev-int nn) 2)
        (* -1))))

(no-boring-zero nn)

;; что-то бинарное
(Integer/reverse 101) ; -1509949440

(def number -2)
(->> (- number)
     (* 2))


;; 2 short helper funcs, passes the tests
(def rev-int
  (comp
    #(Integer/parseInt %)
    clojure.string/reverse
    str))

;;(defn two-times [f n]
  ;;(nth (iterate f n) 2))

(defn two-times [f n]
  (-> n f f))

(defn no-boring-zeros [n]
  (if (pos? n)
    (->> n
      (two-times rev-int))
    (->> (- n)
      (two-times rev-int)
      -)))


;; previous option variation, passes the tests
(defn rev-int [n]
  (let [f (comp
            #(Integer/parseInt %)
            clojure.string/reverse
            str)]
    (-> n f f)))

(defn no-boring-zeros [n]
  (if (pos? n)
    (rev-int n)
    (- (rev-int (- n)))))

;; and a cool colution from Codewars with regexp
(defn no-boring-zeros [n]
  (Integer/parseInt (clojure.string/replace (str n) #"(?!^)[0]+$" "")))
;; 1. Что означают символы?
;;
;;     #"" — литерал регулярного выражения в Clojure.
;;     (?!^) — это Negative Lookahead (отрицательная опережающая проверка).
;;     Она говорит: «Найди совпадение только если оно находится не в самом начале строки».
;;         ? — начать проверку.
;;         ! — отрицание (не должно соответствовать).
;;         ^ — начало строки.
;;     [0]+ — один или более нулей.
;;     $ — конец строки.
;;
;; 2. Зачем здесь (?!^)?
;; Оно защищает строку от полного удаления, если она состоит только из одного нуля.
