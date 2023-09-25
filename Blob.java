import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Blob {
    /*
     * Note: modified code regarding path usage for mac compatibility, commented out
     * windows
     */
    public static String pathToWorkSpace = "C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\objects\\"; // for
                                                                                                                                     // windows
    // public static String pathToWorkSpace = "./bin/objects/"; // for mac
    String hashed;
    boolean isWindows;

    public Blob(String string) {

    }

    public Blob(boolean isWindows) {
        this.isWindows = isWindows;
    }

    public static String blob(String inputFile) throws IOException, NoSuchAlgorithmException {
        // File file = new File(pathToWorkSpace + "\\" + inputFile); //for windows
        File file = new File(pathToWorkSpace + inputFile);// for mac, should also potentially work for windows
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            sb.append(line).append("");
        }
        reader.close();
        String hashed = Utils.generateSHA(sb.toString());
        write(hashed, sb);
        return hashed;
    }

    public String getHash() {
        return hashed;
    }

    private static void write(String hashed, StringBuilder inside) throws IOException {
        String newFile = hashed;
        FileWriter write = new FileWriter(pathToWorkSpace + newFile); // should work for both mac and windows
        write.write(inside.toString());
        write.close();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String file = "input.txt";
        blob(file);
    }
}