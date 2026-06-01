;; https://www.codewars.com/kata/525f50e3b73515a6db000b83


(ns clojure.phone)

(defn create-phone-number [nums]
  (let [[n1 n2 n3 n4 n5 n6 n7 n8 n9 n10] nums]
    (str "(" n1 n2 n3 ") " n4 n5 n6 "-" n7 n8 n9 n10)))

(def nums [1 2 3])
(create-phone-number nums)
