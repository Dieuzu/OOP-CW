public class DRData { // stands for Driver Race Data
    private String RaceName;
    private String RaceDate;
    private int Racepostion;

    public String getRaceName() {
        return this.RaceName;
    }

    public void setRaceName(String RaceName) {
        this.RaceName = RaceName;
    }

    public String getRaceDate() {
        return this.RaceDate;
    }

    public void setRaceDate(String RaceDate) {
        this.RaceDate = RaceDate;
    }

    public int getRacepostion() {
        return this.Racepostion;
    }

    public void setRacepostion(int Racepostion) {
        this.Racepostion = Racepostion;
    }

    DRData(String x, String y, int z){
        RaceName = x;
        RaceDate = y;
        Racepostion = z;
    }
}
