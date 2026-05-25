(ns split-str.core
  (:require [clojure.test :refer :all]
            [clojure.string :as str]))


;; (def s "abc")
(def s "abcdef")

(defn solution [s]
  ;; (map #(apply str %) (partition 2 2 ["_"] s)) ;; worked out
  (map #(apply str %) (partition 2 2 "_" s))) ;; a bit simpler

(str \a \b)

(solution s)

(deftest solution-test
  (is (= (solution "abc") ["ab" "c_"]))
  (is (= (solution "abcdef") ["ab" "cd" "ef"])))
