import java.io.IOException;

public class KeyValueDB {


    public static void main(String[] args){

        FileStore fileStore = new FileStore();
        String fileName = "/Users/gauravyeole/Downloads/EEL6905/KeyValueDB/src/myfile.txt";

        try {
            fileStore.putFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(fileStore.getFile(fileName));
    }
}

