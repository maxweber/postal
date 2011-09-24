(defproject org.clojars.maxweber/postal "1.6-SNAPSHOT"
  :resources-path "etc"
  :repositories {"java.net" "http://download.java.net/maven/2"
                 "clojars" "http://clojars.org/repo"}
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [javax.mail/mail "1.4.4"
                  :exclusions [javax.activation/activation]]]
  :dev-dependencies [[lein-clojars "0.6.0"]
                     [swank-clojure "1.4.0-SNAPSHOT"]])
