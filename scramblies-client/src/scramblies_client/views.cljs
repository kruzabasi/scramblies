(ns scramblies-client.views
  (:require
   [re-frame.core :refer [subscribe dispatch]]
   [scramblies-client.subs :as subs]
   [clojure.string :refer [lower-case]]))

(defn alphabets?
  [x]
  (->>
   (str x)
   (re-matches #"^[a-z]+$")
   (some?)))

(defn- string-input [name id] 
  (let [db-key       (keyword id)
        sub-key      (keyword (str id "-value"))
        input-value  (or @(subscribe [sub-key]) "")
        valid-input? (or (alphabets? input-value) (= input-value ""))]
    [:div {:className "my-6 sm:my-12 text-gray-700"}
     [:label {:className "form-label inline-block"
              :for id} name]
     [:input {:id id
              :type "text"
              :value input-value
              :on-change #(dispatch [:string-handler db-key (lower-case (.-value (.-target %)))])
              :required true
              :className (str
                          "block w-full h-18 px-3 py-1.5 
                          border-transparent focus:border-transparent focus:ring-0 focus:border-2"
                          (if valid-input?
                            " focus:border-b-black border-b-black"
                            " focus:border-b-red-400 border-b-red-400"))}]
     (when-not valid-input? 
       [:span {:className "text-xs text-red-400"} "please enter only alphabet characters:"])]))

(defn- scramblies-form []
  [:form {:on-submit (fn [e]
                       (.preventDefault e)
                       (dispatch [:scramblies-req]))}
   [:div {:className "form-group mb-6"}
    (string-input "Main String" "str1")
    (string-input "Sub String" "str2")
    (let [str1 @(subscribe [:str1-value])
          str2 @(subscribe [:str2-value])
          valid-form? (and (alphabets? str1) (alphabets? str2))]
      [:button {:type "submit"
                :disabled (not valid-form?)
                :className (str 
                            "px-3 py-1.5 w-24 h-10 rounded
                            text-white font-medium text-xs uppercase 
                            shadow-md hover:shadow-lg transition duration-150 ease-in-out "
                            (if valid-form?
                              "bg-blue-600 hover:bg-blue-700"
                              "bg-blue-400"))} "Check"])]])

(defn main-panel []
  (let [name (subscribe [::subs/name])]
    [:div {:className "bg-gray-800 p-10 h-screen flex flex-col"}
     [:h1 {:className "text-center text-white text-3xl font-bold py-2"} @name]
     [:main {:className "self-center m-4 min-h-max w-full"}
      [:div {:className "mx-auto block p-6 rounded-lg shadow-lg bg-white max-w-sm h-full"}
       (scramblies-form)
       [:span {:className "float-right mb-4"}
        (when @(subscribe [:scramblies-res])
          (str (@(subscribe [:scramblies-res]) "result")))]]]]))