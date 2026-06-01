;; https://www.codewars.com/kata/541c8630095125aba6000c00


(ns digital-root
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))

(defn digital-root [n]
  (if (> 10 n)
    [n]
    (recur (reduce + (map #(Integer/parseInt %) (str/split (str n) #""))))))

;; as-> version
(defn digital-root [n]
  (if (> 10 n)
    n
    (as-> (str n) x
      (str/split x #"")
      (map #(Integer/parseInt %) x)
      (reduce + x)
      (recur x))))

(reduce + (map #(Integer/parseInt %) (str/split (str n) #"")))
(str/split "abc" #"")

(def n 123)

(digital-root n)

