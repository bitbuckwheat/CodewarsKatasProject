;; https://www.codewars.com/kata/56b5afb4ed1f6d5fb0000991


(ns revrot.core)
(require '[clojure.string :as str])
(require '[clojure.test :refer :all])

;; (defn revrot [strng sz]
;;   (cond
;;     (or (>= 0 sz)
;;         (empty? strng)
;;         (> sz (count strng))) ""
;;     :else (apply str (map #(reduce str %)
;;                           (map #(if (= 0 (rem (reduce + %) 2))
;;                                   (vec (reverse %))
;;                                   (conj (vec (rest %)) (first %)))
;;                                (partition sz (map #(Integer/parseInt %)
;;                                                   (str/split strng #""))))))))

(defn revrot [strng sz]
  (cond
    (or (>= 0 sz)
        (empty? strng)
        (> sz (count strng))) ""
    :else (->>
            (str/split strng #"")
            (map #(Integer/parseInt %))
            (partition sz)
            (map #(if (even? (reduce + %))
                      (vec (reverse %))
                      (conj (vec (rest %)) (first %))))
            flatten
            (str/join ""))))
            ;; (map #(reduce str %))
            ;; (apply str))))

(def s "1234569876541")
(def z 6)
(revrot s z)
(Integer/parseInt \1)
(Integer. \1)
(map #(Integer/parseInt %) (str/split "123" #""))
(partition z (map #(Integer/parseInt %) (str/split s #"")))
(map #(reduce + %) (partition z (map #(Integer/parseInt %) (str/split s #""))))
(conj [1 2 3] 4) ; [1 2 3 4]
(first [1 2 3])

(conj (vec (rest '(1 2 3))) (first '(1 2 3)))
(vec (rest '(1 2 3)))

(rem 308 2)



(defn test-assert [act exp]
  (is (= act exp)))

(deftest a-test1
  (testing "revrot"
    (test-assert(revrot "123456987654" 6) "234561876549")
    (test-assert(revrot "1234" 0) "")
    (test-assert(revrot  "" 0) "")
    (test-assert(revrot "1234", 5), "")
    (test-assert(revrot "733049910872815764", 5), "330479108928157")))

