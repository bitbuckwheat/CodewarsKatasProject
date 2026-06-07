;; https://www.codewars.com/kata/56b5afb4ed1f6d5fb0000991


(ns revrot.core)
(require '[clojure.string :as str])
(require '[clojure.test :refer :all])

(defn revrot [strng sz]
  (map (fn [l] (reduce (fn [d] (Integer. d)) l)) (partition sz strng)))

(def s "1234569876541")
(def z 6)
(revrot s z)



(defn test-assert [act exp]
  (is (= act exp)))

(deftest a-test1
  (testing "revrot"
    (test-assert(revrot "123456987654" 6) "234561876549")
    (test-assert(revrot "1234" 0) "")
    (test-assert(revrot  "" 0) "")
    (test-assert(revrot "1234", 5), "")
    (test-assert(revrot "733049910872815764", 5), "330479108928157")))

