public class Text {
    private String text;
    private int x;
    private int y;
    private int velikostTextu;
    private boolean jeViditelny;

    public Text(String text, int x, int y, int velikostTextu) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.velikostTextu = velikostTextu;
        this.jeViditelny = true;

        this.nakresli();
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skryt() {
        this.smaz();
        this.jeViditelny = false;
    }

    private void smaz() {
        if (this.jeViditelny) {
            Platno.dejPlatno().erase(this);
        }
    }
       
    private void nakresli() {
        if (this.jeViditelny) {
            Platno.dejPlatno().draw(this, this.text, this.x, this.y, this.velikostTextu);
        }
    }
}