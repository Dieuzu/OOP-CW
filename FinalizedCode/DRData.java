public class DRData { //DRData stands for "Driver Race Data"
    private String raceName;
    private String raceDate;
    private int racepostion;

    //Getters
    public String getRaceName() {
        return raceName;
    }
    public String getRaceDate() {
        return raceDate;
    }
    public int getRacepostion() {
        return racepostion;
    }

    //Setters
    public void setRaceName(String RaceName) {
        raceName = RaceName;
    }
    public void setRaceDate(String RaceDate) {
        raceDate = RaceDate;
    }
    public void setRacepostion(int Racepostion) {
        racepostion = Racepostion;
    }

    //Constructor
    DRData(String x, String y, int z){
        raceName = x;
        raceDate = y;
        racepostion = z;
    }
}