(ns scramblies-server.core-test
  (:require
   [clojure.test :refer [is deftest]]
   [clojure.test.check.generators :as gen]
   [clojure.string :refer [lower-case]]
   [scramblies-server.core :as SUT]))

(deftest test-scramble?-fn []
  "test 'scramblies-server.core/scramble? function with random strings"
  (let [sample-1      (into [] (gen/sample gen/char-alpha 19))
        sample-2      (shuffle (subvec sample-1 4 17))
        sample-3      (->>
                       (gen/such-that #(some (fn [char] (not= char %)) sample-1) gen/char-alpha) ;;=> random letters/char that DO NOT occur in sample-1
                       (gen/sample)
                       (into [])
                       (concat (subvec sample-1 4 9))
                       (shuffle))
        
        [str-1 str-2 str-3] (map #(lower-case (apply str %)) [sample-1 sample-2 sample-3])]
    
    (is (= true (SUT/scramble? str-1 str-2)))
    (is (= false (SUT/scramble? str-1 str-3)))))