public class Driver {
    private String driverName;
    private String driverLocation;
    private Team driverTeam;
    private Formula1Driver driverStats;

    
    //make an array holding data of all races and dates and positions
    private DRData DriverRaceData[] = new DRData[Formula1ChampionshipManager.MaxNumDrivers];

    public DRData getDRD(int x) {
            return DriverRaceData[x];
    }

    public void setDRD(int x, DRData y) { //Setter
        DriverRaceData[x] = y;
    }

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

    public void setDriverS(Formula1Driver stats) { //Setter
        driverStats = stats;
    }

    //Constructors 
    public Driver(String Name, String Location, Team Team, Formula1Driver Stats){
        driverName = Name ;
        driverLocation= Location ;
        driverTeam= Team ;
        driverStats = Stats;
    } 

    public void DriverRaceInit(){

        for (int y = 0; y < Formula1ChampionshipManager.MaxNumRaces; y++) {
            DriverRaceData[y] = new DRData("NA", "NA", 0);//initializing the races type array with all data set to empty
        }
        return;
    }
    

    public Driver(String Name, String Location, Team Team){
        driverName = Name ;
        driverLocation= Location ;
        driverTeam= Team ;

    } 

    public Driver(Formula1Driver Stats){ // overloading
        driverStats = Stats;
    } 

    // public Driver(){ // overloading
    //     for (int y = 0; y < Formula1ChampionshipManager.MaxNumDrivers; y++) //initializing the races type array with all data set to empty
    //        DriverRaceData[y] = new DRData("NA", "NA", 0);
    // } 
}
