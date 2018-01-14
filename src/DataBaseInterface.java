/*
* Abstract Interface fot Key-Value Store Database
* @author Gaurav Yeole
*
* */

public interface DataBaseInterface {

    /**
     * Insert Key, in key-value store database
     * @param key is the  key of the object to be stored in the database
     * @param value is the value associated withe key
     * @return whether key value successfully stored in the database
     */
    public boolean put(String key, String value);

    /**
     * Get the value associated with key and stored in the database
     * @param key is the key of the object stored in the database
     * @return is the corresponding the input key stored in the database
     */
    public String get(String key);

    /**
     * Delete the object of given key from the database
     * @param key is the object whose entry needs to be deleted from the database
     * @return whether delete operation was successful
     */
    public void del(String key);
}
