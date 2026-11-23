# Part A — Persistence Design and Pseudocode

## A1. Bus-pass file format
One UTF-8 record per line, seven pipe-separated fields: passId, registerNo, name, routeNo, boardingStop, validUntil (ISO YYYY-MM-DD), amountPaise (integer). Example:
`P1001|192325001|Arun K|R12|Poonamallee|2027-03-31|450000`
The newline is a recovery boundary; malformed lines can be skipped without losing later records. Reject/escape a pipe in fields. For 8,000 records/year at ~57 bytes each, estimated annual storage is ~456 kB. Binary DataOutputStream has explicit types but no self-resynchronising record boundary.

### AddPass
1. Validate all seven fields.
2. If invalid, report first error and stop.
3. Open file in append mode, creating if absent.
4. Join fields with pipe; append newline.
5. Flush and close even after failure.

### FindPassById
1. If file absent, raise FileMissing.
2. Open buffered reader; set lineNo=0.
3. For each line increment lineNo; skip blank lines.
4. Split on pipe; if field count is not seven, record damage and continue.
5. Parse fields; on parse error record reason and continue.
6. If passId matches, return record; otherwise continue.
7. Close reader and return NULL.

### ListPassesOnRoute
Scan every line, parse valid records, collect matching route, skip/report damaged rows, close and return list. It cannot stop at the first match.

## A2. Buffered I/O
Use an 8,192-byte BufferedInputStream for sequential reads. Count newline bytes and close the stream. Buffering changes system-call frequency, not the result. It helps sequential small reads; it helps little for already-large reads, tiny files, double-buffering, or random seeks. Files.readAllLines is faster for a modest file when every line is needed, but consumes memory proportional to file size.

## A3. Generic DAO
`Dao<T,K>` exposes insert(T)->K, findById(K)->T/NULL, findAll()->List<T>, update(T)->boolean, delete(K)->boolean. A DAO owns connection/statement/result-set lifecycle and SQL mapping. A concrete StudentDao supplies SQL, parameter binding and row mapping. Keep SQL out of Swing/UI and service layers.
Student table mapping: register_no VARCHAR(16) primary key; name VARCHAR(80); department VARCHAR(40); year_of_study TINYINT (1–5); email VARCHAR(120) unique; fees_paid_paise BIGINT. Use paise integer rather than floating point.

## A4. Serialization
Mark live resources transient: Connection, Scanner/streams, Logger; mark password transient to avoid writing secrets in plain bytes; derived cached age may be transient. Keep identity data such as name and register number. Every reachable object must be Serializable. Shared references/cycles are preserved. Declare `serialVersionUID = 1L`; added fields read as null/default from old files and must tolerate that. Never deserialize untrusted input. Prefer JSON or a database for data crossing trust boundaries.

## A5. Transaction and SQL injection
Transfer: disable auto-commit; SELECT balance FOR UPDATE; check funds; debit source; credit destination; insert audit row; commit. On any exception rollback and rethrow; restore auto-commit and close in finally/try-with-resources. This prevents a half-transfer and the check-then-act race.
Login: never concatenate user input into SQL. Use `WHERE username=? AND password_hash=?`, bind username and a salted password hash, execute. PreparedStatement treats hostile strings as values, not SQL syntax.
