;; https://www.codewars.com/kata/54b42f9314d9229fd6000d9c


(ns kata.core
  (:require [clojure.test :refer :all]))

(defn encode-dups [text]
  (let [lc-text (clojure.string/lower-case text)
        fr (frequencies lc-text)]
    (apply str (map #(if (> (get fr %) 1) ")" "(")
                    lc-text))))

(encode-dups "text")

(frequencies "text")
((keyword \t) (frequencies "text"))
(map key (frequencies "text"))
(keyword \t)
(get (frequencies "text") \t)

(defn dotest [text expected]
  (is (= (kata.core/encode-dups text) expected)))

(deftest sample
  (testing "basic"
    (dotest "din" "(((")
    (dotest "recede" "()()()")
    (dotest "(( @" "))((")
    (dotest "ABC" "(((")
    (dotest "AaBbC" "))))("))
  (testing "ignore-case" (dotest "Success" ")())())")))


