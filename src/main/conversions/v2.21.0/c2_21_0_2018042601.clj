(ns facepalm.c2-21-0-2018042601
  (:use [kameleon.sql-reader :only [load-sql-file exec-sql-statement]]))

(def ^:private version
  "The destination database version."
  "2.21.0:20180426.01")

(defn- add-interactive-column
  "Adds the interactive column to the tools table"
  []
  (exec-sql-statement
   "ALTER TABLE ONLY tools
    ADD COLUMN interactive boolean NOT NULL DEFAULT FALSE"))

(defn- create-container-ports-table
  "Adds the container_ports table to the database"
  []
  (load-sql-file "tables/83_container_ports.sql")
  (load-sql-file "constraints/83_container_ports.sql"))

(defn- create-interapps-proxy-settings-table
  "Adds the interactive_apps_proxy_settings table to the database"
  []
  (load-sql-file "tables/84_interapps_proxy_settings.sql")
  (load-sql-file "constraints/84_interapps_proxy_settings.sql"))

(defn convert
  "Performs the conversion for this database version."
  []
  (println "Performing the conversion for" version)
  (add-interactive-column)
  (create-container-ports-table)
  (create-interapps-proxy-settings-table))