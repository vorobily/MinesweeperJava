import java.util.ArrayList;
import java.util.Random;

public class Mriezka extends UIPrvok {
    private Policko[][] policka;
    private ArrayList<Policko> volnePolicka;

    public Mriezka(int x, int y, int rozmer, int velkostPolicok) {
        super((velkostPolicok + 1) * rozmer + 1, (velkostPolicok + 1) * rozmer + 1, x, y);
        super.zobraz();

        this.policka = new Policko[rozmer][rozmer];
        this.volnePolicka = new ArrayList<>();
        this.generujPolicka(x, y, rozmer, velkostPolicok);
        this.najdiVolnePolicka();
    }

    public void klik(int x, int y, boolean laveTlacidlo) {
        for (Policko[] rad : this.policka) {
            for (Policko policko : rad) {
                if (policko.obsahujeSuradnice(x, y)) {
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
                if (policko.getObsahPolicka() == ObsahPolicka.VOLNE) {
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
}
