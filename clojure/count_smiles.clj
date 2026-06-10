;; https://www.codewars.com/kata/583203e6eb35d7980400002a


(ns smiley
   (:require [clojure.test :refer :all]))


(defn count-smileys [arr]
  (if (empty? arr)
    0
    (:c (reduce (fn [acc smile]
                    (if (re-find #"[:;][-~]?[)D]" smile)
                        (assoc acc :c (inc (get acc :c)))
                        acc))
                {:c 0}
                arr))))

;; second version, a bit simpler, no if and no map acc as well
(defn count-smileys [arr]
  (reduce (fn [acc smile]
              (if (re-find #"[:;][-~]?[)D]" smile)
                  (assoc acc :c (inc (get acc :c)))
                  acc))
          0
          arr))


(deftest example-teste
  (is (= (count-smileys [])  0))
  (is (= (count-smileys [":)"  ";("  ";}"  ":-D"])  2))
  (is (= (count-smileys [";]"  ":["  ";*"  ":$"  ";-D"])  1))
  (is (= (count-smileys [":)" ":(" ":D" ":O" ":;"])  2))
  (is (= (count-smileys [":-)" ";~D" ":-D" ":_D"])  3)))

