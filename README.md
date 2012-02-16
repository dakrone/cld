# cld - Clojure Language Detection

A tiny library wrapping
[language-detect](https://code.google.com/p/language-detection/) that
can be used to determine the language of a particular piece of text.

Here are the
[supported languages](https://code.google.com/p/language-detection/wiki/LanguageList)

## Why?

There were not any easily usable language detection libraries, and
while the language-detection library was good, it was not packaged
well and was short on documentation. Also, Clojure needed a nice way
to use it.

# Usage

Add this to your project.clj:

```clojure
[cld "0.1.0"]
```

```clojure
(ns foo
  (:require [cld.core :as lang]))

;; This loads the default language profiles, 99% of the time you will
;; want to use this, the other 1% of the time you can use
;; (lang/load-profiles "/path/to/profilesdir") to load whichever
;; profiles you'd like to use.
;;
;; Calling this multiple times only loads the profiles once, however
;; calling load-profiles multiple times with result in an Exception if
;; the profiles have already been loaded.
(lang/default-init!)

(lang/detect "Clojure is a sweet language.")
;; A tuple is returned on language and language-probability map:
;; => ["en" {"en" "0.7142847692020113", "nl" "0.28571303555752214"}]

(lang/detect "ただしその発表の時にお約束していたとおり")
;; => ["ja" {"ja" "0.9999999913100619"}]

(lang/detect "Le directeur de campagne de François Hollande réagit à l'entrée en campagne de John Doe")
;; => ["fr" {"fr" "0.9999964521882916"}]

;; A Reader can be specified also:
(lang/detect (clojure.java.io/reader "/tmp/foo"))

```

`detect` also supports a map of options, here are the options:

```clojure
{:smoothing n   ;; defaults to 0.5
 :max-length n  ;; defaults to reading the entire stream or string
 :verbose true  ;; defaults to false, prints to stdout
 :prior-map {"en" 0.1123   ;; A map of prior probabilities
             "fr" 0.0091
             "jp" 0.2330}}

(lang/detect "This is english, Un corps de femme a été retrouvé")
;; => ["fr" {"fr" "0.8571405683231152", "en" "0.14285930685987672"}]

(lang/detect "This is english, Un corps de femme a été retrouvé" {:max-length 10})
;; => ["en" {"en" "0.999996754400581"}]
```

# License

Licensed under the Apache Public License, version 2

## Disclaimer

I don't actually know what any of the French or Japanese sentences
mean, there shouldn't be anything offensive in there, but my apologies
if there is.
