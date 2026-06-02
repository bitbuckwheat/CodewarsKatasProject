;; https://www.codewars.com/kata/526571aae218b8ee490006f4


(ns clojure.bit-counting
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(defn count-bits [n]
    0)

(bit-shift-left 0 1)
(apply reduce +
       (map #(Integer/parseInt %) (str/split (Long/toBinaryString 1234) #"")))

(defn count-bits [n]
  (as-> (Long/toBinaryString n) x
      (str/split x #"")
      (map #(Integer/parseInt %) x)
      (reduce + x)))

;; the shortest solution
;; (defn count-bits [n]
;;   (Integer/bitCount n))

(map #(Integer/parseInt %) (Long/toBinaryString 1234))
(Integer/parseInt "1")

(defn do-test [n expected]
  (let [actual (count-bits n)]
    (testing (str "for n = " n "\n") (is (= actual expected)))))

(deftest sample-tests
    (do-test 0 0
     (do-test 1 1)
     (do-test 1234 5)
     (do-test 2048 1)
     (do-test 2047 11)))
