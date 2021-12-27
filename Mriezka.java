import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Mriezka extends UIPrvok {
    private static final int[][] SMERY = new int[][] { //Konštanta pre kontrolovanie okol. políčok
        {-1, -1},
        {-1, 0},
        {-1, 1},
        {0, 1},
        {1, 1},
        {1, 0},
        {1, -1},
        {0, -1}
    };

    private Policko[][] policka;
    private ArrayList<Policko> volnePolicka;
    private HashMap<Object, UdajeOPolicku> udaje;
    private boolean uzKlikol;
    private int pocetMin;
    

    public Mriezka(int x, int y, int rozmer, int velkostPolicok, int pocetMin) {
        super((velkostPolicok + 1) * rozmer + 1, (velkostPolicok + 1) * rozmer + 1, x, y);
        super.zobraz();

        this.policka = new Policko[rozmer][rozmer];
        this.volnePolicka = new ArrayList<>();
        this.udaje = new HashMap<>();
        this.uzKlikol = false;
        this.pocetMin = pocetMin;
        this.generujPolicka(x, y, rozmer, velkostPolicok);
    }

    public void restart() {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                policko.nastavObsahPolicka(ObsahPolicka.VOLNE);
                policko.nastavObrazok(null);
                policko.nastavStav(StavPolicka.SKYRTE);
                this.uzKlikol = false;
            }
        }
    }

    public boolean klik(int x, int y, boolean laveTlacidlo) {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.obsahujeSuradnice(x, y)) {
                    if (!this.uzKlikol) {
                        if (!laveTlacidlo) {
                            return true;
                        }

                        this.najdiVolnePolicka();
                        this.volnePolicka.remove(policko); //Aby sa negenerovala mína na prvom ale zobrazilo míny v okolí
                        this.generujMiny(this.pocetMin);
                        this.prepocitajMinyVOkoli();
                        this.uzKlikol = true;
                    }

                    policko.klik(laveTlacidlo);

                    if (laveTlacidlo) {
                        //int[] suradnice = this.najdiPolicko(policko);
                        int[] suradnice = this.udaje.get(policko).getPozicie();
                        this.odhalenie(suradnice[0], suradnice[1]);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public VysledokHry skoncilaHra() {
        this.najdiVolnePolicka();
        if (this.volnePolicka.size() == 0) {
            return VysledokHry.VYHRAL;
        }

        if (this.jeOdhalenaMina()) {
            return VysledokHry.PREHRAL;
        }

        return VysledokHry.PREBIEHA;
    }

    public int getPocetMin() {
        return this.pocetMin;
    }

    public int getPocetVlajok() {
        int vlajky = 0;

        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.getStavPolicka() == StavPolicka.VLAJKA) {
                    ++vlajky;
                }
            }
        }

        return vlajky;
    }

    public void zobrazVsetkyMiny() {
        for (Policko policko : this.najdiMiny()) {
            policko.nastavObrazok(Obrazky.MINA);
        }
    }

    private ArrayList<Policko> najdiMiny() {
        ArrayList<Policko> miny = new ArrayList<>();

        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.getObsahPolicka() == ObsahPolicka.MINA) {
                    miny.add(policko);
                }
            }
        }

        return miny;
    }

    private void generujPolicka(int x, int y, int rozmer, int velkostPolicok) {
        for (int i = 0; i < rozmer; ++i) {
            for (int j = 0; j < rozmer; ++j) {
                this.policka[j][i] = new Policko(velkostPolicok, x + (i * (velkostPolicok + 1)) + 1, y + (j * (velkostPolicok + 1)) + 1);
                this.udaje.put(this.policka[j][i], new UdajeOPolicku(j, i));
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

    private boolean jeOdhalenaMina() {

        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.getObsahPolicka() == ObsahPolicka.MINA && policko.getStavPolicka() == StavPolicka.ZOBRAZENE) {
                    return true;
                }
            }
        }

        return false;
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

        for (int i = 0; i < this.policka[0].length; ++i) {
            for (int j = 0; j < this.policka[0].length; ++j) {
                int minyVOkoli = 0;
                for (Policko policko : this.getSusednePolicka(i, j)) {
                    if (policko.getObsahPolicka() == ObsahPolicka.MINA) {
                        ++minyVOkoli;
                    }
                }
                this.policka[i][j].nastavMinyVOkoli(minyVOkoli);
            }
        }
    }

    private ArrayList<Policko> getSusednePolicka(int x, int y) {

        ArrayList<Policko> policka = new ArrayList<>();

        for (int smer = 0; smer < 8; ++smer) {
            try {
                Policko policko = this.policka[x + SMERY[smer][0]][y + SMERY[smer][1]];
                policka.add(policko);
            }
            catch(ArrayIndexOutOfBoundsException exception) {
                //NIČ :)
            }
        }

        return policka;
    }

    private void odhalenie(int x, int y) {
        if (this.policka[x][y].getMinyVOkoli() != 0 || this.policka[x][y].getObsahPolicka() == ObsahPolicka.MINA) {
            return;
        }

        for (Policko policko : this.getSusednePolicka(x, y)) {
            if (policko.getStavPolicka() == StavPolicka.ZOBRAZENE) {
                continue;
            }

            policko.nastavStav(StavPolicka.ZOBRAZENE);
            //int[] suradnice = this.najdiPolicko(policko);
            int[] suradnice = this.udaje.get(policko).getPozicie();
            this.odhalenie(suradnice[0], suradnice[1]);
        }
    }

    /*
    Nahradené HashMapou

    private int[] najdiPolicko(Policko policko) {
        int[] suradnice = new int[2];

        for (int i = 0; i < this.policka[0].length; ++i) {
            for (int j = 0; j < this.policka[0].length; ++j) {
                if (this.policka[i][j] == policko) {
                    suradnice[0] = i;
                    suradnice[1] = j;
                    return suradnice;
                }
            }
        }

        return null;
    }
    */
    

    private class UdajeOPolicku {
        private int[] pozicie;

        public UdajeOPolicku(int x, int y) {
            this.pozicie = new int[2];

            this.pozicie[0] = x;
            this.pozicie[1] = y;
        }

        public int[] getPozicie() {
            return this.pozicie;
        }
    }
}
