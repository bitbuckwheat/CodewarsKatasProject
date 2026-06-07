;; https://www.codewars.com/kata/54dc6f5a224c26032800005c


(ns bookseller.core
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(defn stock-list [list-of-books list-of-cat]
  (if (or (empty? list-of-books) (empty? list-of-cat))
    []
    (let [list-of-cat-map (init-acc list-of-cat)]
      (->>
        (map #(str/split % #" ") list-of-books)
        (map (fn [[code number]] [(str (first code)) (Integer/parseInt number)]))
        (filter (fn [[category _]] (contains? list-of-cat-map category)))
        (reduce (fn [acc [category number]]
                    (assoc acc category (+ (get acc category 0) number)))
                list-of-cat-map)
        (into [])))))
    ;; (map vector)))

(def u ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"])
(def v ["A" "B" "C" "W"])
(stock-list u v)
(map vector
  {"A" 0, "B" 1290, "C" 515, "D" 600})
(contains? ["A" "B"] "A")

(def a (init-acc v))
a
(assoc a "B" 100)
(def b (update a "B" - 25))
(get b "B")

(map #(str/split % #" ") u)
(reduce (fn [acc [cat num]] (init-acc v)))
(defn init-acc [v]
  (apply conj (map (fn [letter] {letter 0}) v)))
(init-acc v)

(deftest a-test1
  (testing "Test 1"
    (def ur ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"])
    (def vr ["A" "B" "C" "D"])
    (def res [["A" 0] ["B" 1290] ["C" 515] ["D" 600]])
    (is (= (stock-list ur vr) res))))
