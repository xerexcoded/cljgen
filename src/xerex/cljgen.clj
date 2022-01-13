(ns xerex.cljgen
  (:require [xerex.password :refer [generate-password]]
            [xerex.clip :refer [copy]]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))
(defn println-yellow [& args]
  (println (str "\033[0;33m" (apply str args))))

(def cli-options
  [["-l" "--length LENGTH" "Password Length"
    :default 8
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 100) "Must be a number between 0 and 100"]]
   ["-h" "--help"]])

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [arguments (parse-opts args cli-options)
        options (:options arguments)
        summary (:summary arguments)]

    (if (:help options)
      (println summary)
      (let [generated-password (generate-password (:length options))]
        (copy generated-password)
        (println-yellow "Your generated password is " generated-password)))))



