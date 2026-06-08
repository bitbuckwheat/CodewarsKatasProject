;; https://www.codewars.com/kata/56a5d994ac971f1ac500003e


(ns longest-consec.core
  (:require [clojure.test :refer :all]))

(defn longest-cons [strarr k]
  ;; (first (reduce (fn [a i] (if ())) [] (loop [c (count strarr)])))
  (if (or (empty? strarr) (> k (count strarr)) (>= 0 k))
    ""
    (first
      (second
        (last
          (sort-by first
                    (group-by count
                              (loop [c (count strarr)
                                     acc []
                                     s strarr]
                                (if (> k c)
                                    acc
                                    (recur (dec c)
                                           (conj acc (apply str (take k s)))
                                           (rest s)))))))))))

(defn longest-cons [strarr k]
  ;; (first (reduce (fn [a i] (if ())) [] (loop [c (count strarr)])))
  (if (or (empty? strarr) (> k (count strarr)) (>= 0 k))
    ""
    (apply max-key count (loop [c (count strarr)
                                acc []
                                s strarr]
                           (if (> k c)
                               acc
                               (recur (dec c)
                                      (conj acc (apply str (take k s)))
                                      (rest s)))))))

(take 2 [1 2 3])
(def strarr ["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"])
(def k 2)
(conj [1 2 3] 4)
(conj (apply str (take 2 ["ab" "cd" "ef"])))

(longest-cons strarr k)

(defn test-assert [act exp]
  (is (= act exp)))
(deftest a-test1
  (testing "longest-cons"
    (test-assert(longest-cons ["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2), "abigailtheta")
    (test-assert(longest-cons ["ejjjjmmtthh", "zxxuueeg", "aanlljrrrxx", "dqqqaaabbb", "oocccffuucccjjjkkkjyyyeehh"], 1), "oocccffuucccjjjkkkjyyyeehh")
    (test-assert(longest-cons [], 3), "")
    (test-assert(longest-cons ["itvayloxrp","wkppqsztdkmvcuwvereiupccauycnjutlv","vweqilsfytihvrzlaodfixoyxvyuyvgpck"], 2), "wkppqsztdkmvcuwvereiupccauycnjutlvvweqilsfytihvrzlaodfixoyxvyuyvgpck")
    (test-assert(longest-cons ["wlwsasphmxx","owiaxujylentrklctozmymu","wpgozvxxiu"], 2), "wlwsasphmxxowiaxujylentrklctozmymu")
    (test-assert(longest-cons ["zone", "abigail", "theta", "form", "libe", "zas"], -2), "")
    (test-assert(longest-cons ["it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"], 3), "ixoyx3452zzzzzzzzzzzz")
    (test-assert(longest-cons ["it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"], 15), "")
    (test-assert(longest-cons ["it","wkppv","ixoyx", "3452", "zzzzzzzzzzzz"], 0), "")))

;; my solution is very long, this one is way more shorter 
;; (defn longest-cons [strarr k]
;;   (reduce #(if (> (count %2) (count %1)) %2 %1) ""
;;     (map #(apply str %) (partition k 1 strarr))))
;;
;;
;;and this one as well is better and shorter
;; (defn longest-cons [strarr k]
;;  (->> strarr
;;       (partition k 1)
;;       (map (partial apply str))
;;       reverse
;;       (apply max-key count "")))
