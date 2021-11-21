public class Driver {
    private String driverName;
    private String driverLocation;
    private Team driverTeam;
    private Formula1Driver driverStats;

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

    public Driver(Formula1Driver Stats){ // overloading
        driverStats = Stats;
    } 
}
