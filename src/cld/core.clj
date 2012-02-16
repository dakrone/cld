(ns cld.core
  (:use [clojure.java.io :only [resource file]])
  (:import (com.cybozu.labs.langdetect Detector DetectorFactory)))

(DetectorFactory/loadProfile (file (resource "profiles")))

(defn detect
  "Returns a tuple with the language as the first element and a map of
  languages to their probabilities."
  [^String text]
  (let [^Detector detector (DetectorFactory/create)]
    (.append ^Detector detector text)
    [(.detect ^Detector detector)
     (->> (.getProbabilities ^Detector detector)
          (map str)
          (map #(vec (.split ^String % ":")))
          (into {}))]))
