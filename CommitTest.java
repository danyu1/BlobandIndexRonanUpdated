import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CommitTest {
    static Commit commit1;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        Path path = Paths.get("bin/Index/");
        File directory = new File("bin/objects/");
        for (File subfile : directory.listFiles()) {
            subfile.delete();
        }
        Files.deleteIfExists(path);
        java.nio.file.Path folderPath = Paths
                .get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\objects");
        if (!Files.exists(folderPath)) {
            Files.createDirectory(folderPath);
        }

        Path indexPath = Paths
                .get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\index");
        if (!Files.exists(indexPath)) {
            Files.createFile(indexPath);
        }
        commit1 = new Commit("Arden", "Commit test test thingy");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        File directory = new File(
                "C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\objects");
        for (File subfile : directory.listFiles()) {
            subfile.delete();
        }
        Path path = Paths.get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\objects");
        Files.deleteIfExists(path);
        Path path2 = Paths.get("C:\\Users\\danie\\OneDrive\\Desktop\\Topics Repos\\BlobandIndexRonanUpdated\\index");
        Files.deleteIfExists(path2);
         Path path3 = Paths.get("bin/Index/");
        File directory2 = new File("bin/objects/");
        for (File subfile : directory2.listFiles()) {
            subfile.delete();
        }
        Files.deleteIfExists(path3);
    }

    @Test
    void testGetDate() {
        String currDate = "Sep 24, 2023"; // needs to change each day el oh el
        String myDate = commit1.getDate();
        assertEquals(currDate, myDate);
    }

}
