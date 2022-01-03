/**
 * Segmentovka
 * 
 * @author Peter Hulák
 */
public class Segmentovka {
    private Segment[] segmenty;
    
    public Segmentovka(int poziciaX, int poziciaY, int velkost) {
        this.segmenty = new Segment[7];
        this.generujSegmenty(poziciaX, poziciaY, velkost);
    }
    
    public void zobraz(int cislo) {
        switch (cislo) {
            case -1:
                this.zobrazKonkretneSegmenty(new boolean[] {false, false, false, false, false, false, false});
                break;
            case 0:
                this.zobrazKonkretneSegmenty(new boolean[] {true, true, true, true, true, true, false});
                break;
            case 1:
                this.zobrazKonkretneSegmenty(new boolean[] {false, true, true, false, false, false, false});
                break;
            case 2:
                this.zobrazKonkretneSegmenty(new boolean[] {true, true, false, true, true, false, true});
                break;
            case 3:
                this.zobrazKonkretneSegmenty(new boolean[] {true, true, true, true, false, false, true});
                break;
            case 4:
                this.zobrazKonkretneSegmenty(new boolean[] {false, true, true, false, false, true, true});
                break;
            case 5:
                this.zobrazKonkretneSegmenty(new boolean[] {true, false, true, true, false, true, true});
                break;
            case 6:
                this.zobrazKonkretneSegmenty(new boolean[] {true, false, true, true, true, true, true});
                break;
            case 7:
                this.zobrazKonkretneSegmenty(new boolean[] {true, true, true, false, false, false, false});
                break;
            case 8:
                this.zobrazKonkretneSegmenty(new boolean[] {true, true, true, true, true, true, true});
                break;
            case 9:
                this.zobrazKonkretneSegmenty(new boolean[] {true, true, true, true, false, true, true});
        }
    }

    /* Private */

    private void generujSegmenty(int poziciaX, int poziciaY, int velkost) {
        int sirka = velkost;
        int vyska = velkost / 5;
        
        this.segmenty[0] = new Segment(sirka, vyska, vyska + poziciaX, poziciaY);
        this.segmenty[1] = new Segment(vyska, sirka, vyska + sirka + poziciaX, vyska + poziciaY);
        this.segmenty[2] = new Segment(vyska, sirka, vyska + sirka + poziciaX, 2 * vyska + sirka + poziciaY);
        this.segmenty[3] = new Segment(sirka, vyska, vyska + poziciaX, 2 * vyska + 2 * sirka + poziciaY);
        this.segmenty[4] = new Segment(vyska, sirka, poziciaX, 2 * vyska + sirka + poziciaY);
        this.segmenty[5] = new Segment(vyska, sirka, poziciaX, vyska + poziciaY);
        this.segmenty[6] = new Segment(sirka, vyska, vyska + poziciaX, vyska + sirka + poziciaY);
    }
    
    private void zobrazKonkretneSegmenty(boolean[] zoznamSegmentov) {
        for (int i = 0; i < zoznamSegmentov.length; ++i) {
            this.segmenty[i].prepni(zoznamSegmentov[i]);
        }
    }
}
