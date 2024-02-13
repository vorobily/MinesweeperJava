import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Miny {
    public static final int VYSKA = 600;
    public static final int SIRKA = 800;

    private Mrizka mrizka; // resetka
    private Casovac casovac;
    private Displej displejMin;
    private Obrazek pozadi;

    private ArrayList<Tlacidlo> tlacidla;

    private boolean hraProbiha;

    private int pocetMin;
    private int rozmer;
    private String jmeno;
    private String cas;
    private String result;

    static final String jdbcURL = "jdbc:postgresql://localhost:5432/***";
    static final String username = "***";
    static final String password = "***";

    public Miny() {
        this.pocetMin = Udaje.MINY.getPocatecniHodnota();
        this.rozmer = Udaje.ROZMER.getPocatecniHodnota();

        /* Pozadi */
        this.pozadi = new Obrazek(Obrazky.POZADI.getCesta());
        this.pozadi.zmenvelikost(Miny.SIRKA, Miny.VYSKA);
        this.pozadi.zmenPolohu(Miny.SIRKA / 2, Miny.VYSKA / 2);
        this.pozadi.zobraz();

        /* Rozhrani */
        this.vytvorMrizku(rozmer);
        this.casovac = new Casovac(5, 5, 25);
        this.displejMin = new Displej(720, 5, 25, -1);
        this.aktualizujDisplej(this.pocetMin);
        this.tlacidla = new ArrayList<>();
        this.generujTlacidla();

        this.SQLconnection();

        this.hraProbiha = true;
        new Manazer(this);
    }

    public void tik() {
        if (this.hraProbiha) {
            this.casovac.pridej();
        }
    }
    
    public void klik(int x, int y, boolean leveTlacidlo) {
        if (this.hraProbiha) {
            if (this.mrizka.klik(x, y, leveTlacidlo)) {
                VysledekHry vysledok = this.mrizka.skoncilaHra();
                
                if (vysledok != VysledekHry.PROBIHA) {
                    this.hraProbiha = false;
                    this.mrizka.zobrazVsetkyMiny();

                    this.result = vysledok.getVyhernaZprava();

                    JOptionPane.showMessageDialog(null, this.formatujTextKoncaHry(vysledok), vysledok.getVyhernaZprava(), JOptionPane.INFORMATION_MESSAGE);
                    this.SQLvkladani();
                }
                int zustava = this.mrizka.getPocetMin() - this.mrizka.getPocetVlajek();
                this.aktualizujDisplej(zustava);
                return;
            }
        }
        
        for (Tlacidlo tlacidlo : this.tlacidla) {
            if (!tlacidlo.byloStlaceno(x, y)) {
                continue;
            }
            
            this.handleTlacidlo(tlacidlo.getId());

            return;
        }
    }

    /* Private */

    private void handleTlacidlo(Tlacidla tlacidlo) {
        switch (tlacidlo) {
            case NOVA:
                this.novaHra();
                break;
            case RESTART:
                this.casovac.vynuluj();
                this.hraProbiha = true;
                this.mrizka.restart();
                this.aktualizujDisplej(this.pocetMin);
                break;
            case NAPOVEDA:
                JOptionPane.showMessageDialog(null, "Levé tlačitko myši: Odhalení políčka\nPravé tlačitko myši: Vložení vlajky", "Nápověda", JOptionPane.INFORMATION_MESSAGE);
                break;
            case KONEC:
                System.exit(0);   
        }
    }

    private void generujTlacidla() {
        for (int i = 0; i < Tlacidla.values().length; ++i) {
            this.registrujTlacidlo(new Tlacidlo(Tlacidla.values()[i].getSirka(), 25, 50, 190 + (50 * i), Tlacidla.values()[i].getText(), 20, Tlacidla.values()[i]));
        }
    }
    
    private void vytvorMrizku(int rozmer) {
        if (this.mrizka != null) {
            this.mrizka.skryt();
        }
        int velikostPolicek = 350 / (rozmer + 1);

        int polohaX = Miny.SIRKA / 2 - (rozmer * (velikostPolicek + 1) + 1) / 2;
        int polohaY = Miny.VYSKA / 2 - (rozmer * (velikostPolicek + 1) + 1) / 2;
        
        this.mrizka = new Mrizka(polohaX, polohaY, rozmer, velikostPolicek, this.pocetMin);
    }


    private void aktualizujDisplej(int zustava) {
        if (zustava > 99) {
            this.displejMin.zobraz(99);
        } else if (zustava < 0) {
            this.displejMin.zobraz(0);
        } else {
            this.displejMin.zobraz(zustava);
        }
    }

    private void registrujTlacidlo(Tlacidlo tlacidlo) {
        this.tlacidla.add(tlacidlo);
    }

    private String formatujTextKoncaHry(VysledekHry vysledok) {
        return String.format("%s, tvůj čas byl %s", vysledok.getVyhernaZprava(), this.casovac.getFormatovanyCas());
    }

    private void novaHra() {
        this.jmeno = JOptionPane.showInputDialog(null, "Zadej sve jmeno:");
        rozmer = this.zeptejVstup("Zadej rozmer NxN (2 - 20):", 2, 20);
        this.pocetMin = this.zeptejVstup(String.format("Zadej počet mín (1 - %d):", rozmer * rozmer - 2), 1, rozmer * rozmer - 2);

        this.vytvorMrizku(rozmer);
        this.hraProbiha = true;
        this.aktualizujDisplej(this.pocetMin);
        this.casovac.vynuluj();
    }

    private int zeptejVstup(String text, int min, int max) {
        String zadane;

        do {
            zadane = JOptionPane.showInputDialog(null, text);
        } while (!Operace.jeCislo(zadane) || Integer.parseInt(zadane) < min || Integer.parseInt(zadane) > max);

        return Integer.parseInt(zadane);
    }

    private void SQLconnection() {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to PostgreSQL");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL");
            e.printStackTrace();
        }
    }

    private void SQLvkladani() {
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             )
        {
            PreparedStatement příkaz = connection.prepareStatement("INSERT INTO Minesweeper (name, size, miny, cas, result) VALUES (?,?,?,?,?)");
            int[] novyCas = this.casovac.getCas();

            int minuty = novyCas[0];
            int sekundy = novyCas[1];

            cas = String.format("%s:%s", Operace.getHodnotaSNulou(minuty), Operace.getHodnotaSNulou(sekundy));
            příkaz.setString(1,this.jmeno);
            příkaz.setInt(2,this.rozmer);
            příkaz.setInt(3,this.pocetMin);
            příkaz.setString(4,cas);
            příkaz.setString(5,this.result);

            příkaz.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in insert to PostgreSQL");
            e.printStackTrace();
        }
    }
}
