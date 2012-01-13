(ns euler.test.core
  (:use [euler.core])
  (:use [clojure.test]))

(deftest test-problem1
  (is true (= (problem1) 233168)))

(deftest test-problem2
  (is true (= (problem2) 4613732)))

(deftest test-problem3
  (is true (= (problem3) 600851475143)))

(deftest test-problem4
  (is true (= (problem4) 906609)))

(deftest test-problem5
  (is true (= (problem5) 232792560)))

(deftest test-problem6
  (is true (= (problem6) 25164150)))

(deftest test-problem7
  (is true (= (problem7) 104743)))

(deftest test-problem8
  (is true (= (problem8) 40824)))

