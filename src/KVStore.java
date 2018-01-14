import java.util.HashMap;

public class KVStore implements DataBaseInterface {
    private HashMap<String, String> dictionary = null;

    public KeyValueDB(){
        resetStore();
    }

    private void resetStore(){
        dictionary = new HashMap<>();
    }

    public boolean put(String key, String value){
//        TODO: Implement Me!
    }

    public String get(String key){
//        TODO: Implemtnt Me!
    }

    public boolean del(String key){
//        TODO: Implement Me!
    }
}
