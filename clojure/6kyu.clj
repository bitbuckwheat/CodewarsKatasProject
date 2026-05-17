;;======================================================================
;; https://www.codewars.com/kata/56dbe7f113c2f63570000b86/train/clojure

(ns sixth_kyu.core
  (:require [clojure.test :refer [test-vars run-tests deftest is are testing]]))
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

Test of the typing. This is how I type. And it's prettey fast. But now I don't really care about it. Because my pace of typing is pretty good right now. And it grows up naturally or organically. В общем, кажется, как-будто удобнее стало печатать и стало меньше ложных срабатываний. Но пока это не однозначно. Надо понаблюбать подольше. И тогда станет ясно окончательно.

;;====================================================================== 
;; https://www.codewars.com/kata/5a420163b6cfd7cde5000077

(def resultSheet1
  (str
    "Los Angeles Clippers 104 Dallas Mavericks 88,
    New York Knicks 101 Atlanta Hawks 112,
    Indiana Pacers 103 Memphis Grizzlies 112,"
    "Los Angeles Lakers 111 Minnesota Timberwolves 112,
    Phoenix Suns 95 Dallas Mavericks 111,
    Portland Trail Blazers 112 New Orleans Pelicans 94,"
    "Sacramento Kings 104 Los Angeles Clippers 111,
    Houston Rockets 85 Denver Nuggets 105,
    Memphis Grizzlies 76 Cleveland Cavaliers 106,"
    "Milwaukee Bucks 97 New York Knicks 122,
    Oklahoma City Thunder 112 San Antonio Spurs 106,
    Boston Celtics 112 Philadelphia 76ers 95,"
    "Brooklyn Nets 100 Chicago Bulls 115,
    Detroit Pistons 92 Utah Jazz 87,
    Miami Heat 104 Charlotte Hornets 94,"
    "Toronto Raptors 106 Indiana Pacers 99,
    Orlando Magic 87 Washington Wizards 88,
    Golden State Warriors 111 New Orleans Pelicans 95,"
    "Atlanta Hawks 94 Detroit Pistons 106,
    Chicago Bulls 97 Cleveland Cavaliers 95,"))

(def resultSheet2
  (str
    "San Antonio Spurs 111 Houston Rockets 86,Chicago Bulls 103 Dallas Mavericks 102,Minnesota Timberwolves 112 Milwaukee Bucks 108,"
    "New Orleans Pelicans 93 Miami Heat 90,Boston Celtics 81 Philadelphia 76ers 65,Detroit Pistons 115 Atlanta Hawks 87,"  
    "Toronto Raptors 92 Washington Wizards 82,Orlando Magic 86 Memphis Grizzlies 76,Los Angeles Clippers 115 Portland Trail Blazers 109,"  
    "Los Angeles Lakers 97 Golden State Warriors 136,Utah Jazz 98 Denver Nuggets 78,Boston Celtics 99 New York Knicks 85,"  
    "Indiana Pacers 98 Charlotte Hornets 86,Dallas Mavericks 87 Phoenix Suns 99,Atlanta Hawks 81 Memphis Grizzlies 82,"  
    "Miami Heat 110 Washington Wizards 105,Detroit Pistons 94 Charlotte Hornets 99,Orlando Magic 110 New Orleans Pelicans 107,"  
    "Los Angeles Clippers 130 Golden State Warriors 95,Utah Jazz 102 Oklahoma City Thunder 113,San Antonio Spurs 84 Phoenix Suns 104,"  
    "Chicago Bulls 103 Indiana Pacers 94,Milwaukee Bucks 106 Minnesota Timberwolves 88,Los Angeles Lakers 104 Portland Trail Blazers 102,"
    "Houston Rockets 120 New Orleans Pelicans 100,Boston Celtics 111 Brooklyn Nets 105,Charlotte Hornets 94 Chicago Bulls 86,Cleveland Cavaliers 103 Dallas Mavericks 97"))

(def resultSheet3 (str resultSheet1 resultSheet2))

(def team
  (str
    "Los Angeles Clippers,Dallas Mavericks,New York Knicks,NYK,Atlanta Hawks,Indiana Pacers,Memphis Grizzlies,"
    "Los Angeles Lakers,Minnesota Timberwolves,Phoenix Suns,Portland Trail Blazers,New Orleans Pelicans,"
    "Sacramento Kings,Los Angeles Clippers,Houston Rockets,Denver Nuggets,Cleveland Cavaliers,Milwaukee Bucks,"
    "Oklahoma City Thunder,San Antonio Spurs,Boston Celtics,Philadelphia 76ers,Brooklyn Nets,Chicago Bulls,"
    "Detroit Pistons,Utah Jazz,Miami Heat,Charlotte Hornets,Toronto Raptors,Orlando Magic,Washington Wizards,"
    "Golden State Warriors,Dallas Maver"))


(defn nba-cup [result-sheet to-find]
  (-> (str/split result-sheet #",")
      (map #(str % "  "))
      (map #(str/split % #"\s(\d{1,3})\s?"))))


(defn pair-again [[fir sec]]
  [fir (Integer/parseInt sec)])
(pair-again vect)


(defn filtering [[[team-one score-two] [team-two score-two] :as match] team]
  (cond
    (= team team-one) true;; match
    (= team team-two) (reverse match)))


(defn matches [team resultSheet3]
  (filter #(filtering % team)
          (partition 2
            (map pair-again
                (map #(str/split % #"\s+(?=\d{1,3}\b)")
                     (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+"))))))

(def m (matches "Boston Celtics" resultSheet3))
(println m)


(def nums
  (map (fn [[[t-one score-one]
             [t-two score-two]]]
           [score-one score-two])
       m))

(println nums)
(def tm "Boston Celtics")

(defn final [results tm]
  (let [m (matches tm results)
        numbs (map (fn [[[t-one score-one
                          t-two score-two]]]
                       [score-one score-two])
                   m)
        wins (reduce + (map (fn [[one two]] (if (> one two) 1 0)) numbs))
        losses (reduce + (map (fn [[one two]] (if (< one two) 1 0)) numbs))
        draws (reduce + (map (fn [[one two]] (if (= one two) 1 0)) numbs))
        scored (reduce + (map (fn [pair] (first pair)) numbs))
        conceded (reduce + (map (fn [pair] (last pair)) numbs))
        points (r tm results)]
    (str tm ":W=" wins ";D=" draws ";L=" losses ";Scored=" scored ";Conceded=" conceded ";Points=" points)))

(final nums tm)


(defn score [[team score-one] [team_two score-two]]
  (cond
    (> score-one score-two) 3
    (< score-one score-two) 0
    (= score-one score-two) 1))

(reduce + (map #(apply score %) (matches "Boston Celtics" resultSheet3)))
(defn r [string results]
  (reduce + (map #(apply score %) (matches
                                    string
                                    resultSheet3))))

(def points (r tm resultSheet3))
(println points)

(def a [["1" 2] ["2" 3]])
(reverse a) ; (["2" 3] ["1" 2])


(println resultSheet3)

(nba-cup resultSheet3 "Boston Celtics")
(final resultSheet3 "Boston Celtics")


(deftest a-test1
  (are [input1 input2 expected] (= (final input1 input2) expected)
   resultSheet3
   "Boston Celtics"
   "Boston Celtics:W=4;D=0;L=0;Scored=403;Conceded=350;Points=12"
   resultSheet3
   "Boston Celt"
   "Boston Celt:This team didn't play!"))

