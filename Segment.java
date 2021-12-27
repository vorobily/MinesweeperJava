/**
 * Segment
 * 
 * @author Peter Hulák
 */
public class Segment {
    private Obdlznik segment;
    
    public Segment(int sirka, int dlzka, int poziciaX, int poziciaY) {
        this.segment = new Obdlznik(sirka, dlzka, poziciaX, poziciaY);
    }
    
    public void prepni(boolean zapnut) {
        if (zapnut) {
            this.segment.zobraz();
        } else {
            this.segment.skry();
        }
    }
}
