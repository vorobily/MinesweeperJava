public class Mriezka extends UIPrvok {
    private Policko[][] policka;

    public Mriezka(int x, int y, int rozmer, int velkostPolicok) {
        super((velkostPolicok + 1) * rozmer + 1, (velkostPolicok + 1) * rozmer + 1, x, y);

        super.zobraz();

        this.policka = new Policko[rozmer][rozmer];
        this.generujPolicka(x, y, rozmer, velkostPolicok);
    }

    private void generujPolicka(int x, int y, int rozmer, int velkostPolicok) {
        for (int i = 0; i < rozmer; ++i) {
            for (int j = 0; j < rozmer; ++j) {
                this.policka[j][i] = new Policko(velkostPolicok, x + (i * (velkostPolicok + 1)) + 1, y + (j * (velkostPolicok + 1)) + 1);
            }
        }
    }
}
