;; https://www.codewars.com/kata/57814d79a56c88e3e0000786


(ns alternate-split.kata
  (:require [clojure.test :refer :all]))


;; (defn encrypt [st n]
;;   (if (or (> 0 n) (empty? st))
;;     st
;;     (apply str (nth (iterate (fn [s] (concat (y s) (x s))) st) n))))

(defn encrypt [st n]
  (if (or (neg? n) (empty? st))
    st
    (let [step (fn [s]
                 (let [even-idxs (take-nth 2 s)
                       odd-idxs (take-nth 2 (rest s))]
                   (concat odd-idxs even-idxs)))]
       (apply str (nth (iterate step st) n)))))

(count (take-nth 2 "conca"))
(interleave "con" "cat")
(map #(char %) "text")
(subvec (mapv char "hskt svr neetn!Ti aai eyitrsig") 0 (count (take-nth 2 "hskt svr neetn!Ti aai eyitrsig"))) ; (\t \e \x \t)
(def od (subvec (mapv char "hskt svr neetn!Ti aai eyitrsig") 0 (count (take-nth 2 "hskt svr neetn!Ti aai eyitrsig")))) ; (\t \e \x \t)
(subvec (mapv char "hskt svr neetn!Ti aai eyitrsig"))
(def ev (subvec (mapv char "hskt svr neetn!Ti aai eyitrsig")
               (count (take-nth 2 "hskt svr neetn!Ti aai eyitrsig"))))

(apply str (interleave ev od))

(defn decrypt [st n]
  (if (or (neg? n) (empty? st))
    st
    (let [step (fn [s]
                 (let [odds (subvec (mapv char s)
                                    0
                                    (count (take-nth 2 s)))
                       evens (subvec (mapv char s)
                                     (count (take-nth 2 s)))]
                   (if (= (count odds) (count evens))
                     (interleave evens odds)
                     (conj (interleave odds evens)
                           (last odds)))))]
       (apply str (nth (iterate step st) n)))))

(mapv char "text") ; [\t \e \x \t]
(mapv char [\t \e \x \t])

;; (conj (y t) (x t))
(concat (y t) (x t))
(conj [\a \b] \c)

(defn x [s]
  (apply str (map (fn [[_ itm]] itm) (filter (fn [[idx _]] (even? idx)) (map-indexed vector s)))))
(x t)
(defn y [s]
  (apply str (map (fn [[_ itm]] itm) (filter (fn [[idx _]] (odd? idx)) (map-indexed vector s)))))
(y t)

(def t "This is a test!")

(apply str (nth (iterate (fn [s] (concat (y s) (x s))) t) 1))
(take 2 (iterate (fn [s] (concat (y s) (x s))) t))


(encrypt t 2)
(decrypt "hsi  etTi sats!" 1)
(count "hsi  etTi sats!")
(decrypt "hskt svr neetn!Ti aai eyitrsig" 1)
(count "hskt svr neetn!Ti aai eyitrsig")
(decrypt " Tah itse sits!" 1)

(deftest sample-tests-encrypt
  (testing "Encription tests"
    (is (= "This is a test!" (encrypt "This is a test!" 0)))
    (is (= "hsi  etTi sats!" (encrypt "This is a test!" 1)))
    (is (= "s eT ashi tist!" (encrypt "This is a test!" 2)))
    (is (= " Tah itse sits!" (encrypt "This is a test!" 3)))
    (is (= "This is a test!" (encrypt "This is a test!" 4)))
    (is (= "This is a test!" (encrypt "This is a test!" -1)))
    (is (= "hskt svr neetn!Ti aai eyitrsig"
           (encrypt "This kata is very interesting!" 1)))))

(deftest sample-tests-decrypt
  (testing "Decryption tests"
    (is (= "This is a test!" (decrypt "This is a test!" 0)))
    (is (= "This is a test!" (decrypt "hsi  etTi sats!" 1)))
    (is (= "This is a test!" (decrypt "s eT ashi tist!" 2)))
    (is (= "This is a test!" (decrypt " Tah itse sits!" 3)))
    (is (= "This is a test!" (decrypt "This is a test!" 4)))
    (is (= "This is a test!" (decrypt "This is a test!" -1)))
    (is (= "This kata is very interesting!"
           (decrypt "hskt svr neetn!Ti aai eyitrsig" 1)))))
