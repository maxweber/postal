(ns postal.smtp
  (:use [postal.message :only [make-jmessage]])
  (:import [javax.mail Transport Session]))

(defn default-port [port]
  (= port "25"))

(defn smtp-send
  ([msg]
     (let [jmsg (make-jmessage msg)]
       (try
         (Transport/send jmsg)
         {:code 0 :error :SUCCESS :message "message sent"}
         (catch Exception e
           {:code 99 :error (class e) :message (.getMessage e)}))))
  ([host port & msgs]
     (let [session (doto (Session/getInstance
                          (doto (java.util.Properties.)
                            (.put "mail.smtp.host" (or host "localhost"))
                            (.put "mail.smtp.port" (or port "25"))
                            (.put "mail.smtp.starttls.enable" (if port "true" "false"))))
                     (.setDebug false))]
       (with-open [transport (doto (if (default-port port)
                                     (-> session (.getTransport "smtp"))
                                     (-> session (.getTransport "smtps")))
                               (.connect host port))]
         (let [jmsgs (map #(make-jmessage % session) msgs)]
           (doseq [jmsg jmsgs]
             (.sendMessage transport jmsg (.getAllRecipients jmsg))))))))


