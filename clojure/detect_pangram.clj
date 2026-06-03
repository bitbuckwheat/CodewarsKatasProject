;; https://www.codewars.com/kata/545cedaa9943f7fe7b000048


(ns Pangram
  (:require [clojure.string :as str]
            [clojure.test :refer :all]
            [clojure.set :as c-set]))

(defn pangram? [s]
  (let [lc-alphabet-set (set (map char (range 97 123)))
        lc-s (str/lower-case s)]
    (= lc-alphabet-set (c-set/intersection (set lc-s) lc-alphabet-set))))

(set (map char (range 97 123)))

(def s "Pack my box with five dozen liquor jugs.")
(set (str/lower-case s))

(deftest Tests
  (is (= (pangram? "The quick brown fox jumps over the lazy dog.") true))
  (is (= (pangram? "Pack my box with five dozen liquor jugs.") true))
  (is (= (pangram? "Not every sentence is a a pangram. An example") false)))

;; there is subset? in clojure.set lib
;;
;; (ns Pangram
;;   (:require
;;     [clojure.string :refer [lower-case]] 
;;     [clojure.set :refer [subset?]]))
;; (defn pangram?
;;   [s]
;;   (subset? 
;;     (set "abcdefghijklmnopqrstuvwxyz") 
;;     (set (lower-case s))))
