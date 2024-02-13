public class Operace {

    public static String getHodnotaSNulou(int hodnota) {
        if (hodnota < 10) {
            return "0" + String.valueOf(hodnota);
        }
        return String.valueOf(hodnota);
    }

    public static boolean jeCislo(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            return false;
        }

        return true;
    }
}
