package br.com.api;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface FilePath {

    public final Path root = Paths.get("uploads");
}
