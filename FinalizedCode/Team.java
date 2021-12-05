public class Team {

    private String teamName; // Holds the Team Name of a Driver from Driver class

    //Getter
    public String getTeamN() {
        return teamName;
    }
    //Setter
    public void setTeamN(String name) {
        teamName = name;
    }
    
    //Contructor
    public Team(String Name){
        teamName = Name;
    }

}