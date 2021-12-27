import javax.swing.JOptionPane;
import java.util.ArrayList;
public class Miny {
    private Mriezka mriezka;
    private Manazer manazer;
    private Casovac casovac;
    private Displej displejMin;

    private ArrayList<Tlacidlo> tlacidla;

    private int pocetMin;
    private boolean hraSa;
    
    public Miny(int rozmer, int velkostPolicok, int pocetMin) {
        //Centrovanie hracej oblasti
        int polohaX = 400 - (rozmer * (velkostPolicok + 1) + 1) / 2;
        int polohaY = 300 - (rozmer * (velkostPolicok + 1) + 1) / 2;

        this.mriezka = new Mriezka(polohaX, polohaY, rozmer, velkostPolicok, pocetMin);
        this.manazer = new Manazer();
        this.casovac = new Casovac(5, 5, 25);
        this.displejMin = new Displej(720, 5, 25, -1);
        this.pocetMin = pocetMin;
        this.aktualizujDisplej(this.pocetMin);
        this.tlacidla = new ArrayList<>();

        this.registrujTlacidlo(new Tlacidlo(75, 25, 50, 200, "Reštart", 20, Tlacidla.RESTART));
        this.registrujTlacidlo(new Tlacidlo(95, 25, 50, 250, "Nápoveda", 20, Tlacidla.NAPOVEDA));
        this.registrujTlacidlo(new Tlacidlo(70, 25, 50, 300, "Koniec", 20, Tlacidla.KONIEC));

        this.hraSa = true;
        this.manazer.spravujObjekt(this);
    }

    public void klik(int x, int y, boolean laveTlacidlo) {
        if (this.hraSa) { //Ak prebieha hra
            if (this.mriezka.klik(x, y, laveTlacidlo)) { //Ak klikol na políčko
                VysledokHry vysledok = this.mriezka.skoncilaHra();

                if (vysledok != VysledokHry.PREBIEHA) {
                    this.hraSa = false;
                    this.mriezka.zobrazVsetkyMiny();
                    JOptionPane.showMessageDialog(null, this.formatujTextKoncaHry(vysledok), vysledok.getVyhernaSprava(), JOptionPane.INFORMATION_MESSAGE);
                }

                int zostava = this.mriezka.getPocetMin() - this.mriezka.getPocetVlajok();
                this.aktualizujDisplej(zostava);
                return;
            }
        }

        for (Tlacidlo tlacidlo : this.tlacidla) {
            if (!tlacidlo.boloStlacene(x, y)) {
                continue;
            }
            
            switch (tlacidlo.getId()) {
                case RESTART:
                    this.casovac.vynuluj();
                    this.hraSa = true;
                    this.mriezka.restart();
                    this.aktualizujDisplej(this.pocetMin);
                    break;
                case NAPOVEDA:
                    JOptionPane.showMessageDialog(null, "Lavé tlačidlo myši: Odhalenie políčka\nPravé tlačidlo myši: Položenie vlajky", "Nápoveda", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case KONIEC:
                    System.exit(0);    
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

    private String formatujTextKoncaHry(VysledokHry vysledok) {
        return String.format("%s, tvoj čas bol %s", vysledok.getVyhernaSprava(), this.casovac.getFromatovanyCas());
    }
}
