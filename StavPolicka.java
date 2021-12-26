public enum StavPolicka {
    SKYRTE("gray"),
    VLAJKA("gray"),
    ZOBRAZENE("white");

    private String farba;

    StavPolicka(String farba) {
        this.farba = farba;
    }

    public String getFarba() {
        return this.farba;
    }
}
