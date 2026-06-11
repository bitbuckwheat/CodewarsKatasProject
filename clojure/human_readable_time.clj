;; https://www.codewars.com/kata/52685f7382004e774f0001f7

(ns HumanTime
  (:require [clojure.test :refer :all]))

(defn human-readable [x]
  (clojure.string/join
    ":"
    (map (fn [n] (format "%02d" n))
         (:result
          (reduce (fn [acc divisor]
                    (let [current-sec (:current acc)]
                      {:result (conj (:result acc) (quot current-sec divisor))
                       :current (rem current-sec divisor)}))
                  {:result []
                    :current x}
                  [3600 60 1])))))

(defn human-readable [x]
  (clojure.string/join
    ":"
    (:result
      (reduce (fn [acc divisor]
                (let [current-sec (:current acc)]
                  {:result (conj (:result acc)
                                 (format "%02d" (quot current-sec divisor)))
                   :current (rem current-sec divisor)}))
              {:result []
               :current x}
              [3600 60 1]))))

(human-readable 3601)
(:0 [1 2 3])
(reduce str [1 2 3])

;;     (let [total-sec (quot (* g 3600) (- v2 v1))]
;;
;;       ;; Запускаем reduce по списку делителей
;;       (:result
;;        (reduce (fn [acc divisor]
;;                  (let [current-sec (:current acc)]
;;                    {:result  (conj (:result acc) (quot current-sec divisor))
;;                     :current (rem current-sec divisor)}))
;;                {:result [] :current total-sec} ; Начальное состояние
;;                [3600 60 1])))))                ; Список делителей



(deftest Tests
  (is (= (human-readable      0) "00:00:00"))
  (is (= (human-readable     59) "00:00:59"))
  (is (= (human-readable     60) "00:01:00"))
  (is (= (human-readable     90) "00:01:30"))
  (is (= (human-readable  86399) "23:59:59"))
  (is (= (human-readable 359999) "99:59:59")))
