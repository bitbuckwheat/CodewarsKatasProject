;; https://www.codewars.com/kata/52c31f8e6605bcc646000082

(ns twosum
  ;; (:require [clojure.test :refer [test-vars run-tests deftest is are testing]]))
  (:require [clojure.test :refer :all]))
(require '[clojure.string :as str])

(defn twosum [numbers target]
  (let [indexed_vector (map-indexed vector numbers)]
    (->
      (for [[idx_x val_x] indexed_vector
            [idx_y val_y] indexed_vector
            :when (and
                    (= target (+ val_x val_y))
                    (not= idx_x idx_y))]
        [idx_x idx_y])
      first)))

;; doesn't work, inner let is the reason
;; (defn twosum [numbers target]
;;   (->
;;      (for [[[idx_x val_x] :as x] (map-indexed vector numbers)
;;            :let [[idx_y val_y] x]
;;            :when (and
;;                    (= target (+ val_x val_y))
;;                    (not= idx_x idx_y))]
;;        [idx_x idx_y])
;;      first))

(deftest twosum-example-tests
  (is (= (sort < (twosum '[1 2 3] 4)) '[0 2]))
  (is (= (sort < (twosum '[1234 5678 9012] 14690)) '[1 2]))
  (is (= (sort < (twosum '[2 2 3] 4)) '[0 1])))

(for [x [1 2 3]
      y [1 2 3]]
  (filter (= 4 (+ x y)) [x y]))

(for [x [1 2 3]
      y [1 2 3]]
  (->>
    [x y]
    (filter (fn [pair] (= 4 (+ (first pair) (second pair)))))))

(for [x [1 2 3]
      y [1 2 3]
      :when (= 4 (+ x y))]
  [x y])

(for [x (map-indexed vector [2 2 3])
      y (map-indexed vector [2 2 3])
      :when (= 4 (+ (second x) (second y)))]
  [x y])

(for [x (map-indexed vector [1 2 3])
      y x
      :when (= 4 (+ (second x) (second y)))]
  [x y])

(def i
  (first (for [x (map-indexed vector [2 2 3])
               y (map-indexed vector [2 2 3])
               :when (= 4 (+ (second x) (second y)))]
            [x y])))

(defn destr [[[idx1 _] [idx2 _]]]
  [idx1 idx2])

(destr i)

(map-indexed hash-map [1 2 3]) ; ({0 1} {1 2} {2 3})
(map-indexed vector [1 2 3]) ; [1 2 3]
(map-indexed list [1 2 3]) ; [1 2 3]

(filter even? [1 2 3 4])
