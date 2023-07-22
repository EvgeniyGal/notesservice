package edu.goit.notesservice.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class VersionHolder {
    private static final String dir = String.valueOf(Paths.get("src/main/resources/db/migration/dev").toAbsolutePath());
    private int version;

    public VersionHolder() {
        checkVersion();
    }

    public int getVersion() {
        return version;
    }

    private void checkVersion(){
        String str = lastFile();
        int before = str.indexOf("V");
        int after = str.indexOf("_");
        version = Integer.parseInt(str.substring(before+1, after))+1;
    }

    private String lastFile() {
        return Stream.of(new File(dir).listFiles())
                .max(Comparator.comparingLong(File::lastModified))
                .map(File::getName)
                .orElse("0");
    }
}
