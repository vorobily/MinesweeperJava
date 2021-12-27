import javax.swing.JOptionPane;
import java.util.ArrayList;
public class Miny {
    private Mriezka mriezka;
    private Manazer manazer;
    private Casovac casovac;
    private Displej displejMin;
    private Tlacidlo tlacidloRestart;

    private ArrayList<Tlacidlo> tlacidla;

    private boolean hraSa;
    
    public Miny(int rozmer, int velkostPolicok, int pocetMin) {
        //Centrovanie hracej oblasti
        int polohaX = 400 - (rozmer * (velkostPolicok + 1) + 1) / 2;
        int polohaY = 300 - (rozmer * (velkostPolicok + 1) + 1) / 2;

        this.mriezka = new Mriezka(polohaX, polohaY, rozmer, velkostPolicok, pocetMin);
        this.manazer = new Manazer();
        this.casovac = new Casovac(5, 5, 25);
        this.displejMin = new Displej(720, 5, 25, -1);
        this.aktualizujDisplej(0);
        this.tlacidla = new ArrayList<>();

        this.tlacidloRestart = new Tlacidlo(75, 25, 50, 200, "Reštart", 20, Tlacidla.RESTART);
        this.registrujTlacidlo(tlacidloRestart);

        this.hraSa = true;
        this.manazer.spravujObjekt(this);
    }

    public void klik(int x, int y, boolean laveTlacidlo) {
        if (this.hraSa) { //Ak prebieha hra
            if (this.mriezka.klik(x, y, laveTlacidlo)) { //Ak klikol na políčko
                switch (this.mriezka.skoncilaHra()) { //Ak skončila hra
                    case PREBIEHA:
                        break;
                    case VYHRAL:
                        this.hraSa = false;
                        this.mriezka.zobrazVsetkyMiny();
                        JOptionPane.showMessageDialog(null, "Vyhral si");
                        break;
                    case PREHRAL:
                        this.hraSa = false;
                        this.mriezka.zobrazVsetkyMiny();
                        JOptionPane.showMessageDialog(null, "Prehral si");
                }
                int zostava = this.mriezka.getPocetMin() - this.mriezka.getPocetVlajok();
                this.aktualizujDisplej(zostava);
                return;
            }
        }

        for (Tlacidlo tlacidlo : this.tlacidla) {
            if (!tlacidlo.obsahujeSuradnice(x, y)) {
                continue;
            }
            
            switch (tlacidlo.getId()) {
                case RESTART:
                    this.casovac.vynuluj();
                    this.hraSa = true;
                    this.mriezka.restart();
                    this.aktualizujDisplej(0);
            }

            return;
        }
    }

    private void aktualizujDisplej(int zostava) { //Stará sa o správne zobrazovanie
        if (zostava > 99) {
            this.displejMin.zobraz(99);
        } else if (zostava < 0) {
            this.displejMin.zobraz(0);
        } else {
            this.displejMin.zobraz(zostava);
        }
    }

    public void tik() { //Volá sa každú sekundu
        if (this.hraSa) {
            this.casovac.pridaj();
        }
    }

    private void registrujTlacidlo(Tlacidlo tlacidlo) {
        this.tlacidla.add(tlacidlo);
    }
}
