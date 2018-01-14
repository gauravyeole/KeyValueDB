/**
 * Key-Value store database get put del implementation
 * @author Gaurav Yeole
 */
import java.util.HashMap;

public class KVStore implements DataBaseInterface {
    private HashMap<String, String> dictionary = null;

    public KVStore(){
        resetStore();
    }

    private void resetStore(){
        dictionary = new HashMap<>();
    }

    @Override
    public boolean put(String key, String value){
          this.dictionary.put(key, value);
          String existingValue = get(key);
          if(existingValue == null) {
              return false;
          }
          return true;
    }

    @Override
    public String get(String key){
        return this.dictionary.getOrDefault(key,null);
    }

    @Override
    public void del(String key){
        this.dictionary.remove(key);
    }
}
