public class Text {
    private String text;
    private int x;
    private int y;
    private int velkostTextu;
    private boolean jeViditelny;

    public Text(String text, int x, int y, int velkostTextu) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.velkostTextu = velkostTextu;
        this.jeViditelny = true;

        this.nakresli();
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    private void zmaz() {
        if (this.jeViditelny) {
            Platno.dajPlatno().erase(this);
        }
    }
       
    private void nakresli() {
        if (this.jeViditelny) {
            Platno.dajPlatno().draw(this, this.text, this.x, this.y, this.velkostTextu);
        }
    }
}