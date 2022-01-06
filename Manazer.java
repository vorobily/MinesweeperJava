import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Manazer {
    private Miny miny;
    private long dalsiTick;
    
    public Manazer(Miny miny) {
        this.dalsiTick = System.nanoTime();
        this.miny = miny;
        Platno platno = Platno.dajPlatno();

        platno.addTimerListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (Manazer.this.dalsiTick <= System.nanoTime()) {
                    Manazer.this.miny.tik();
                    Manazer.this.dalsiTick = System.nanoTime() + 1000000000;
                }
            }
        });

        platno.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                switch (event.getButton()) {
                    case MouseEvent.BUTTON1: //Ľavé
                        Manazer.this.miny.klik(event.getX(), event.getY(), true);
                        break;
                    case MouseEvent.BUTTON3: //Pravé
                        Manazer.this.miny.klik(event.getX(), event.getY(), false);
                }
            }
        });
    }
}
