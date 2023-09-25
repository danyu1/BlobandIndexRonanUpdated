import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;

public class Commit {
    String treeSha, prevComm, author, date, summary;
    String objectsFolderPath = "bin/objects";

    public Commit(String auth, String summ) throws NoSuchAlgorithmException, IOException {

        File folder = new File(objectsFolderPath);
        if (!folder.exists())
            folder.mkdirs();
        author = auth;
        summary = summ;
        prevComm = "";
        date = setDate();
        treeSha = createTree();
        commitCommit();

    }

    public Commit(String auth, String summ, String parentSHA1) throws NoSuchAlgorithmException, IOException {

        File folder = new File(objectsFolderPath);
        if (!folder.exists())
            folder.mkdirs();
        author = auth;
        summary = summ;
        prevComm = parentSHA1;
        date = setDate();
        treeSha = createTree();
        commitCommit();
        addCurrentCommitToPreviousCommit();

    }

    private String setDate() {
        // gets unix epoch
        Date myDate = new Date();
        // converts to MON DD, YYYY
        String date = DateFormat.getDateInstance().format(myDate);
        return date;
    }

    private String createTree() throws NoSuchAlgorithmException, IOException {
        Tree tree = new Tree();
        tree.generateBlob();
        return tree.getSha1();
    }

    private String writeCommitFile() {
        StringBuilder commitFileContents = new StringBuilder();
        commitFileContents.append(treeSha + "\n"); // 1st line: SHA1 of a Tree object (never can be null)
        commitFileContents.append(prevComm + "\n"); // 2nd line: SHA1 of a file location of prev commit (can be blank)
        commitFileContents.append("\n"); // 3rd line: SHA1 of a file location of ext commit (can be blank)
        commitFileContents.append(author + "\n"); // 4th line is author
        commitFileContents.append(getDate() + "\n"); // 5th line is date
        commitFileContents.append(summary); // 6th line is summary
        return commitFileContents.toString();
    }

    private void commitCommit() throws IOException, NoSuchAlgorithmException {
        // commit wrriten to folder named 'objects'
        // name of file will be SHA1 of file contents
        String fileContents = writeCommitFile();
        String fileHash = Utils.generateSHA(fileContents);
        File file = new File(objectsFolderPath + "/" + fileHash);
        if (!file.exists())
            file.createNewFile();
        Utils.writeStringToFile(objectsFolderPath + "/" + fileHash, fileContents);
    }

    public String getDate() {
        return date;

    }

    private void addCurrentCommitToPreviousCommit() {
        // finds previous commit file from hash
        // reads file
        // rewrites file with same content as before + current commit sha in third line
    }
}
