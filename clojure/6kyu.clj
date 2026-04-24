;;======================================================================
;; https://www.codewars.com/kata/56dbe7f113c2f63570000b86/train/clojure

(ns sixth_kyu.core
  (:require [clojure.test :refer [test-vars run-tests deftest is are]]))
(require '[clojure.string :as str])

(def s "abcd\nefgh\nijkl\nmnop")

(defn rot [strng]
  (->> (str/split strng #"\n")
       (map #(str/reverse %))
       reverse))

(defn selfie-and-rot [s]
  (let [ss (str/split s #"\n")
        sss (reverse (map #(str/reverse %) ss))
        c (count (first ss))
        dots (apply str (repeat c "."))]
    (concat
       (map #(str % dots) ss)
       (map #(str dots %) sss))))

(defn oper [fct s]
  (str/join "\n" (fct s)))

(take 5 (repeat "."))
(apply str (take 5 (repeat ".")))
(def ss (str/split s #"\n"))
(map #(str % (apply str (take (count (first ss)) (repeat ".")))) ss)
(conj ["ab" "cd"] ["ef" "gh"])
(cons ["ab" "cd"] ["ef" "gh"])
(list ["ab" "cd"] ["ef" "gh"])
(into ["ab" "cd"] ["ef" "gh"]) ; ["ab" "cd" "ef" "gh"]
(into '("a" "b") '("c" "d")) ; ("d" "c" "a" "b")
(concat '("a" "b") '("c" "d"))

(oper rot s)
(oper selfie-and-rot s)

;;====================================================================== 
;; https://www.codewars.com/kata/5a9c35e9ba1bb5c54a0001ac

;; (defn add [x y]
;;   (Integer/sum x y))

(require '[clojure.math :as m])

(defn plus [x y]
  ;; (+ x y))
  (m/add-exact x y))

(defn add [x y]
  (plus x y))

(deftest sample-tests
  (is (= 24 (add 5 19)))
  (is (= -9 (add -27 18)))
  (is (= -30 (add -14 -16)))
  (is (= 10 (add 0 10))))

Exactly. You are essentially telling Clojure: "Whenever I say add, I really mean unchecked-add."
In technical terms, you are binding the Var add to the value of the Var unchecked-add (which is the function itself).
Important distinction: def vs defn
When you use def like this, you aren't creating a new function "wrapper"; you are pointing directly to the existing one.
Direct Alias (def):
(def add unchecked-add)
Calling (add 1 2) goes straight to the unchecked-add code. This is very fast.
Wrapper Function (defn):
(defn add [x y] (unchecked-add x y))
This creates a new function that calls unchecked-add inside it. This adds a tiny bit of overhead because you’re adding an extra step to the process.

