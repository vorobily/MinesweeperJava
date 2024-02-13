public class Segmentovka {
    private Segment[] segmenty;
    
    public Segmentovka(int poziceX, int poziceY, int velikost) {
        this.segmenty = new Segment[7];
        this.generujSegmenty(poziceX, poziceY, velikost);
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

    private void generujSegmenty(int poziceX, int poziceY, int velikost) {
        int sirka = velikost;
        int vyska = velikost / 5;
        
        this.segmenty[0] = new Segment(sirka, vyska, vyska + poziceX, poziceY);
        this.segmenty[1] = new Segment(vyska, sirka, vyska + sirka + poziceX, vyska + poziceY);
        this.segmenty[2] = new Segment(vyska, sirka, vyska + sirka + poziceX, 2 * vyska + sirka + poziceY);
        this.segmenty[3] = new Segment(sirka, vyska, vyska + poziceX, 2 * vyska + 2 * sirka + poziceY);
        this.segmenty[4] = new Segment(vyska, sirka, poziceX, 2 * vyska + sirka + poziceY);
        this.segmenty[5] = new Segment(vyska, sirka, poziceX, vyska + poziceY);
        this.segmenty[6] = new Segment(sirka, vyska, vyska + poziceX, vyska + sirka + poziceY);
    }
    
    private void zobrazKonkretneSegmenty(boolean[] zaznamSegmentov) {
        for (int i = 0; i < zaznamSegmentov.length; ++i) {
            this.segmenty[i].prepni(zaznamSegmentov[i]);
        }
    }
}
