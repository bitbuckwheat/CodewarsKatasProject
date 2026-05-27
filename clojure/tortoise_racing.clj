;; https://www.codewars.com/kata/55e2adece53b4cdcb900006c/

(ns tortoise.core
  (:require [clojure.test :refer :all]
            [clojure.math :as math]))


;; v1*t2 + g = v2*t2
;; g = v2*t2 - v1*t2
;; g = t2 * (v2 - v1)
;; t2 = g/(v2 - v1)

(def v1 80)
(def v2 91)
(def g 37)

(defn f [[number]]
  [number (math/floor-div (* 60 g) (last number))])

;; (defn race [v1 v2 g]
;;   (let [diff (- v2 v1)]
;;     [(math/floor-div g diff)
;;      (math/floor-div (* 60 g) diff)]))

(defn race [v1 v2 g]
  (let [diff (- v2 v1)
        fir (float (/ g diff))
        sec (- fir (int fir))
        third (* 60 sec)
        fourth (- third (int third))
        fifth (* 60 fourth)]
    [(int fir) (int third) (int fifth)]))

(rem 11 3)
(quot 70 130)
(rem 70 130)
(math/floor-mod 70 130)
(def diff (- v2 v1))
(float (/ g diff)) ; 3.3636363
(int (* 3600 (float (/ g diff)))) ; 12109
(quot (int (* 3600 (float (/ g diff)))) 3600) ; 12109
(rem (int (* 3600 (float (/ g diff)))) 3600) ; 1309
(quot (rem (int (* 3600 (float (/ g diff)))) 3600) 60) ; 21
(rem (rem (int (* 3600 (float (/ g diff)))) 3600) 60) ; 49
(int (* 3600 (/ g diff))) ; 12109
(* 3600 (/ g diff)) ; 133200/11
(quot g diff)
(rem g diff)
(quot (* 3600 (/ g diff)))

(quot (int (* 3600 (/ g diff)))) 3600
(quot (int (* 3600 (/ g diff))) 3600)

(defn helper [number]
  [(int number)])

(defn race [v1 v2 g]
  (if (> v1 v2)
    nil
    (let [result-in-seconds (quot (* 3600 g) (- v2 v1))
          mins-and-secs (rem result-in-seconds 3600)]
      [(quot result-in-seconds 3600)
       (quot mins-and-secs 60)
       (rem mins-and-secs 60)])))

(defn race [v1 v2 g]
  (if (> v1 v2)
    nil
    (let [time-in-secs (quot (* 3600 g) (- v2 v1))]
      [(quot time-in-secs 3600)
       (quot (rem time-in-secs 3600) 60)
       (rem (rem time-in-secs 3600) 60)])))


(race v1 v2 g)

(deftest a-test1
  (testing "Basic tests"
   (is (= (race 720 850 70) [0 32 18]))
   (is (= (race 80 91 37) [3 21 49]))
   (is (= (race 80 100 40) [2 0 0]))))

;; ai reduce solution
;;
;; (defn race [v1 v2 g]
;;   (if (> v1 v2)
;;     nil
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


