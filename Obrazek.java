import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obrazek implements IUIPrvek {
    private boolean jeViditelny;
    
    private int levyHornyX;
    private int levyHornyY;
    private int puvodniVelikostX;
    private int puvodniVelikostY;
    private int velikostX;
    private int velikostY;
    private double uhel;
    
    private BufferedImage obrazek;

    public Obrazek(String SouborSObrazkom) {
        this.obrazek = this.nacitejobrazekZeSouboru(SouborSObrazkom);
 
        this.jeViditelny = false;
        this.levyHornyX = 100;
        this.levyHornyY = 100;
        this.puvodniVelikostX = this.obrazek.getWidth();
        this.puvodniVelikostY = this.obrazek.getHeight();
        this.velikostX = this.puvodniVelikostX;
        this.velikostY = this.puvodniVelikostY;
        this.uhel = 0;         
    }

    public void zobraz() {      
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skryt() {
        this.smaz();
        this.jeViditelny = false;
    }                     

    public void zmenobrazek(String SouborSObrazkom) {
        boolean nakresleny = this.jeViditelny;
        this.smaz();        
        this.obrazek = this.nacitejobrazekZeSouboru(SouborSObrazkom);
        if (nakresleny) {
            this.nakresli();
        }
    }    

    public void zmenPolohu(int stredX, int stredY) {
        boolean nakresleny = this.jeViditelny;
        this.smaz();
        this.levyHornyX = stredX - this.sirka() / 2;
        this.levyHornyY = stredY - this.vyska() / 2;
        if (nakresleny) {
            this.nakresli();
        }
    } 

    public void zmenvelikost(int velikostX, int velikostY) {
        boolean nakresleny = this.jeViditelny;
        this.smaz();
        this.velikostX = velikostX;
        this.velikostY = velikostY;
        if (nakresleny) {
            this.nakresli();
        }
    }  

    private BufferedImage nacitejobrazekZeSouboru(String Soubor) {
        BufferedImage nacitanyobrazek = null;
        
        try {
            nacitanyobrazek = ImageIO.read(new File(Soubor));
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Soubor " + Soubor + " sa nenasiel.");
        }        
        
        return nacitanyobrazek;
    }     

    private int sirka() {
        return this.velikostX;
    }

    private int vyska() {
        return this.velikostY;
    }    

    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dejPlatno();
        
            AffineTransform at = new AffineTransform();
            at.translate(this.levyHornyX + this.sirka() / 2, this.levyHornyY + this.vyska() / 2);
            at.rotate(this.uhel / 180.0 * Math.PI);
            at.translate(-this.sirka() / 2, -this.vyska() / 2);
            at.scale(this.velikostX / (double)this.puvodniVelikostX, this.velikostY / (double)this.puvodniVelikostY);
            
            canvas.draw(this, this.obrazek, at);
        }
    }

    private void smaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dejPlatno();
            canvas.erase(this);
        }
    }
    
}
