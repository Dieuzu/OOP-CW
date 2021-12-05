public class Races {

    private String raceName;  // Holds Race name for Each completed Race
    private String raceDate;  // Holds race Date for each Completed Race

    //Getter
    public String getMapN() {
        return raceName;
    }
    public String getRaceD() {
        return raceDate;
    }

    //Setter
    public void setMapN(String y) { 
        raceName = y;
    }
    public void setRaceD(String z) { 
        raceDate = z;
    }

    //Constructor
    public Races(String MName, String Date) { 
        raceName = MName;
        raceDate = Date;
    }
    
}