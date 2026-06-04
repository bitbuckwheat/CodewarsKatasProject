;; https://www.codewars.com/kata/578aa45ee9fd15ff4600090d


(ns sort-odd
  (:require [clojure.test :refer :all]))

(defn sort-array [xs]
  (if (= [] xs)
    []
    (let [xs-indexed (map-indexed vector xs)
          even-indexed (filter (fn [[idx itm]] (even? itm)) xs-indexed)
          odd-indexed (filter (fn [[idx itm]] (odd? itm)) xs-indexed)]
      (->> odd-indexed
        (map (fn [[_ itm]] itm))
        sort
        (map (fn [[idx _] itm] [idx itm]) odd-indexed)
        (concat even-indexed)
        sort
        (mapv (fn [[_ itm]] (conj itm)))))))

(sort-array xs)

(map-indexed vector xs) ; ([0 5] [1 3] [2 2] [3 8] [4 1] [5 4])
(sort (map-indexed vector xs))
(filter (fn [[idx itm]] (even? itm)) (map-indexed vector xs))
(def i (filter (fn [[idx itm]] (even? itm)) (map-indexed vector xs)))
(filter (fn [[idx itm]] (odd? itm)) (map-indexed vector xs))
(def x (filter (fn [[idx itm]] (odd? itm)) (map-indexed vector xs)))
(sort (map (fn [[_ itm]] itm) (filter (fn [[idx itm]] (odd? itm)) (map-indexed vector xs))))
(def y (sort (map (fn [[_ itm]] itm) (filter (fn [[idx itm]] (odd? itm)) (map-indexed vector xs)))))
(map (fn [[idx _] itm] [idx itm]) x y)
(def z (map (fn [[idx _] itm] [idx itm]) x y))
(sort (conj z i))
(conj z i)
(concat z i)
(sort (concat z i))
(mapv (fn [[_ itm]] (conj itm)) (sort (concat z i)))

(def xs [5 3 2 8 1 4])

(defn sort-test [input output]
  (is (= (sort-array input) output)))

(deftest sort-odd-test
  (sort-test [] [])
  (sort-test [5 3 2 8 1 4] [1 3 2 8 5 4])
  (sort-test [5 3 1 8 0] [1 3 5 8 0]))
