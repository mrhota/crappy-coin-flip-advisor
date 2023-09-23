(ns mrhota.alphabetsuperset.c
  (:require [goog.dom :as gdom]
            [goog.style :as gstyle]
            [clojure.string :as string]))

(defn coin-flip []
  (rand-nth ["heads" "tails"]))

(def el-names [:surface-texture
               :temperature
               :fan-status
               :humidity
               :coin-diameter
               :coin-thickness
               :coin-weight
               :edge-type
               :wind-heading
               :wind-speed
               :flip-height
               :number-of-turns])

(defn predict-outcome [event]
  ;; don't submit form the normal way
  (.preventDefault event)
  (let [els (into {} (map (fn [el-name]
                            [el-name (gdom/getElement (name el-name))]))
                  el-names)
        result-area (gdom/getElement "result")]

    (gstyle/setStyle result-area #js {:display "block"})
    (gdom/setTextContent result-area
                         (str "I predict "
                              (coin-flip)
                              ". It's clear that the "
                              (string/replace (name (rand-nth el-names)) "-" " ")
                              " will dominate the outcome. Naturally."))))

(defn- init []
  (let [submit-button (gdom/getElement "submit-button")]
    (.addEventListener submit-button "click" predict-outcome)))

(defn ^:export main []
  (init))
