public enum StavPolicka {
    SKRYTE("gray"),
    VLAJKA("gray"),
    ZOBRAZENE("white");

    private String barva;

    StavPolicka(String barva) {
        this.barva = barva;
    }

    public String getbarva() {
        return this.barva;
    }
}
