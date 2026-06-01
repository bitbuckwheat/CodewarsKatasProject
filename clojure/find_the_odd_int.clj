;; https://www.codewars.com/kata/54da5a58ea159efa38000836


(ns find-the-odd-int
  (:require [clojure.test :refer :all]))

(defn find-odd [xs]
  (ffirst (filter (fn [[n f]] (odd? f)) (frequencies xs))))


(find-odd data)

(def data [20 1 -1 2 -2 3 3 5 5 1 2 4 20 4 -1 -2 5])
(frequencies data)

(deftest example-tests
  (are [xs answer] (= (find-odd xs) answer)
    [20 1 -1 2 -2 3 3 5 5 1 2 4 20 4 -1 -2 5] 5
    [1 1 2 -2 5 2 4 4 -1 -2 5] -1
    [20 1 1 2 2 3 3 5 5 4 20 4 5] 5
    [10] 10
    [1 1 1 1 1 1 10 1 1 1 1] 10
    [5 4 3 2 1 5 4 3 2 10 10] 1))

