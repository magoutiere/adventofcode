package fr.advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Util {

    public static <R> List<R> lireEntree(String file, Function<String, R> transformation) throws IOException {
        return Files.readAllLines(Paths.get("input").resolve(file))//
            .stream()//
            .map(transformation)//
            .collect(Collectors.toList());
    }

    public static List<String> lireEntree(String file) throws IOException {
        return Files.readAllLines(Paths.get("input").resolve(file));
    }
}
