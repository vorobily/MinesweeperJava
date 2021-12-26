public class Miny {
    private Mriezka mriezka;
    private Manazer manazer;
    private Casovac casovac;

    private boolean hraSa;
    
    public Miny(int rozmer, int velkostPolicok, int pocetMin) {
        int polohaX = 400 - (rozmer * (velkostPolicok + 1) + 1) / 2;
        int polohaY = 300 - (rozmer * (velkostPolicok + 1) + 1) / 2;

        this.mriezka = new Mriezka(polohaX, polohaY, rozmer, velkostPolicok, pocetMin);
        this.manazer = new Manazer();
        this.casovac = new Casovac(5, 5, 25);

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

    public void tik() {
        if (this.hraSa) {
            this.casovac.pridaj();
        }
    }
}
