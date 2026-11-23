# JAVA ASSIGNMENT 5 — CO5: Engineering Reliable Data Storage
**Student:** N. ARUN KUMAR | **Register:** 192424184 | **Course:** CSA09 — Programming in Java  
**Java:** OpenJDK 21 | **Database:** MariaDB / MySQL-compatible | **JDBC:** MariaDB Connector/J 2.7.6

## Contents
- Part-A/: persistence design, buffered I/O, DAO pattern, serialization and transaction/security pseudocode.
- Task-B1/: delimited bus-pass file store, damaged-line recovery and I/O benchmark.
- Task-B2/: SQL injection demonstration versus PreparedStatement.
- Task-B3/: JDBC Statement, PreparedStatement and batch import comparison, including transaction rollback.
- Task-B4/: metadata inspection and validated migration of legacy records into a relational database.
- sql/: schema and seed scripts.
- data/: benchmark CSV files.
- figures/: explanatory SVG diagrams and charts.

## Run (JDK 21)
From this Assignment-5 folder:
```bash
javac -d out Task-B1/*.java
java -cp out PassStoreDemo
java -cp out IoBenchmark
```
For JDBC tasks, install MariaDB/MySQL and MariaDB Connector/J 2.7.6. Run SQL scripts from `sql/`, update the database URL, username and password in the JDBC source files, then compile and run with the connector JAR. Example:
```bash
javac -cp mariadb-java-client-2.7.6.jar -d out Task-B3/LoaderBenchmark.java
java -cp out:mariadb-java-client-2.7.6.jar LoaderBenchmark
```
On Windows use `; ` instead of `:` in the Java classpath. For Task-B4 compile `Task-B4/PassMigration.java` with the connector JAR and pass the path to `Task-B4/migration-source.txt`.

## Evidence note
Benchmark and migration numbers in report text and CSV files are transcribed from the supplied Assignment 5 PDF; they are reference measurements, not newly measured here. The Java files are runnable experiments. Original screenshot files mentioned in the PDF were not separately supplied, so the SVG files are explanatory diagrams/charts, not claimed live screenshots.
