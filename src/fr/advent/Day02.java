package fr.advent;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Day02 {

    public static void main(String[] args) throws IOException {
        var entree = Util.lireEntree("day02.txt", Ligne::new);

        var position = new Position(0, 0);
        for (Ligne ligne : entree) {
            position = StrategieDeplacement.of(ligne.operateur).apply(position, ligne.i);
        }

        System.out.println(position.x * position.y);
    }

    enum StrategieDeplacement {
        FORWARD("forward", (pos, i) -> new Position(pos.x + i, pos.y)),//
        DOWN("down", (pos, i) -> new Position(pos.x, pos.y + i)),//
        UP("up", (pos, i) -> new Position(pos.x, pos.y - i));

        final String valeur;
        final BiFunction<Position, Integer, Position> operateur;

        StrategieDeplacement(final String valeur, final BiFunction<Position, Integer, Position> operateur) {
            this.valeur = valeur;
            this.operateur = operateur;
        }

        public static StrategieDeplacement of(final String operateur) {
            return Arrays.stream(StrategieDeplacement.values())//
                .filter(strategieDeplacement -> strategieDeplacement.valeur.equals(operateur))//
                .findAny()//
                .orElseThrow();
        }

        public Position apply(final Position position, final int i) {
            return operateur.apply(position, i);
        }
    }

    static class Position {
        final int x;
        final int y;

        Position(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Ligne {
        final String operateur;
        final int i;

        Ligne(final String ligne) {
            var tablal = ligne.split(" ");
            operateur = tablal[0];
            i = Integer.parseInt(tablal[1]);
        }
    }
}
