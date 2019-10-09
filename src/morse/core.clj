(ns morse.core
  (:require [clojure.set]
            [clojure.string :as str]))

(def alpha->morse {
                  "a" ".-"
                  "b" "-.."
                  "c" "-.-."
                  "d" "-.."
                  "e" "."
                  "f" "..-."
                  "g" "--."
                  "h" "...."
                  "i" ".."
                  "j" ".---"
                  "k" "-.-"
                  "l" ".-.."
                  "m" "--"
                  "n" "-."
                  "o" "---"
                  "p" ".--."
                  "q" "--.-"
                  "r" ".-."
                  "s" "..."
                  "t" "-"
                  "u" "..-"
                  "v" "...-"
                  "w" ".--"
                  "x" "-..-"
                  "y" "-.--"
                  "z" "--.."
                  "1" ".----"
                  "2" "..---"
                  "3" "...--"
                  "4" "....-"
                  "5" "....."
                  "6" "-...."
                  "7" "--..."
                  "8" "---.."
                  "9" "----."
                  "0" "----"
                  })

(def morse->alpha
  (clojure.set/map-invert alpha->morse))

(def alphabets
  {:alpha alpha->morse
   :morse morse->alpha})

(defn current-alphabet
  [type]
  (get alphabets type))

(defn chars-join
  [type chars]
  (cond
    (= type :morse) (apply str chars)
    (= type :alpha) (str/join " " chars)))

(defn get-single-character
  [alpha character]
    (get alpha (str character)))

(defn get-characters
  [type characters]
  (let [characters (cond
                     (= type :morse) characters
                     (= type :alpha) (str/join "" characters))]
    (chars-join type (map #(get-single-character (current-alphabet type) %) characters))))

(defn convert
  [type phrase]
  (get-characters type (str/split phrase #" ")))

;(defn- convert-characters [type characters]
;  (chars-join type (map (comp (current-alphabet type) str) characters)))
;
;(defn- phrase->chars [type phrase]
;  (cond->> (str/split phrase #" ")
;    (= type :alpha) (str/join "")))
;
;(defn convert [type phrase]
;  (convert-characters type (phrase->chars type phrase)))
