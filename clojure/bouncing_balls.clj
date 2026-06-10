;; https://www.codewars.com/kata/5544c7a5cb454edb3c000047


(ns bouncing-balls.core
  (:require [clojure.test :refer :all]
            [clojure.math :as math]))

;; working!
(defn bouncing-balls [h bounce window]
  (if (or (>= 0 h) (or (neg? bounce) (>= bounce 1)) (>= window h))
    -1
    (loop [bh (* h bounce)
           c 1]
      (cond
        (> bh window) (recur (* bh bounce) (+ c 2))
        (= bh window) (recur (* bh bounce) (inc c))
        (<= bh window) c))))

;; working!
(defn bouncing-balls [h bounce window]
  (if (or (>= 0 h) (or (neg? bounce) (>= bounce 1)) (>= window h))
    -1
    (loop [bh (* h bounce)
           c 1]
      (cond
        (<= bh window) c
        (> bh window) (recur (* bh bounce) (+ c 2))))))

;; works, but too long as well
;; (defn bouncing-balls [h bounce window]
;;   (if (or (>= 0 h) (and (neg? bounce) (> bounce 1)) (>= window h))
;;     -1
;;     ;; (math/ceil (/ (math/log (/ window h)) (math/log bounce)))))
;;    (+ 1 (* 2 (count (rest (take-while #(< window %) (iterate #(* % bounce) h))))))))

;; works good, look the conditionals!!!
(defn bouncing-balls [h bounce window]
  (if (or (>= 0 h) (or (neg? bounce) (>= bounce 1)) (>= window h))
    -1
   (dec (* 2 (count (take-while #(< window %) (iterate #(* % bounce) h)))))))


;; working!
(defn bouncing-balls [h bounce window]
  (if (or (>= 0 h)
          (or (neg? bounce) (>= bounce 1))
          (>= window h))
    -1
    (->> h
         (iterate #(* % bounce))
         (take-while #(< window %))
         count
         (* 2)
         dec)))

;; probably the best solution, at least the shortest
(defn bouncing-balls [h bounce window]
  (if (or (>= 0 h)
          (not (< 0 bounce 1))
          (>= window h))
    -1
    (->> h
         (iterate #(* % bounce))
         (take-while #(< window %))
         count
         (* 2)
         dec)))

(+ 1 (count (take-while #(< 1.5 %) (iterate #(* % 0.66) 3))))

(bouncing-balls 3 0.66 1.5)

(deftest a-test1
  (testing "Test 1"
    (def rr 3)
    (is (= (bouncing-balls 3 0.66 1.5) rr))))
(deftest a-test2
  (testing "Test 2"
    (def rr 15)
    (is (= (bouncing-balls 30 0.66 1.5) rr))))
