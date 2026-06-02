;; https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1


(ns katas.counting-duplicates
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

;; (defn duplicate-count [text]
;;   (count (filter (fn [[_ f]] (> f 1)) (frequencies (str/lower-case text)))))

(defn duplicate-count [text]
  (->> (str/lower-case text)
       frequencies
       (filter (fn [[_ f]] (> f 1)))
       count))

(def text "abcde")
(duplicate-count text)

(deftest sample-tests
  (testing "blank"
    (is (= 0 (duplicate-count ""))))
  (testing "abcde"
    (is (= 0 (duplicate-count "abcde"))))
  (testing "aabbcde"
    (is (= 2 (duplicate-count "aabbcde"))))
  (testing "ignore-case"
    (is (= 2 (duplicate-count "aabBcde"))))
  (testing "Indivisibility"
    (is (= 1 (duplicate-count "Indivisibility")))))



;; https://www.codewars.com/kata/54e6533c92449cc251001667
;; Unique In Order

(def x "ABC")
(def y "AABCC")
(def z [1 2 2 3])
(defn unique-in-order [input]
  (println input)
  ;; peek instead of last is much faster for large vectors
  (reduce (fn [acc i] (if (not= i (last acc))
                        (conj acc i)
                        acc)) [] input))

(unique-in-order x)
(unique-in-order y)
(unique-in-order z)


;; but the shortest solutions are
;; Returns a lazy sequence removing consecutive duplicates in coll.
;; Returns a transducer when no collection is provided.
;;
;; (defn unique-in-order [input]
  ;; (dedupe input))
;;
;; (def unique-in-order dedupe)
