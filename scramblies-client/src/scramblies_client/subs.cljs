(ns scramblies-client.subs
  (:require
   [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::name
 (fn [db]
   (:name db)))

(reg-sub
 :str1-value
 (fn [db]
   (:str1 db)))

(reg-sub
 :str2-value
 (fn [db]
   (:str2 db)))

(reg-sub
 :scramblies-res
 (fn [db]
   (:scramblies-res db)))
