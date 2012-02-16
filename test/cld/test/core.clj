(ns cld.test.core
  (:use [cld.core]
        [clojure.test]))

(deftest t-detect
  (is (= "en"
         (first (detect (str "This is a sentence, it is written in "
                             "English. I like tacos. My cat is weird.")))))
  (is (= "fr"
         (first (detect (str "Florent Goncalves, l'ancien directeur de la "
                             "prison pour femmes de Versailles, a été condamné "
                             "à un an de prison ferme pour avoir accordé des "
                             "faveurs à Emma, l'appât du Gang "
                             "des barbares.")))))
  (is (= "de"
         (first (detect (str "Die Parteichefs der großen Regierungsparteien "
                             "sichern schriftlich zu, auch nach der Wahl an "
                             "den Sparplänen festzuhalten. Aber was hilft das, "
                             "wenn niemand weiß, ob sie dann überhaupt "
                             "noch regieren?")))))
  (is (= "es"
         (first (detect (str "No hay cien días de tregua en las relaciones "
                             "entre el Gobierno y la oposición. A los 55 días "
                             "de la toma de posesión de Mariano Rajoy, la dura "
                             "reforma laboral aprobada por el Ejecutivo es ya "
                             "el campo de batalla en el que se librará "
                             "el enfrentamiento."))))))

