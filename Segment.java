/**
 * Segment
 * 
 * @author Peter Hulák
 */
public class Segment extends UIPrvok {
    
    public Segment(int sirka, int dlzka, int poziciaX, int poziciaY) {
        super(sirka, dlzka, poziciaX, poziciaY);
    }
    
    public void prepni(boolean zapnut) {
        if (zapnut) {
            super.zobraz();
        } else {
            super.skry();
        }
    }
}
