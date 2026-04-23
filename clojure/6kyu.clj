;;======================================================================
;; https://www.codewars.com/kata/56dbe7f113c2f63570000b86/train/clojure

(ns sixth_kyu.core)
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


