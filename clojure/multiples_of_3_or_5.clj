;; https://www.codewars.com/kata/514b92a657cdc65150000006

(ns multiples
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(defn multiple-of-3-or-5? [number]
  (or (= 0 (rem number 3)) (= 0 (rem number 5))))

(rem 9 3)
(multiple-of-3-or-5? 11)

(defn solution [number]
  (apply + (filter multiple-of-3-or-5? (range 1 number))))

(solution 10)


(deftest test-multiples-of-3-and-5
  (is (= 23 (solution 10))))


;; https://www.codewars.com/kata/5264d2b162488dc400000001
;; Stop gninnipS My sdroW!


(def strng "Hey fellow warriors")

;; (defn spin-words [strng]
;;   (str/join " " (map (fn [word]
;;                          (if (< 4 (count word))
;;                            (str/reverse word)
;;                            word))
;;                      (str/split strng #" "))))

(defn spin-words [strng]
  (as-> strng x
    (str/split x #" ")
    (map (fn [word] (if (< 4 (count word)) (str/reverse word) word)) x)
    (str/join " " x)))

(str/reverse "hello")
(def words "Hey fellow warriors")
(spin-words words)

"Hey fellow warriors"  --> "Hey wollef sroirraw" 
"This is a test        --> "This is a test") 
"This is another test" --> "This is rehtona test"
