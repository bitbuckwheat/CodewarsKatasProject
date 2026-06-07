;; https://www.codewars.com/kata/550498447451fbbd7600041c


(ns aretheysame.core
  (:require [clojure.test :refer :all]))

(defn compSame [a b]
  (if (not-any? nil? [a b])
    (= (sort b) (sort (map #(* % %) a)))
    false))

(empty? nil)


(deftest a-test1
  (testing "Test 1"
    (def ur [121, 144, 19, 161, 19, 144, 19, 11])
    (def vr [121, 14641, 20736, 361, 25921, 361, 20736, 361])
    (is (= (compSame ur vr) true))))

(map-indexed vector "012345")
;; 135024
