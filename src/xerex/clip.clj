(ns xerex.clip)

(import [java.awt.datatransfer StringSelection])

(defn ^sun.awt.X11.XClipboard get-clipboard []
  (-> (java.awt.Toolkit/getDefaultToolkit)
      (.getSystemClipboard)))

(defn copy [text]
  (let [^java.awt.datatransfer.StringSelection selection (StringSelection. text)]
    (.setContents (get-clipboard) selection selection)))
