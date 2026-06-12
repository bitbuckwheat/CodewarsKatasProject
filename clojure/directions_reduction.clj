;; https://www.codewars.com/kata/550f22f4d758534c1100025a


(ns dir-reduc.core
  (:require [clojure.test :refer :all]))

(defn dirReduc [arr]
  (loop [acc []
         s arr]
    (if (empty? s)
      (vec acc)
      (if (or
            (and (= (last acc) "NORTH") (= (first s) "SOUTH"))
            (and (= (last acc) "SOUTH") (= (first s) "NORTH"))
            (and (= (last acc) "WEST") (= (first s) "EAST"))
            (and (= (last acc) "EAST") (= (first s) "WEST")))
        (recur (pop acc) (subvec s 1))
        (recur (conj acc (first s)) (subvec s 1))))))

(defn dirReduc [arr]
  (loop [acc '()
         s (seq arr)]
    (if (empty? s)
      (if (not (empty? acc))
        (vec acc))
      (if (or
            (and (= (last acc) "NORTH") (= (first s) "SOUTH"))
            (and (= (last acc) "SOUTH") (= (first s) "NORTH"))
            (and (= (last acc) "WEST") (= (first s) "EAST"))
            (and (= (last acc) "EAST") (= (first s) "WEST")))
        (recur (butlast acc) (rest s))
        (recur (concat acc (list (first s))) (rest s))))))


(let [res (reduce (fn [acc s] (if (or
                                   (and (= (last acc) "NORTH") (= s "SOUTH"))
                                   (and (= (last acc) "SOUTH") (= s "NORTH"))
                                   (and (= (last acc) "WEST") (= s "EAST"))
                                   (and (= (last acc) "EAST") (= s "WEST")))
                               (pop acc)
                               (conj acc s)))
                 []
                 arr)]
  (if (not (empty? res))
    res))

(defn dirReduc [arr]
  (let [opposites {"NORTH" "SOUTH"
                   "SOUTH" "NORTH"
                   "WEST" "EAST"
                   "EAST" "WEST"}
        res (reduce
              (fn [acc move]
                  (if (= (last acc) (opposites move))
                    (pop acc)
                    (conj acc move)))
                []
                arr)]
    (if (not (empty? res))
      res)))

(when [2]
  1)

(conj nil "s" "t")
(def arr ["NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"])
(partition 2 2 nil arr)
(dirReduc arr)
(seq [1 2 3])
(conj [] "a" "b")
(drop-last [1 2 3])
(rest [1 2 3])
(def c '(1 2 3))
(drop 1 '(1 2 3))
(drop 1 c)
(butlast '(1 2 3))
(concat '() (list (first '(2 3 4))))
(first '(1 2 3))


(deftest a-test1
  (testing "Test 1"
    (def ur ["NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"])
    (def vr ["WEST"])
    (is (= (dirReduc ur) vr))))
