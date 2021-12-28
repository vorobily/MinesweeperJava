import java.awt.Rectangle;

/**
 * Obdĺžnik, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Obdlznik implements IUIPrvok {
    private int stranaA;
    private int stranaB;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;

    /**
     * Vytvor nový obdĺžnik preddefinovanej farby na preddefinovanej pozícii.
     */
    public Obdlznik(int stranaA, int stranaB, int lavyHornyX, int lavyHornyY) {
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.lavyHornyX = lavyHornyX;
        this.lavyHornyY = lavyHornyY;
        this.jeViditelny = false;
        this.farba = "black";
    }

    /**
     * (Obdĺžnik) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Obdĺžnik) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    public void zmenStrany(int stranaA, int stranaB) {
        this.zmaz();
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.nakresli();
    }
    
    public void zmenPolohu(int lavyHornyX, int lavyHornyY) {
        boolean nakresleny = this.jeViditelny;
        this.zmaz();
        this.lavyHornyX = lavyHornyX;
        this.lavyHornyY = lavyHornyY;
        if (nakresleny) {
            this.nakresli();
        }
    }

    /**
     * (Obdĺžnik) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     * biela   - "white"
     * hnedá   - "brown"
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba, new Rectangle(this.lavyHornyX, this.lavyHornyY, this.stranaA, this.stranaB));
            //canvas.wait(10);
        }
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
