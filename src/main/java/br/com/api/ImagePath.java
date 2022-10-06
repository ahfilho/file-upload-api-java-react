package br.com.api;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface ImagePath {

    public final Path root = Paths.get("uploads");
}
