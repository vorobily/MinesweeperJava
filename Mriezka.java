import java.util.ArrayList;
import java.util.Random;

public class Mriezka extends UIPrvok {
    private Policko[][] policka;
    private ArrayList<Policko> volnePolicka;
    private boolean uzKlikol;

    public Mriezka(int x, int y, int rozmer, int velkostPolicok) {
        super((velkostPolicok + 1) * rozmer + 1, (velkostPolicok + 1) * rozmer + 1, x, y);
        super.zobraz();

        this.policka = new Policko[rozmer][rozmer];
        this.volnePolicka = new ArrayList<>();
        this.uzKlikol = false;
        this.generujPolicka(x, y, rozmer, velkostPolicok);
    }

    public void klik(int x, int y, boolean laveTlacidlo) {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.obsahujeSuradnice(x, y)) {
                    if (!this.uzKlikol) {
                        this.najdiVolnePolicka();
                        this.volnePolicka.remove(policko); //Aby sa negenerovala mína na prvom ale zobrazilo míny v okolí
                        this.generujMiny(20);
                        this.prepocitajMinyVOkoli();
                        this.uzKlikol = true;
                    }

                    policko.klik(laveTlacidlo);
                    return;
                }
            }
        }
    }

    private void generujPolicka(int x, int y, int rozmer, int velkostPolicok) {
        for (int i = 0; i < rozmer; ++i) {
            for (int j = 0; j < rozmer; ++j) {
                this.policka[j][i] = new Policko(velkostPolicok, x + (i * (velkostPolicok + 1)) + 1, y + (j * (velkostPolicok + 1)) + 1);
            }
        }
    }

    private void najdiVolnePolicka() {
        this.volnePolicka.clear();
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.getObsahPolicka() == ObsahPolicka.VOLNE && policko.getStavPolicka() != StavPolicka.ZOBRAZENE) {
                    this.volnePolicka.add(policko);
                }
            }
        }
    }

    private void generujMiny(int pocet) {
        if(pocet >= this.volnePolicka.size()) {
            pocet = this.volnePolicka.size() - 1; //Aby bolo vždy aspoň 1 volné políčko
        }

        for (int i = 0; i < pocet; ++i) {
            this.volnePolicka.remove(new Random().nextInt(this.volnePolicka.size())).nastavObsahPolicka(ObsahPolicka.MINA);
        }
    }

    private void prepocitajMinyVOkoli() {
        System.out.println("test");
        int[][] smery = new int[][] {
            {-1, -1},
            {-1, 0},
            {-1, 1}, 
            {0, 1},
            {1, 1}, 
            {1, 0}, 
            {1, -1}, 
            {0, -1}
        };

        for (int i = 0; i < this.policka[0].length; ++i) {
            for (int j = 0; j < this.policka[0].length; ++j) {
                int minyVOkoli = 0;
                for (int smer = 0; smer < 8; ++smer) {
                    try {
                        if (this.policka[i + smery[smer][0]][j + smery[smer][1]].getObsahPolicka() == ObsahPolicka.MINA) {
                            minyVOkoli++;
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException exception) {
                        //NIČ :)
                    }
                }
                this.policka[i][j].nastavMinyVOkoli(minyVOkoli);
            }
        }
    }
}
