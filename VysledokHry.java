public enum VysledokHry {
    PREBIEHA("Hra prebieha"),
    VYHRAL("Vyhral si"),
    PREHRAL("Prehral si");

    private String vyhernaSprava;

    VysledokHry(String vyhernaSpravaString) {
        this.vyhernaSprava = vyhernaSpravaString;
    }

    public String getVyhernaSprava() {
        return this.vyhernaSprava;
    }
}
