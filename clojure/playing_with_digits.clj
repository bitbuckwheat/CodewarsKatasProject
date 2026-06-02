;; https://www.codewars.com/kata/5552101f47fc5178b1000050


(ns play-digits.core
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(defn dig-pow [n p]
  (let [x (map #(Integer/parseInt %) (str/split (str n) #""))
        y (range p (+ p (count x)))
        p (reduce + (map #(reduce * (take %2 (repeat %1))) x y))
        c (zero? (rem p n))
        r (int (quot p n))]
    (if (= c true)
      r
      -1)))

(take 2 (iterate * 3))
(reduce * (take 3 (repeat 3)))

(def x (map #(Integer/parseInt %) (str/split (str n) #"")))
(def y (range p (+ p (count x))))
(zero? (rem (reduce + (map #(math/pow %1 %2) x y)) n))
(int (quot (reduce + (map #(math/pow %1 %2) x y)) n))
(def n 695)
(def p 2)


(deftest a-test1
  (testing "Test1"
    (is (= (dig-pow 89 1) 1))))
(deftest a-test2
  (testing "Test2"
    (is (= (dig-pow 92 1) -1))))
