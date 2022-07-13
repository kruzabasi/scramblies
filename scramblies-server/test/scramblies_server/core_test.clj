(ns scramblies-server.core-test
  (:require
   [clojure.test :refer [is deftest]]))

(deftest init-test []
  "just passes for now"
  (is (= 2 (+ 1 1))))