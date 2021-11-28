public class Driver {
    private String driverName;
    private String driverLocation;
    private Team driverTeam;
    private Formula1Driver driverStats;

    // temp details vars <<these are exclusive for Q3. Part 5>>
    private int StartPosition;
    private int FinishPosition;
    private double WinPercentPerDriver;
    // these are exclusive for Q3. Part 5

    //make an array holding data of all races and dates and positions
    private DRData DriverRaceData[] = new DRData[Formula1ChampionshipManager.MaxNumDrivers];






    //Getters 
    public String getDriverN() {
        return driverName;
    }
    public String getDriverL() {
        return driverLocation;
    }
    public Team getDriverT() {
        return driverTeam;
    }
    public Formula1Driver getDriverS() {
        return driverStats;
    }

    public DRData getDRD(int x) { 
        return DriverRaceData[x];
    }

    public int getStartP() {
        return StartPosition;
    }
    public int getFinishP() {
        return FinishPosition;
    }
    public double getWinPercent() {
        return WinPercentPerDriver;
    }


    //Setters 
    public void setDriverN(String name) {
        driverName = name;
    }
    public void setDriverL(String location) {
        driverLocation = location;
    }
    public void setDriverT(Team team) {
        driverTeam = team;
    }
    public void setDriverS(Formula1Driver stats) { 
        driverStats = stats;
    }

    public void setDRD(int x, DRData y) { 
        DriverRaceData[x] = y;
    }

    public void setStartP(int a) {
        StartPosition = a;
    }
    public void setFinishP(int b) {
        FinishPosition = b;
    }
    public void setWinPercent(double c) {
        WinPercentPerDriver = c;
    }

    // Compund Functions 
    public void DriverRaceInit(){

        for (int y = 0; y < Formula1ChampionshipManager.MaxNumRaces; y++) {
            DriverRaceData[y] = new DRData("NA", "NA", 0);//initializing the races type array with all data set to empty
        }
        return;
    }

    //Constructors 
    public Driver(String Name, String Location, Team Team, Formula1Driver Stats){
        driverName = Name ;
        driverLocation= Location ;
        driverTeam= Team ;
        driverStats = Stats;
    } 

    public Driver(String Name, String Location, Team Team){ // overloading
        driverName = Name ;
        driverLocation= Location ;
        driverTeam= Team ;

    } 

    public Driver(Formula1Driver Stats){ // overloading
        driverStats = Stats;
    } 


}
