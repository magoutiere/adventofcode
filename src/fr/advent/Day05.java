package fr.advent;

import java.io.IOException;
import java.util.Arrays;

public class Day05 {

    public static final int MAPSIZE = 1000;
    
    public static void main(String[] args) throws IOException {
        var lines = Util.lireEntree("day05.txt", ligne -> {
            var entreePositions = ligne.split(" -> ");
            var debutArr = entreePositions[0].split(",");
            var finArr = entreePositions[1].split(",");
            var debut = new Pos(Integer.parseInt(debutArr[0]), Integer.parseInt(debutArr[1]));
            var fin = new Pos(Integer.parseInt(finArr[0]), Integer.parseInt(finArr[1]));
            return new Line(debut, fin);
        });

        byte[] map = initialiserByteMap();

        lines.forEach(line -> line.apply(map));

        int count = 0;
        for (byte b : map) {
            if(b > 1) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static byte[] initialiserByteMap() {
        byte[] map = new byte[MAPSIZE * MAPSIZE];
        Arrays.fill(map, (byte)0);
        return map;
    }

    record Line(Pos debut, Pos fin){
        public void apply(byte[] map) {
            if (debut.x == fin.x) {
                for (int i = Math.min(debut.y, fin.y); i <= Math.max(debut.y, fin.y); i++) {
                    map[MAPSIZE * i + debut.x]++;
                }
            } else if (debut.y == fin.y) {
                for (int i = Math.min(debut.x, fin.x); i <= Math.max(debut.x, fin.x); i++) {
                    map[MAPSIZE * debut.y + i]++;
                }
            } else {
                if (debut.y < fin.y && debut.x < fin.x) {
                    for (int i = 0; i <= fin.y - debut.y; i++) {
                        map[MAPSIZE * (debut.y + i) + (debut.x + i)]++;
                    }
                } else if (debut.y > fin.y && debut.x > fin.x) {
                    for (int i = 0; i <= debut.y - fin.y; i++) {
                        map[MAPSIZE * (debut.y - i) + (debut.x - i)]++;
                    }
                } else if (debut.y < fin.y) {
                    for (int i = 0; i <= fin.y - debut.y; i++) {
                        map[MAPSIZE * (debut.y + i) + (debut.x - i)]++;
                    }
                } else {
                    for (int i = 0; i <= fin.x - debut.x; i++) {
                        map[MAPSIZE * (debut.y - i) + (debut.x + i)]++;
                    }
                }
            }
        }
    }

    record Pos(int x, int y){}
}
