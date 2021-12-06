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

public class DRData { //DRData stands for "Driver Race Data" this class basically holds individual race data for the driver for a perticular race
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