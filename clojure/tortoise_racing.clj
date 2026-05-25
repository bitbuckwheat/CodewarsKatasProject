(ns tortoise.core
  (:require [clojure.test :refer :all]
            [clojure.math :as math]))


;; v1*t2 + g = v2*t2
;; g = v2*t2 - v1*t2
;; g = t2 * (v2 - v1)
;; t2 = g/(v2 - v1)

(def v1 720)
(def v2 850)
(def g 70)

(defn f [[number]]
  [number (math/floor-div (* 60 g) (last number))])

(defn race [v1 v2 g]
  (let [diff (- v2 v1)]
    [(math/floor-div g diff)
     (math/floor-div (* 60 g) diff)]))


(race v1 v2 g)

(deftest a-test1
  (testing "Basic tests"
   (is (= (race 720 850 70) [0 32 18]))
   (is (= (race 80 91 37) [3 21 49]))
   (is (= (race 80 100 40) [2 0 0]))))

