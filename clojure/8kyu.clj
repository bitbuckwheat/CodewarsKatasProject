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
