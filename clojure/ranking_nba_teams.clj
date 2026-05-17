;; https://www.codewars.com/kata/5a420163b6cfd7cde5000077

(ns ranking
  (:require [clojure.test :refer [test-vars run-tests deftest is are testing]]))
(require '[clojure.string :as str])

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
    "New Orleans Pelicans 93 Miami Heat 90,Philadelphia 76ers 65 Boston Celtics 81,Detroit Pistons 115 Atlanta Hawks 87,"  
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


;; (re-find #"")
(def t "Boston Celtics")
(re-seq (re-pattern (str "(?:,|\\s)" t "(?=\\s)")) resultSheet3)
(println resultSheet3)

(defn pair-again [[fir sec]]
  [fir (Integer/parseInt sec)])


;; (defn pair-again [[fir sec] [third forth]]
;;   [fir (if (re-find #"\d+\.\d+" sec)
;;           Integer/parseInt sec)] [third (Integer/parseInt forth)])


;; (defn filtering [[[team-one score-one] [team-two score-two] :as match] team]
;;   (cond
;;     (= team team-one) true;; match
;;     (= team team-two) (reverse match)))


(defn filtering [[[team-one score-one] [team-two score-two]] team]
  (if (or (= team team-one) (= team team-two))
    true
    false))

(defn change-order [[team-one score-one team-two score-two :as match] team]
  (cond
    (= team team-one) match
    (= team team-two) [team-two score-two team-one score-one]))

;; (defn matches [team resultSheet3]
;;   (map #(change-order % team)
;;     (filter #(filtering % team)
;;             (partition 2
;;               (map pair-again
;;                   (map #(str/split % #"\s+(?=\d{1,3}\b)")
;;                        (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+")))))))


(def team "Boston Celtics")
(defn prepare_matches [team resultSheet3]
  (->> (str/split resultSheet3 #",")
       (filter (fn [match] (str/includes? match team)))
       (map str/trim-newline)
       (map str/trim)
       (map #(str/split % #"(?<=\b\d{1,3})\s|\s(?=\d{1,3}\b)"))
       (map (fn [[one two three four]]
                [one (Integer/parseInt two) three (Integer/parseInt four)]))))
       ;; (map #(str/split % #"(?<=\d{1,3})[\s]")))

(map #(change-order % team) (prepare_matches team resultSheet3))

(map #(change-order % team)
  (filter #(filtering % team)
          (partition 2
            (map pair-again
                (map #(str/split % #"\s+(?=\d{1,3}\b)")
                     (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+"))))))



(map pair-again
           (map #(str/split % #"\s+(?=\d{1,3}\b)")
                (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+")))
(str/split resultSheet3 #"(?<=\d{1,3})[,\s]+")
(str/split resultSheet3 #",")
(not nil)
(def m (matches "Boston Celtics" resultSheet3))
(println m)

(def nums
  (map (fn [[[t-one score-one]
             [t-two score-two]]]
           [score-one score-two])
       m))
(println nums)
(map (fn [[[t-one score-one] [t-two score-two]]] [score-one score-two] m))

(def tm "Boston Celtics")

(defn final [results tm]
  (cond
    (= nil tm) (str ":This team didn't play!")
    (= nil (re-seq (re-pattern (str "(?:,|\\s)" tm "(?=\\s)")) resultSheet3))
    (str tm ":This team didn't play!")
    (not= nil (re-find #"\d+\.\d+" results))
    (str "Error(float number):"
         (str/trim (str/trim-newline (filter #(not= nil (re-find #"\d+\.\d+" %)) results))))
    :else (let [numbs (map (fn [[_ score-one _ score-two]] [score-one score-two])
                           (map #(change-order % tm) (prepare_matches tm results)))
                wins (reduce + (map (fn [[one two]] (if (> one two) 1 0)) numbs))
                losses (reduce + (map (fn [[one two]] (if (< one two) 1 0)) numbs))
                draws (reduce + (map (fn [[one two]] (if (= one two) 1 0)) numbs))
                scored (reduce + (map (fn [pair] (first pair)) numbs))
                conceded (reduce + (map (fn [pair] (last pair)) numbs))
                points (r tm results)]
             (str tm ":W=" wins ";D=" draws ";L=" losses ";Scored=" scored ";Conceded=" conceded ";Points=" points))))

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


;; works, but not for all tests
;; Passed: 5 Failed: 13 Errors: 1 Exit Code: 1
;;
;; (ns nba.core)
;; (require '[clojure.string :as str])
;;
;;
;; (defn pair-again [[fir sec]]
;;   [fir (Integer/parseInt sec)])
;;
;;
;; (defn filtering [[[team-one score-two] [team-two score-two] :as match] team]
;;   (cond
;;     (= team team-one) match
;;     (= team team-two) (reverse match)))
;;
;;
;; (defn matches [team resultSheet3]
;;   (filter #(filtering % team)
;;           (partition 2
;;             (map pair-again
;;                 (map #(str/split % #"\s+(?=\d{1,3}\b)")
;;                      (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+"))))))
;;
;; (defn score [[team score-one] [team_two score-two]]
;;   (cond
;;     (> score-one score-two) 3
;;     (< score-one score-two) 0
;;     (= score-one score-two) 1))
;;
;; (defn r [string result-sheet]
;;   (reduce + (map #(apply score %) (matches
;;                                     string
;;                                     result-sheet))))
;;
;; ;; (def points (r to-find result-sheet))
;;
;;
;; (defn nba-cup [result-sheet to-find]
;;   (if (= nil (re-seq (re-pattern (str "(?:,|\\s)" to-find "(?=\\s)")) result-sheet))
;;       (str to-find ":This team didn't play!")
;;       (let [m (matches to-find result-sheet)
;;             numbs (map (fn [[[t-one score-one] [t-two score-two]]] [score-one score-two])
;;                        m)
;;             wins (reduce + (map (fn [[one two]] (if (> one two) 1 0)) numbs))
;;             losses (reduce + (map (fn [[one two]] (if (< one two) 1 0)) numbs))
;;             draws (reduce + (map (fn [[one two]] (if (= one two) 1 0)) numbs))
;;             scored (reduce + (map (fn [pair] (first pair)) numbs))
;;             conceded (reduce + (map (fn [pair] (last pair)) numbs))
;;             points (r to-find result-sheet)]
;;         (str to-find ":W=" wins ";D=" draws ";L=" losses ";Scored=" scored ";Conceded=" conceded ";Points=" (r to-find result-sheet)))))
;;
;;
;; Passed: 16 Failed: 2 Errors: 1 Exit Code: 1
;; (ns nba.core)
;; (require '[clojure.string :as str])
;;
;;
;; (defn pair-again [[fir sec]]
;;   [fir (Integer/parseInt sec)])
;;
;;
;; (defn filtering [[[team-one score-one] [team-two score-two] :as match] team]
;;   (if (or (= team team-one) (= team team-two))
;;     true
;;     false))
;;     ;; (= team team-two) (reverse match)))
;;
;;
;; (defn change-order [[[team-one score-one] [team-two score-two] :as match] team]
;;   (cond
;;     (= team team-one) match
;;     (= team team-two) (reverse match)))
;;
;; (defn matches [team resultSheet3]
;;   (map #(change-order % team)
;;     (filter #(filtering % team)
;;             (partition 2
;;               (map pair-again
;;                   (map #(str/split % #"\s+(?=\d{1,3}\b)")
;;                        (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+")))))))
;;
;;
;; (defn score [[team score-one] [team_two score-two]]
;;   (cond
;;     (> score-one score-two) 3
;;     (< score-one score-two) 0
;;     (= score-one score-two) 1))
;;
;; (defn r [string result-sheet]
;;   (reduce + (map #(apply score %) (matches
;;                                     string
;;                                     result-sheet))))
;;
;;
;; (defn nba-cup [result-sheet to-find]
;;   (cond
;;     (= nil (re-seq (re-pattern (str "(?:,|\\s)" to-find "(?=\\s)")) result-sheet))
;;     (str to-find ":This team didn't play!")
;;     (= (not nil) (re-find #"\d+\.\d+" result-sheet))
;;     (str "Error(float number):the concerned string")
;;     :else (let [m (matches to-find result-sheet)
;;                 numbs (map (fn [[[t-one score-one] [t-two score-two]]] [score-one score-two])
;;                            m)
;;                 wins (reduce + (map (fn [[one two]] (if (> one two) 1 0)) numbs))
;;                 losses (reduce + (map (fn [[one two]] (if (< one two) 1 0)) numbs))
;;                 draws (reduce + (map (fn [[one two]] (if (= one two) 1 0)) numbs))
;;                 scored (reduce + (map (fn [pair] (first pair)) numbs))
;;                 conceded (reduce + (map (fn [pair] (last pair)) numbs))
;;                 points (r to-find result-sheet)]
;;             (str to-find ":W=" wins ";D=" draws ";L=" losses ";Scored=" scored ";Conceded=" conceded ";Points=" (r to-find result-sheet)))))
;;
;;
;;
;;
;; working soluttion Passed: 18 Failed: 0
;; (ns nba.core)
;; (require '[clojure.string :as str])
;;
;;
;; (defn pair-again [[fir sec]]
;;   [fir (Integer/parseInt sec)])
;;
;;
;; (defn filtering [[[team-one score-one] [team-two score-two] :as match] team]
;;   (if (or (= team team-one) (= team team-two))
;;     true
;;     false))
;;     ;; (= team team-two) (reverse match)))
;;
;;
;; (defn change-order [[[team-one score-one] [team-two score-two] :as match] team]
;;   (cond
;;     (= team team-one) match
;;     (= team team-two) (reverse match)))
;;
;; (defn matches [team resultSheet3]
;;   (map #(change-order % team)
;;     (filter #(filtering % team)
;;             (partition 2
;;               (map pair-again
;;                   (map #(str/split % #"\s+(?=\d{1,3}\b)")
;;                        (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+")))))))
;;
;;
;; (defn score [[team score-one] [team_two score-two]]
;;   (cond
;;     (> score-one score-two) 3
;;     (< score-one score-two) 0
;;     (= score-one score-two) 1))
;;
;; (defn r [string result-sheet]
;;   (reduce + (map #(apply score %) (matches
;;                                     string
;;                                     result-sheet))))
;;
;; (defn nba-cup [result-sheet to-find]
;;   (do
;;     (println result-sheet)
;;     (println to-find)
;;     (cond
;;       (= "" to-find) (str "")
;;       (= nil (re-seq (re-pattern (str "(?:,|\\s)" to-find "(?=\\s)")) result-sheet))
;;       (str to-find ":This team didn't play!")
;;       (not= nil (re-find #"\d+\.\d+" result-sheet))
;;       (str "Error(float number):"
;;            (str/trim (str/trim-newline (first (filter #(not= nil (re-find #"\d+\.\d+" %)) (str/split result-sheet #","))))))
;;       :else (let [m (matches to-find result-sheet)
;;                   numbs (map (fn [[[t-one score-one] [t-two score-two]]] [score-one score-two])
;;                              m)
;;                   wins (reduce + (map (fn [[one two]] (if (> one two) 1 0)) numbs))
;;                   losses (reduce + (map (fn [[one two]] (if (< one two) 1 0)) numbs))
;;                   draws (reduce + (map (fn [[one two]] (if (= one two) 1 0)) numbs))
;;                   scored (reduce + (map (fn [pair] (first pair)) numbs))
;;                   conceded (reduce + (map (fn [pair] (last pair)) numbs))
;;                   points (r to-find result-sheet)]
;;               (str to-find ":W=" wins ";D=" draws ";L=" losses ";Scored=" scored ";Conceded=" conceded ";Points=" (r to-find result-sheet))))))
;;
;;
;;
;; final solution with AI optimized codebase
;; (ns nba.core)
;; (require '[clojure.string :as str])
;;
;;
;; ;; convert str scores to integer scores
;; (defn scores-to-integers [[team score]]
;;   [team (Integer/parseInt score)])
;;
;; ;; filter input team matches
;; (defn filtering [[[team-one _] [team-two _]] team]
;;   (or (= team team-one) (= team team-two)))
;;
;; ;; put input team first in every match
;; (defn normalize-teams-ordering
;;   [[[team-one _] [team-two _] :as match] team]
;;   (cond
;;     (= team team-one) match
;;     (= team team-two) (reverse match)))
;;
;; ;; form a proper match structure
;; (defn parse-matches [team resultSheet3]
;;   (map #(normalize-teams-ordering % team)
;;     (filter #(filtering % team)
;;             (partition 2
;;               (map scores-to-integers
;;                   (map #(str/split % #"\s+(?=\d{1,3}\b)")
;;                        (str/split resultSheet3 #"(?<=\d{1,3})[,\s]+")))))))
;;
;; ;; calculate and return team stats
;; (defn nba-cup [result-sheet to-find]
;;     (println result-sheet)
;;     (println to-find)
;;     (cond
;;
;;       ;; edge case ""
;;       (= "" to-find)
;;       (str "")
;;
;;       ;; edge case of wrong team spelling (e.g. "Boston Celt")
;;       (= nil (re-seq (re-pattern (str "(?:,|\\s)" to-find "(?=\\s)")) result-sheet))
;;       (str to-find ":This team didn't play!")
;;
;;       ;; edge case of float scores (e.g. "101.25")
;;       (not= nil (re-find #"\d+\.\d+" result-sheet))
;;       (str "Error(float number):"
;;            (str/trim (str/trim-newline (first (filter #(not= nil (re-find #"\d+\.\d+" %))
;;                                                       (str/split result-sheet #","))))))
;;
;;       ;; proper inputs case
;;       :else (let [stats (reduce (fn [acc [[_ score1] [_ score2]]]
;;                                   (cond
;;                                     (> score1 score2) (-> acc 
;;                                                         (update :w inc)
;;                                                         (update :pts + 3)
;;                                                         (update :sc + score1)
;;                                                         (update :con + score2))
;;                                     (< score1 score2) (-> acc
;;                                                         (update :l inc)
;;                                                         (update :pts + 0)
;;                                                         (update :sc + score1)
;;                                                         (update :con + score2))
;;                                     (= score1 score2) (-> acc
;;                                                         (update :d inc)
;;                                                         (update :pts inc)
;;                                                         (update :sc + score1)
;;                                                         (update :con + score2))))
;;                                 {:w 0 :d 0 :l 0 :sc 0 :con 0 :pts 0}
;;                                 (parse-matches to-find result-sheet))]
;;               (str to-find
;;                    ":W=" (stats :w)
;;                    ";D=" (stats :d)
;;                    ";L=" (stats :l)
;;                    ";Scored=" (stats :sc)
;;                    ";Conceded=" (stats :con)
;;                    ";Points=" (stats :pts)))))
