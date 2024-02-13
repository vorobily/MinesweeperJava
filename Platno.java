import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Platno {
    
    private static Platno platnoSingleton;

    public static Platno dejPlatno() {
        if (Platno.platnoSingleton == null) {
            Platno.platnoSingleton = new Platno("Míny", Miny.SIRKA, Miny.VYSKA, Color.white);
        }
        Platno.platnoSingleton.setVisible(true);
        return Platno.platnoSingleton;
    }

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color pozadie;
    private Image canvasImage;
    private Timer timer;
    private List<Object> objekty;
    private HashMap<Object, IDraw> tvary;

    
    private Platno(String titulok, int sirka, int vyska, Color pozadie) {
        this.frame = new JFrame();
        this.canvas = new CanvasPane();
        this.frame.setContentPane(this.canvas);
        this.frame.setTitle(titulok);
        this.canvas.setPreferredSize(new Dimension(sirka, vyska));
        this.timer = new javax.swing.Timer(25, null);
        this.timer.start();
        this.pozadie = pozadie;
        this.frame.pack();
        this.objekty = new ArrayList<Object>();
        this.tvary = new HashMap<Object, IDraw>();
    }
    
    public void setVisible(boolean visible) {
        if (this.graphic == null) {
            Dimension size = this.canvas.getSize();
            this.canvasImage = this.canvas.createImage(size.width, size.height);
            this.graphic = (Graphics2D)this.canvasImage.getGraphics();
            this.graphic.setColor(this.pozadie);
            this.graphic.fillRect(0, 0, size.width, size.height);
            this.graphic.setColor(Color.black);
        }
        this.frame.setVisible(visible);
    }

    public void draw(Object objekt, String barva, Shape tvar) {
        this.objekty.remove(objekt);  
        this.objekty.add(objekt);      
        this.tvary.put(objekt, new PopisTvaru(tvar, barva));
        this.redraw();
    }
    
    public void draw(Object objekt, String text, int x, int y, int velikostPisma) { 
        this.objekty.remove(objekt);
        this.objekty.add(objekt);
        this.tvary.put(objekt, new PopisTextu(text, x, y, velikostPisma));
        this.redraw();
    } 
    
    public void draw(Object objekt, BufferedImage image, AffineTransform transform) {
        this.objekty.remove(objekt);
        this.objekty.add(objekt);
        this.tvary.put(objekt, new PopisObrazku(image, transform));
        this.redraw();
    }
    
    public void erase(Object objekt) {
        this.objekty.remove(objekt);
        this.tvary.remove(objekt);
        this.redraw();
    }
    
    public void setForegroundColor(String barva) {
        if (barva.equals("red")) {
            this.graphic.setColor(Color.red);
        } else if (barva.equals("black")) {
            this.graphic.setColor(Color.black);
        } else if (barva.equals("blue")) {
            this.graphic.setColor(Color.blue);
        } else if (barva.equals("yellow")) {
            this.graphic.setColor(Color.yellow);
        } else if (barva.equals("green")) {
            this.graphic.setColor(Color.green);
        } else if (barva.equals("magenta")) {
            this.graphic.setColor(Color.magenta);
        } else if (barva.equals("white")) {
            this.graphic.setColor(Color.white);
        } else if (barva.equals("gray")) {
            this.graphic.setColor(new Color(150, 150, 150));
        } else {
            this.graphic.setColor(Color.black);
        }
    }

    private void redraw() {
        this.erase();
        for (Object tvar : this.objekty ) {
            this.tvary.get(tvar).draw(this.graphic);
        }
        this.canvas.repaint();
    }
    
    private void erase() {
        Color original = this.graphic.getColor();
        this.graphic.setColor(this.pozadie);
        Dimension size = this.canvas.getSize();
        this.graphic.fill(new Rectangle(0, 0, size.width, size.height));
        this.graphic.setColor(original);
    }
    
    public void addMouseListener(MouseListener listener) {
        this.canvas.addMouseListener(listener);
    }
    
    public void addTimerListener(ActionListener listener) {
        this.timer.addActionListener(listener);
    }
    
    private class CanvasPane extends JPanel {
        public void paint(Graphics graphic) {
            graphic.drawImage(Platno.this.canvasImage, 0, 0, null);
        }
    }
    
    private interface IDraw {
        void draw(Graphics2D graphic);
    }
    
    private class PopisTvaru implements Platno.IDraw {
        private Shape tvar;
        private String barva;

        PopisTvaru(Shape tvar, String barva) {
            this.tvar = tvar;
            this.barva = barva;
        }

        public void draw(Graphics2D graphic) {
            Platno.this.setForegroundColor(this.barva);
            graphic.fill(this.tvar);
        }
    }

    private class PopisTextu implements Platno.IDraw {
        private String text;
        private int velikostPisma;
        private int x;
        private int y;

        PopisTextu(String text, int x, int y, int velikostPisma) {
            this.text = text;
            this.velikostPisma = velikostPisma;
            this.x = x;
            this.y = y;
        }

        public void draw(Graphics2D graphic) {
            Platno.this.setForegroundColor("black");   
            graphic.setFont(new Font("TimesRoman", Font.PLAIN, this.velikostPisma));
            graphic.drawString(this.text, this.x, this.y);
        }
    }
    
    private class PopisObrazku implements Platno.IDraw {
        private BufferedImage obrazek;
        private AffineTransform transformacia;
        
        PopisObrazku(BufferedImage obrazek, AffineTransform transformacia) {
            this.obrazek = obrazek;
            this.transformacia = transformacia;
        }
        
        public void draw(Graphics2D graphic) {
            graphic.drawImage(this.obrazek, this.transformacia, null); 
        }
    }
}
