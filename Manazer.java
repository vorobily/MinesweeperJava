import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
Upravená trieda manažér z hodín
*/

public class Manazer {
    private Miny miny;
    private long dalsiTick;
    
    public Manazer(Miny miny) {
        this.miny = miny;
        Platno platno = Platno.dajPlatno();

        platno.addTimerListener(new ManazerCasovaca());
        platno.addMouseListener(new ManazerMysi());
    }

    private class ManazerCasovaca implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (Manazer.this.dalsiTick <= System.nanoTime()) {
                Manazer.this.miny.tik();
                Manazer.this.dalsiTick = System.nanoTime() + 1000000000;
            }
        }
    }

    private class ManazerMysi extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            switch (event.getButton()) {
                case MouseEvent.BUTTON1:
                    Manazer.this.miny.klik(event.getX(), event.getY(), true);
                    break;
                case MouseEvent.BUTTON3:
                    Manazer.this.miny.klik(event.getX(), event.getY(), false);
            }
        }
    }
}
