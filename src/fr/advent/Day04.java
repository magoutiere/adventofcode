package fr.advent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 {

    public static final int TAILLE_PLATEAU = 5;

    public static void main(String[] args) throws IOException {
        var entree = Util.lireEntree("day04.txt");

        var tirages = Arrays.stream(entree.get(0).split(",")).toList();
        var plateaux = lirePlateaux(entree);

        var score = recupererScorePlateauGagnant(tirages, plateaux);

        System.out.println(score);
    }

    private static int recupererScorePlateauGagnant(final List<String> tirages, final List<PlateauBingo> plateaux) {
        PlateauBingo plateauBingoGagnant = null;

        for (String tirage : tirages) {
            var plateauxPerdants = plateaux.stream().filter(PlateauBingo::estPerdant).toList();
            if (plateauxPerdants.size() == 1){
                plateauBingoGagnant = plateauxPerdants.get(0);
            }

            plateauxPerdants.forEach(plateauBingo -> plateauBingo.jouer(tirage));

            if (plateaux.stream().noneMatch(PlateauBingo::estPerdant)) {
                return plateauBingoGagnant.score() * Integer.parseInt(tirage);
            }
        }
        throw new IllegalStateException("Pas de gagnant ?!");
    }

    private static List<PlateauBingo> lirePlateaux(final List<String> entree) {
        List<PlateauBingo> plateaux = new ArrayList<>();

        String[][] lecturePlateau = new String[TAILLE_PLATEAU][TAILLE_PLATEAU];
        int i = 0;
        for (String ligne : entree.subList(2, entree.size())) {
            if (ligne.isBlank()) {
                i = 0;
                plateaux.add(new PlateauBingo(lecturePlateau));
                lecturePlateau = new String[TAILLE_PLATEAU][TAILLE_PLATEAU];
            } else {
                lecturePlateau[i] = ligne.trim().split("[ ]+");
                i++;
            }
        }
        plateaux.add(new PlateauBingo(lecturePlateau));
        return plateaux;
    }

    private static final class PlateauBingo {
        final String[][] plateau;
        boolean[][] mark = new boolean[TAILLE_PLATEAU][TAILLE_PLATEAU];

        private PlateauBingo(String[][] plateau) {
            this.plateau = plateau;
        }

        public void jouer(final String tirage) {
            for (int i = 0; i < plateau.length; i++) {
                for (int j = 0; j < plateau[i].length; j++) {
                    mark[i][j] = mark[i][j] || tirage.equals(plateau[i][j]);
                }
            }
        }

        public boolean estPerdant(){
            return !estGagnant();
        }

        public boolean estGagnant() {
            for (int i = 0; i < TAILLE_PLATEAU; i++) {
                boolean ligneGagnante = true;
                boolean colonneGagnante = true;

                for (int j = 0; j < TAILLE_PLATEAU; j++) {
                    ligneGagnante &= mark[i][j];
                }
                for (int j = 0; j < TAILLE_PLATEAU; j++) {
                    colonneGagnante &= mark[j][i];
                }

                if (ligneGagnante || colonneGagnante) {
                    return true;
                }
            }
            return false;
        }

        public int score() {
            int score = 0;
            for (int i = 0; i < TAILLE_PLATEAU; i++) {
                for (int j = 0; j < TAILLE_PLATEAU; j++) {
                    if (!mark[i][j]) {
                        score += Integer.parseInt(plateau[i][j]);
                    }
                }
            }
            return score;
        }
    }
}
