(ns cld.core
  (:use [clojure.java.io :only [resource file]])
  (:import (com.cybozu.labs.langdetect Detector DetectorFactory)
           (java.util HashMap)))

(defn load-profiles
  "Load detection profiles from either a File object or a String path."
  [file-or-string]
  (DetectorFactory/clear)
  (DetectorFactory/loadProfile file-or-string))

(defn load-default-profiles
  "Load the default profiles from the langdetect jar."
  []
  (DetectorFactory/clear)
  (DetectorFactory/loadProfile))

(defn detect
  "Returns a tuple with the language as the first element and a map of
  languages to their probabilities. Accepts an optional hash-map of options:

  :smoothing <double>   - Smoothing, defaults to 0.5
  :max-length <int>     - Maximum length of data to read, defaults to all
  :prior-map <hash-map> - A map on languages to probabilites to use
  :verbose <boolean>    - Use verbose mode, defaults to off (false)"
  [text-or-reader & [opts]]
  (let [^Detector detector (DetectorFactory/create)]
    (when (:smoothing opts)
      (.setAlpha detector (double (:smoothing opts))))
    (when (:max-length opts)
      (.setMaxTextLength detector (:max-length opts)))
    (when (:prior-map opts)
      (.setPriorMap detector (HashMap. (:prior-map opts))))
    (when (:verbose opts)
      (.setVerbose detector))
    (.append ^Detector detector text-or-reader)
    [(.detect ^Detector detector)
     (->> (.getProbabilities ^Detector detector)
          (map str)
          (map #(vec (.split ^String % ":")))
          (into {}))]))

(defn default-init!
  "Initialize the DetectorFactory with the default profiles. Will not throw an
  exception on subsequent invocations."
  []
  (defonce _ (load-default-profiles)))
