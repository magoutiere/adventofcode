package fr.advent;

import static fr.advent.Util.lireEntree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day01 {

    public static void main(String[] args) throws IOException {
        var entree = lireEntree("day01.txt", Integer::parseInt);
        var groupes = extraireFenetresMesure(entree);
        var nbIncrement = compterIncrementMesure(groupes);

        System.out.println(nbIncrement);
    }

    private static long compterIncrementMesure(final List<Integer> groupes) {
        long count = 0L;
        for (int i = 1; i < groupes.size(); i++) {
            if (groupes.get(i) > groupes.get(i - 1)) {
                count++;
            }
        }
        return count;
    }

    private static List<Integer> extraireFenetresMesure(final List<Integer> entree) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < entree.size(); i++) {
            list.add(entree.get(i - 2) + entree.get(i - 1) + entree.get(i));
        }
        return list;
    }
}
