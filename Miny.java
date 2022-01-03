import javax.swing.JOptionPane;
import java.util.ArrayList;
public class Miny {
    private Mriezka mriezka;
    private Casovac casovac;
    private Displej displejMin;
    private Obrazok pozadie;

    private ArrayList<Tlacidlo> tlacidla;

    private int pocetMin;
    private boolean hraSa;
    
    public Miny() {
        /* Načítanie zo systému ukládania */
        SystemUkladania ins = SystemUkladania.dajInstanciu();
        this.pocetMin = ins.getUdaj("pocetMin");
        int rozmer = ins.getUdaj("rozmer");

        /* Pozadie */
        this.pozadie = new Obrazok(Obrazky.POZADIE.getCesta());
        this.pozadie.zmenVelkost(800, 600);
        this.pozadie.zmenPolohu(400, 300);
        this.pozadie.zobraz();

        /* Rozhranie */
        this.vytvorMriezku(rozmer);
        this.casovac = new Casovac(5, 5, 25);
        this.displejMin = new Displej(720, 5, 25, -1);
        this.aktualizujDisplej(this.pocetMin);
        this.tlacidla = new ArrayList<>();
        this.generujTlacidla();

        this.hraSa = true;
        new Manazer(this);
    }

    public void tik() { //Volá sa každú sekundu
        if (this.hraSa) {
            this.casovac.pridaj();
        }
    }
    
    public void klik(int x, int y, boolean laveTlacidlo) {
        if (this.hraSa) { //Ak prebieha hra
            if (this.mriezka.klik(x, y, laveTlacidlo)) { //Ak klikol na políčko
                VysledokHry vysledok = this.mriezka.skoncilaHra();
                
                if (vysledok != VysledokHry.PREBIEHA) {
                    this.hraSa = false;
                    this.mriezka.zobrazVsetkyMiny();
                    if (vysledok == VysledokHry.VYHRAL) { 
                        this.aktualizujRekord();
                    }
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
                case NOVA:
                this.novaHra();
                break;
                case RESTART:
                this.casovac.vynuluj();
                this.hraSa = true;
                this.mriezka.restart();
                this.aktualizujDisplej(this.pocetMin);
                break;
                case NAPOVEDA:
                JOptionPane.showMessageDialog(null, "Lavé tlačidlo myši: Odhalenie políčka\nPravé tlačidlo myši: Položenie vlajky", "Nápoveda", JOptionPane.INFORMATION_MESSAGE);
                break;
                case REKORD:
                JOptionPane.showMessageDialog(null, this.formatujRekord(), "Rekord", JOptionPane.INFORMATION_MESSAGE);
                break;
                case KONIEC:
                System.exit(0);   
            }
            
            return;
        }
    }

    /* Private */

    private void generujTlacidla() {
        for (int i = 0; i < Tlacidla.values().length; ++i) {
            this.registrujTlacidlo(new Tlacidlo(Tlacidla.values()[i].getSirka(), 25, 50, 190 + (50 * i), Tlacidla.values()[i].getText(), 20, Tlacidla.values()[i]));
        }
    }
    
    private void vytvorMriezku(int rozmer) {
        if (this.mriezka != null) {
            this.mriezka.skry();
        }
        int velkostPolicok = 350 / (rozmer + 1);
        //Centrovanie hracej oblasti
        int polohaX = 400 - (rozmer * (velkostPolicok + 1) + 1) / 2;
        int polohaY = 300 - (rozmer * (velkostPolicok + 1) + 1) / 2;
        
        this.mriezka = new Mriezka(polohaX, polohaY, rozmer, velkostPolicok, this.pocetMin);
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

    private void registrujTlacidlo(Tlacidlo tlacidlo) {
        this.tlacidla.add(tlacidlo);
    }

    private String formatujTextKoncaHry(VysledokHry vysledok) {
        return String.format("%s, tvoj čas bol %s", vysledok.getVyhernaSprava(), this.casovac.getFromatovanyCas());
    }

    private String formatujRekord() {
        SystemUkladania ins = SystemUkladania.dajInstanciu();
        int minuty = ins.getUdaj("minuty");
        int sekundy = ins.getUdaj("sekundy");

        if (minuty < 0 || sekundy < 0) {
            return "Ešte nemáš žiadny osobný rekord";
        }

        return String.format("Tvoj osobný rekord je %s:%s", Operacie.getHodnotaSNulou(minuty), Operacie.getHodnotaSNulou(sekundy));
    }

    private void aktualizujRekord() {
        SystemUkladania ins = SystemUkladania.dajInstanciu();
        int[] novyCas = this.casovac.getCas();
        int minuty = ins.getUdaj("minuty");
        int sekundy = ins.getUdaj("sekundy");

        if ((novyCas[0] <= minuty && novyCas[1] < sekundy) || (minuty < 0 || sekundy < 0)) {
            ins.aktualizujUdaj("minuty", novyCas[0]);
            ins.aktualizujUdaj("sekundy", novyCas[1]);
            return;
        }
    }

    private void novaHra() {
        int rozmer = this.vypytajVstup("Zadaj rozmer NxN (0 - 30):", 0, 30);
        this.pocetMin = this.vypytajVstup(String.format("Zadaj počet mín (0 - %d):", rozmer * rozmer - 2), 0, rozmer * rozmer - 2);

        SystemUkladania.dajInstanciu().aktualizujUdaj("minuty", -1);
        SystemUkladania.dajInstanciu().aktualizujUdaj("sekundy", -1);
        SystemUkladania.dajInstanciu().aktualizujUdaj("pocetMin", this.pocetMin);
        SystemUkladania.dajInstanciu().aktualizujUdaj("rozmer", rozmer);

        this.vytvorMriezku(rozmer);
        this.hraSa = true;
        this.aktualizujDisplej(this.pocetMin);
        this.casovac.vynuluj();
    }

    private int vypytajVstup(String text, int min, int max) {
        String zadane;

        do {
            zadane = JOptionPane.showInputDialog(null, text);
        } while (!Operacie.jeCislo(zadane) || Integer.parseInt(zadane) < min || Integer.parseInt(zadane) > max);

        return Integer.parseInt(zadane);
    }
}
