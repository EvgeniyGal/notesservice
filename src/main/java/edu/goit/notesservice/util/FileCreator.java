package edu.goit.notesservice.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FileCreator {
    private File file;

    public FileCreator(String statement) throws IOException {
        int version = new VersionHolder().getVersion();
        createFile(version);
        write(statement);
    }

    private void createFile(int version) throws IOException {
        file = new File(Paths.get("src/main/resources/db/migration/dev").toAbsolutePath() + "/V" + version + "__new_user.sql");
        file.createNewFile();
    }

    private void write(String statement) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(statement);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (writer != null) writer.close();
        }
    }
}
