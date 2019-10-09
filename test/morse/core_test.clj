(ns morse.core-test
  (:require [clojure.test :refer :all]
            [morse.core :refer :all]))

(deftest morse-code-test
  (testing "Testing structure and types of morse (&& morse revert) codes"
           (is (map? alpha->morse))
           (is (not (empty? alpha->morse)))
           (is (map? morse->alpha))
           (is (not (empty? morse->alpha))))

  (testing "Testing single characters (morse => alphabet && alphabet => morse)"
           (is (= (get-single-character (current-alphabet :alpha) "a") ".-"))
           (is (= (get-single-character (current-alphabet :alpha) "1") ".----"))
           (is (= (get-single-character (current-alphabet :morse) ".-") "a"))
           (is (= (get-single-character (current-alphabet :morse) ".----") "1")))

  (testing "Testing convert morse to alphabet"
           (is (= (convert :morse "... --- ...") "sos"))
           (is (= (convert :morse "-.. .- - -- .- -.") "batman"))
           (is (= (convert :morse ".- .-. . .- ..... .----") "area51")))

  (testing "Testing convert alphabet to morse"
           (is (= (convert :alpha "sos") "... --- ..."))
           (is (= (convert :alpha "batman") "-.. .- - -- .- -."))
           (is (= (convert :alpha "area51") ".- .-. . .- ..... .----"))))
