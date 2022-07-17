(ns scramblies-client.events
  (:require
   [re-frame.core :refer [reg-event-fx reg-event-db subscribe]]
   [scramblies-client.db :as db]
   [superstructor.re-frame.fetch-fx]))

(reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(reg-event-fx
 :scramblies-req
 (fn [{:keys [_]} _]
   {:fetch {:method       :get
            :url          "http://localhost:3000"
            :params       {:str1 @(subscribe [:str1-value])
                           :str2 @(subscribe [:str2-value])}
            :timeout      5000
            :mode         :cors
            :on-success   [:scramblies-req-success]
            :on-failure   [:scramblies-req-failed]}}))

(reg-event-db
 :scramblies-req-success
 (fn [db [_ res]]
   (assoc db :scramblies-res  (->> res :body (.parse js/JSON) js->clj))))

(reg-event-fx
 :scramblies-req-failed
 (fn [_ [_ res]]
   (js/alert (str (:problem-message res) "! Please Make Sure Server is Running"))))

(reg-event-db
 :string-handler
 (fn [db [_ id val]]
   (assoc db id val)))
