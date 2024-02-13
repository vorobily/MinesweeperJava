import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mrizka extends UIPrvek {
    private static final int[][] SMERY = new int[][] {
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
    private boolean uzKlikl;
    private int pocetMin;
    private boolean jeViditelny;
    

    public Mrizka(int x, int y, int rozmer, int velikostPolicek, int pocetMin) {
        super((velikostPolicek + 1) * rozmer + 1, (velikostPolicek + 1) * rozmer + 1, x, y);
        super.zobraz();

        this.policka = new Policko[rozmer][rozmer];
        this.volnePolicka = new ArrayList<>();
        this.udaje = new HashMap<>();
        this.uzKlikl = false;
        this.pocetMin = pocetMin;
        this.generujPolicka(x, y, rozmer, velikostPolicek);
        this.jeViditelny = true;
    }

    @Override
    public void skryt() {
        super.skryt();
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                policko.skryt();
            }
        }
        this.jeViditelny = false;
    }

    @Override
    public void zobraz() {
        super.zobraz();
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                policko.zobraz();
            }
        }
        this.jeViditelny = true;
    }

    public void restart() {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                policko.nastavObsahPolicka(ObsahPolicka.VOLNE);
                policko.nastavobrazek(null);
                policko.nastavStav(StavPolicka.SKRYTE);
                this.uzKlikl = false;
            }
        }
    }

    public boolean klik(int x, int y, boolean leveTlacidlo) {
        if (!this.jeViditelny) {
            return false;
        }

        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.obsahujesouradnice(x, y)) {
                    if (!this.uzKlikl) {
                        if (!leveTlacidlo) {
                            return true;
                        }

                        this.najdiVolnePolicka();
                        this.volnePolicka.remove(policko); //Aby se negenerovala mína na prvem ale zobrazily míny v okolí
                        this.generujMiny(this.pocetMin);
                        this.prepocitajMinyVOkoli();
                        this.uzKlikl = true;
                    }

                    policko.klik(leveTlacidlo);

                    if (leveTlacidlo) {
                        int[] souradnice = this.udaje.get(policko).getPozice();
                        this.odhaleni(souradnice[0], souradnice[1]);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public VysledekHry skoncilaHra() {
        this.najdiVolnePolicka();
        if (this.volnePolicka.size() == 0) {
            return VysledekHry.VYHRAL;
        }

        if (this.jeOdhalenaMina()) {
            return VysledekHry.PROHRAL;
        }

        return VysledekHry.PROBIHA;
    }

    public int getPocetMin() {
        return this.pocetMin;
    }

    public int getPocetVlajek() {
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
            if (policko.getStavPolicka() == StavPolicka.ZOBRAZENE) {
                policko.nastavobrazek(Obrazky.MINAVYB);
            } else {
                policko.nastavobrazek(Obrazky.MINA);
            }
        }
    }

    /* Private */

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

    private void generujPolicka(int x, int y, int rozmer, int velikostPolicek) {
        for (int i = 0; i < rozmer; ++i) {
            for (int j = 0; j < rozmer; ++j) {
                this.policka[j][i] = new Policko(velikostPolicek, x + (i * (velikostPolicek + 1)) + 1, y + (j * (velikostPolicek + 1)) + 1);
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
        if (pocet >= this.volnePolicka.size()) {
            pocet = this.volnePolicka.size() - 1; //Aby bylo vždy aspoň 1 volné políčko
        }

        for (int i = 0; i < pocet; ++i) {
            this.volnePolicka.remove(new Random().nextInt(this.volnePolicka.size())).nastavObsahPolicka(ObsahPolicka.MINA);
        }
    }

    private void prepocitajMinyVOkoli() {

        for (int i = 0; i < this.policka[0].length; ++i) {
            for (int j = 0; j < this.policka[0].length; ++j) {
                int minyVOkoli = 0;
                for (Policko policko : this.getSousednePolicka(i, j)) {
                    if (policko.getObsahPolicka() == ObsahPolicka.MINA) {
                        ++minyVOkoli;
                    }
                }
                this.policka[i][j].nastavMinyVOkoli(minyVOkoli);
            }
        }
    }

    private ArrayList<Policko> getSousednePolicka(int x, int y) {

        ArrayList<Policko> zaznamPolicek = new ArrayList<>();

        for (int smer = 0; smer < 8; ++smer) {
            try {
                Policko policko = this.policka[x + SMERY[smer][0]][y + SMERY[smer][1]];
                zaznamPolicek.add(policko);
            } catch (ArrayIndexOutOfBoundsException exception) {
                ExceptionHandler.handleException(exception, true);
            }
        }

        return zaznamPolicek;
    }

    private void odhaleni(int x, int y) {
        if (this.policka[x][y].getMinyVOkoli() != 0 || this.policka[x][y].getObsahPolicka() == ObsahPolicka.MINA) {
            return;
        }

        for (Policko policko : this.getSousednePolicka(x, y)) {
            if (policko.getStavPolicka() == StavPolicka.ZOBRAZENE) {
                continue;
            }

            policko.nastavStav(StavPolicka.ZOBRAZENE);
            int[] souradnice = this.udaje.get(policko).getPozice();
            this.odhaleni(souradnice[0], souradnice[1]);
        }
    }

    private class UdajeOPolicku {
        private int[] Pozice;

        UdajeOPolicku(int x, int y) {
            this.Pozice = new int[2];

            this.Pozice[0] = x;
            this.Pozice[1] = y;
        }

        public int[] getPozice() {
            return this.Pozice;
        }
    }
}
