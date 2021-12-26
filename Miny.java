public class Miny {
    private Mriezka mriezka;
    private Manazer manazer;

    private boolean hraSa;
    
    public Miny(int x, int y, int rozmer, int velkostPolicok, int pocetMin) {
        this.mriezka = new Mriezka(x, y, rozmer, velkostPolicok, pocetMin);
        this.manazer = new Manazer();

        this.hraSa = true;
        this.manazer.spravujObjekt(this);
    }

    public void klik(int x, int y, boolean laveTlacidlo) {
        if (this.hraSa) { //Ak prebieha hra
            if (this.mriezka.klik(x, y, laveTlacidlo)) { //Ak klikol na políčko
                switch (this.mriezka.skoncilaHra()) { //Ak skončila hra
                    case PREBIEHA:
                        break;
                    case VYHRAL:
                        this.hraSa = false;
                        System.out.println("Výhra");
                        break;
                    case PREHRAL:
                        this.hraSa = false;
                        System.out.println("Prehra");
                }
            }
        }
    }
}
