/* ----------------------------------------------------------------------------------------------
 * MODULE: (2021) 5COSC019C.1 Object Oriented Programming (IIT Sri Lanka)
 * OOP - Coursework
 * ----------------------------------------------------------------------------------------------
 * Student Name    : Saadat Hamid Mansoor
 * Student IIT ID  : 20200616
 * Student UoW ID  : w18336607/1
 * 
 * Date            : 24th November 2021 
 * ----------------------------------------------------------------------------------------------
 * ”I confirm that I understand what plagiarism is and have read and understood the
 * section on Assessment Offences in the Essential Information for Students. The work
 * that I have submitted is entirely my own. Any work from other authors is duly
 * referenced and acknowledged within my code.”
 * ----------------------------------------------------------------------------------------------
 */

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