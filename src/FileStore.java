import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class FileStore {
    private KVStore dataStore  = null;
    private static final int MAX_KEY_SIZE = 256;
    private static final int MAX_VALUE_SIZE = 64*1024;

    private HashMap<String,Integer> fileSizeMap = null;

    public FileStore(){
        dataStore = new KVStore();
        fileSizeMap = new HashMap<>();
    }

    public boolean putFile(String filename) throws IOException {
        String content = readFileAsString(filename);
        int blkNo = 0;
        for(int i=0; i<content.length();i+=MAX_VALUE_SIZE,blkNo++){
            String val = content.substring(i,Math.min(i+MAX_VALUE_SIZE,content.length()));
            String key = getKey(filename,Integer.toString(blkNo));
            if(!dataStore.put(key,val))     return false;
        }
        fileSizeMap.put(filename,blkNo);
        return true;
    }

    public String getFile(String filename){

        int noBlks = fileSizeMap.getOrDefault(filename,-1);

        // throw here exception
        if(noBlks < 0){
            System.out.println(filename+" file is not stored in the databse ");
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<noBlks; i++){
            String substring = dataStore.get(getKey(filename,Integer.toString(i)));
            if(substring != null)   sb.append(substring);
        }

        return sb.toString();
    }


    /**
     * readFileAsString reads file names fineName and returns it's content as a String
     * @param fileName File to be read
     * @return Content of file as a String
     * @throws IOException if file not present
     */
    private String readFileAsString(String fileName) throws IOException{
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            System.out.println("File could not open");
            e.printStackTrace();
        }
        return data;
    }

    /**
     * getKey calculates the hashvalue of the filename+blkNo and returns key to store that block in database
     * @param filename
     * @param blkNo
     * @return string of 256 bytes which is key of block in database
     */
    private String getKey(String filename, String blkNo){
        String rawKey = filename + blkNo;

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        md.update(rawKey.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
