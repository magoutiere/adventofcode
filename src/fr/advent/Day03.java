package fr.advent;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day03 {

    public static void main(String[] args) throws IOException {
        var entree = Util.lireEntree("day03.txt", s -> s.split(""));

        String oxygenGenerator = getValue(entree, "1", true);
        String CO2Scrubber = getValue(entree, "0", false);

        int oxygeneGeneratorValeur = new BigInteger(oxygenGenerator, 2).intValue();
        int co2ScrubberValeur = new BigInteger(CO2Scrubber, 2).intValue();

        System.out.println(oxygeneGeneratorValeur * co2ScrubberValeur);
    }

    public static String getValue(List<String[]> entree, String critere, boolean keepMost) {
        int column = 0;

        List<String[]> queue = new ArrayList<>(entree);

        while (queue.size() > 1) {
            int positif = 0;
            int negatif = 0;

            for (String[] ligne : queue) {
                String s = ligne[column];
                if (s.equals("1")) {
                    positif++;
                } else {
                    negatif++;
                }
            }

            String keepCriteria = critere;

            if (positif > negatif) {
                if (keepMost) {
                    keepCriteria = "1";
                } else {
                    keepCriteria = "0";
                }
            } else if (positif < negatif) {
                if (keepMost) {
                    keepCriteria = "0";
                } else {
                    keepCriteria = "1";
                }
            }

            List<String[]> keepList = new ArrayList<>();

            for (String[] ligne : queue) {
                String val = ligne[column];
                if (val.equals(keepCriteria)) {
                    keepList.add(ligne);
                }
            }
            queue = keepList;
            column++;
        }

        return String.join("", queue.get(0));
    }
}
