import java.awt.*;

public class Obdelnik implements IUIPrvek {
    private int stranaA;
    private int stranaB;
    private int levyHornyX;
    private int levyHornyY;
    private String barva;
    private boolean jeViditelny;

    public Obdelnik(int stranaA, int stranaB, int levyHornyX, int levyHornyY) {
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.levyHornyX = levyHornyX;
        this.levyHornyY = levyHornyY;
        this.jeViditelny = false;
        this.barva = "black";
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skryt() {
        this.smaz();
        this.jeViditelny = false;
    }

    public void zmenStrany(int stranaA, int stranaB) {
        this.smaz();
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.nakresli();
    }
    
    public void zmenPolohu(int levyHornyX, int levyHornyY) {
        boolean nakresleny = this.jeViditelny;
        this.smaz();
        this.levyHornyX = levyHornyX;
        this.levyHornyY = levyHornyY;
        if (nakresleny) {
            this.nakresli();
        }
    }

    public void zmenBarva(String barva) {
        this.barva = barva;
        this.nakresli();
    }

    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dejPlatno();
            canvas.draw(this, this.barva, new Rectangle(this.levyHornyX, this.levyHornyY, this.stranaA, this.stranaB));
        }
    }

    private void smaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dejPlatno();
            canvas.erase(this);
        }
    }
}
