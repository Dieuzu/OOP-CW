public class Races {
    private String MapName;
    //private Driver DriverX;
    private String RDate;

    //private String RDate;

    //Getters 
    // public Driver getDriverX() {
    //     return DriverX;
    // }

    public String getMapN() {
        return MapName;
    }

    public String RaceD() {
        return RDate;
    }

    //Setters
    // public void setDriverX(Driver x) { 
    //     DriverX = x;
    // }

    public void setMapN(String y) { 
        MapName = y;
    }

    public void setRaceD(String z) { 
        RDate = z;
    }

    //Constructor
    // public Races(Driver PN) { 
    //     DriverX = PN;

    // }
    public Races(String MName, String Date) { 
        MapName = MName;
        RDate = Date;
    }
    
}
