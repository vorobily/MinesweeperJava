public class SystemUkladania {

    private static SystemUkladania instancia = null;

    public static SystemUkladania dajInstanciu()
    {
        if (SystemUkladania.instancia == null) {
            SystemUkladania.instancia = new SystemUkladania();
        }
 
        return SystemUkladania.instancia;
    }

    private SystemUkladania()
    {
        //TODO :)
    }
}
