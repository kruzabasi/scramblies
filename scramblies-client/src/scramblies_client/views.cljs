(ns scramblies-client.views
  (:require
   [re-frame.core :refer [subscribe]]
   [scramblies-client.subs :as subs]))

(defn- string-input [name id]
  [:div {:className "my-6 sm:my-12 text-gray-700 "}
   [:label {:className "form-label inline-block"
            :for id} name]
   [:input {:id id
            :className "block w-full h-18 px-3 py-1.5 
                        bg-clip-padding border-b border-solid border-gray-800 
                        transition ease-in-out outline-none"}]])

(defn main-panel []
  (let [name (subscribe [::subs/name])]
    [:div {:className "bg-gray-800 p-10 h-screen flex flex-col"}
     [:h1 {:className "text-center text-3xl font-bold py-2"} "Hello from " @name]
     [:main {:className "self-center m-4 h-4/6 w-full"}
      [:div {:className "mx-auto block p-6 rounded-lg shadow-lg bg-white max-w-sm h-full"}
       [:form
        [:div {:className "form-group mb-6"}
         (string-input "Main String" "mainStringInput")
         (string-input "Sub String" "subStringInput")
         [:button {:type "submit"
                   :className "px-3 py-1.5 w-24 h-10 rounded
                               bg-blue-600 text-white font-medium text-xs leading-tight uppercase
                               shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out"} "Check"]]]]]]))
