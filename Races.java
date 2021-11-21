public class Races {
    private String MapName;
    private String RDate;

    //Getters 
    public String getMapN() {
        return MapName;
    }

    public String getRaceD() {
        return RDate;
    }

    //Setters
    public void setMapN(String y) { 
        MapName = y;
    }

    public void setRaceD(String z) { 
        RDate = z;
    }

    //Constructor
    public Races(String MName, String Date) { 
        MapName = MName;
        RDate = Date;
    }
    
}
