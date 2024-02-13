public class Segment extends UIPrvek {
    
    public Segment(int sirka, int vyska, int poziceX, int poziceY) {
        super(sirka, vyska, poziceX, poziceY);
    }
    
    public void prepni(boolean zapnut) {
        if (zapnut) {
            super.zobraz();
        } else {
            super.skryt();
        }
    }
}
