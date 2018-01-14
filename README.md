# Key-Value Database

## Implementation of Simple and Efficient Small Key-Value DataBase.

This is an in-memory key-value database implemented in JAVA. 
KVDataStore gives the follwing Interface:
- put(String key, String Value)
- get(String key)
- del(String key)

key is 256 byte String
value is 64KB binary data object

FileStore class implements the following procedures to store files in the Key Value database
- putFile(String fileName) - stores the file in the database
- getFile(String fileName) - retrieve the file form database
- delFile(String fileName) - deletes file from database
