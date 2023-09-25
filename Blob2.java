import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Blob2 {
    String objectsFolderPath, hashed;
    boolean isMac = false;

    public Blob2(String inputFile) throws IOException, NoSuchAlgorithmException {
        objectsFolderPath = "C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\bin\\objects";
        File file = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            sb.append(line).append("");
        }
        reader.close();
        hashed = Utils.generateSHA(sb.toString());
        write(hashed, sb);
    }

    public Blob2(String inputFile, boolean isMac) throws IOException, NoSuchAlgorithmException {
        this.isMac = isMac;
        if (isMac)
            objectsFolderPath = "./bin/objects";
        else
            objectsFolderPath = "C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\bin\\objects";
        File file = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            sb.append(line).append("");
        }
        reader.close();
        hashed = Utils.generateSHA(sb.toString());
        write(hashed, sb);
    }

    public String getHash() {
        return hashed;
    }

    private void write(String hashed, StringBuilder inside) throws IOException {
        String newFile = hashed;
        FileWriter write = new FileWriter(objectsFolderPath + newFile);
        write.write(inside.toString());
        write.close();
    }
}