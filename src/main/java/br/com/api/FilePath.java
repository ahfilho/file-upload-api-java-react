package br.com.api;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface FilePath {

    public final Path rootCpu = Paths.get("uploads/cpu");
    public final Path rootSsd = Paths.get("uploads/ssd");

    public final Path rootRam = Paths.get("uploads/ram");

}
