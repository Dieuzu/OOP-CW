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