import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

/*
Upravená trieda manažér z hodín
*/

public class Manazer {
    private ArrayList<Object> spravovaneObjekty;
    private ArrayList<Integer> vymazaneObjekty;
    private long oldTick;
    private static final long TICK_LENGTH = 250000000;
    
    private class ManazerCasovaca implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            long newTick = System.nanoTime();
            if (newTick - Manazer.this.oldTick >= Manazer.TICK_LENGTH || newTick < Manazer.TICK_LENGTH) {
                Manazer.this.oldTick = (newTick / Manazer.TICK_LENGTH) * Manazer.TICK_LENGTH;
                Manazer.this.posliSpravu("tik");
            }
        }
    }
    
    private class ManazerMysi extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            switch (event.getButton()) {
                case MouseEvent.BUTTON1:
                    Manazer.this.posliSpravu("klik", event.getX(), event.getY(), true);
                    break;
                case MouseEvent.BUTTON3:
                    Manazer.this.posliSpravu("klik", event.getX(), event.getY(), false);
            }
        }
    }
    
    private void posliSpravu(String selektor) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                if (adresat != null) {                    
                    Method sprava = adresat.getClass().getMethod(selektor);
                    sprava.invoke(adresat);
                }
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
        this.removeDeletedObjects();
    }
    
    private void posliSpravu(String selektor, int prvyParameter, int druhyParameter, boolean laveTlacidlo) {
        for (Object adresat : this.spravovaneObjekty) {
            try {
                if (adresat != null) {
                    Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE, Integer.TYPE, Boolean.TYPE);
                    sprava.invoke(adresat, prvyParameter, druhyParameter, laveTlacidlo);
                }
            } catch (SecurityException e) {
                this.doNothing();
            } catch (NoSuchMethodException e) {
                this.doNothing();
            } catch (IllegalArgumentException e) {
                this.doNothing();
            } catch (IllegalAccessException e) {
                this.doNothing();
            } catch (InvocationTargetException e) {
                this.doNothing();
            }
        }
        this.removeDeletedObjects();
    }
    
    private void doNothing() {
        
    }
    
    private void removeDeletedObjects() {
        if (this.vymazaneObjekty.size() > 0) {
            Collections.sort(this.vymazaneObjekty, Collections.reverseOrder());
            for (int i = this.vymazaneObjekty.size() - 1; i >= 0; i--) {
                this.spravovaneObjekty.remove(this.vymazaneObjekty.get(i));
            }
            this.vymazaneObjekty.clear();
        }        
    }
    
    /**
     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
     */
    public Manazer() {
        this.spravovaneObjekty = new ArrayList<Object>();
        this.vymazaneObjekty = new ArrayList<Integer>();
        Platno.dajPlatno().addTimerListener(new ManazerCasovaca());
        Platno.dajPlatno().addMouseListener(new ManazerMysi());
    }
    
    /**
     * Manazer bude spravovat dany objekt.
     */
    public void spravujObjekt(Object objekt) {
        this.spravovaneObjekty.add(objekt);
    }
    
    /**
     * Manazer prestane spravovat dany objekt.
     */
    public void prestanSpravovatObjekt(Object objekt) {
        int index = this.spravovaneObjekty.indexOf(objekt);
        if (index >= 0) {
            this.spravovaneObjekty.set(index, null);
            this.vymazaneObjekty.add(index);
        }
    }
}
