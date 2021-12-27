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

    public void nastavText(String text) {
        this.text = text;
        this.nakresli();
    }   
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.nakresli();
    }
    
    public void setVelkostTextu(int velkostTextu) {
        this.velkostTextu = velkostTextu;
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
       
    public void nakresli() {
        if (this.jeViditelny) {
            Platno.dajPlatno().draw(this, this.text, this.x, this.y, this.velkostTextu);
        }
    }
}