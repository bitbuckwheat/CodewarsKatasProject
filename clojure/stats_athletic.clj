;; https://www.codewars.com/kata/55b3425df71c1201a800009c


(ns stat.core
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(def stat "01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17")
(defn stat [strg])

(let [st (sort (map (fn [[h m s]] (reduce + (map #(* %1 %2)
                                              [3600 60 1]
                                              (map #(Integer/parseInt %) [h m s]))))
                 (map #(str/split % #"\|") (str/split stat #", "))))
      r (- (last st) (first st))
      a (int (/ (reduce + st) (count st)))
      m (nth st 2)]
  (str "Range: " r " Average: " a " Median: " m))

(apply * [2 3 4]) ; 24
;; (* [2 3 4])
(map #(* %1 %2) [2 3 4] [3 4 5])


(deftest a-test1
  (testing "2 basic tests"
    (is (= (stat "01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17") 
           "Range: 01|01|18 Average: 01|38|05 Median: 01|32|34"))
    (is (= (stat "02|15|59, 2|47|16, 02|17|20, 2|32|34, 2|17|17, 2|22|00, 2|31|41") 
           "Range: 00|31|17 Average: 02|26|18 Median: 02|22|00"))))
