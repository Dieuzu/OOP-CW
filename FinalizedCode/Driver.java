public class Driver {

    private String driverName;
    private String driverLocation;
    private Team driverTeam;
    private Formula1Driver driverStats;

    // Temp Detail variables <<these are exclusive for Q3. Part 5>>
    private int startPosition;
    private int finishPosition;
    private double winPercentPerDriver;

    // An array holding data of all races and dates and positions for driver Class
    private DRData driverRaceData[] = new DRData[Formula1ChampionshipManager.MaxNumRaces];

    // Getters 
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

    public int getStartP() {
        return startPosition;
    }
    public int getFinishP() {
        return finishPosition;
    }
    public double getWinPercent() {
        return winPercentPerDriver;
    }

    public DRData getDRD(int x) { 
        return driverRaceData[x];
    }


    // Setters 
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
        driverRaceData[x] = y;
    }

    public void setStartP(int a) {
        startPosition = a;
    }
    public void setFinishP(int b) {
        finishPosition = b;
    }
    public void setWinPercent(double c) {
        winPercentPerDriver = c;
    }

    // Memember function to initialize the Array that holds all records of the driver object in all races he participate
    public void DriverRaceInit(){

        for (int y = 0; y < driverRaceData.length; y++) {
            driverRaceData[y] = new DRData("NA", "NA", 0);//initializing the races type array with all data set to Default
        }
        return;
    }

    // Constructors 
    public Driver(String Name, String Location, Team Team, Formula1Driver Stats){
        driverName = Name ;
        driverLocation= Location ;
        driverTeam= Team ;
        driverStats = Stats;
    } 

    public Driver(String Name, String Location, Team Team){ // Constructor Overloading
        driverName = Name ;
        driverLocation= Location ;
        driverTeam= Team ;

    } 

    public Driver(Formula1Driver Stats){ // Constructor Overloading
        driverStats = Stats;
    } 


}