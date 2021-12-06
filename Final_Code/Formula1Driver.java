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

public class Formula1Driver {

    private int racePosition = 0;   // Variable to hold Race postion of Last Race
    private int numberOfPoints = 0; // Variable to hold Total number of points earned
    private int numberOfRaces = 0;  // Variable to hold Total number of Races Participated

    // Variables to Keep track of 1st, 2nd and 3rd postions attained during a race!
    private int numberOfFirstPositions = 0;
    private int numberOfSecondPositions = 0;
    private int numberOfThirdPositions = 0;

    //Getters 
    public int getRP() {
        return racePosition;
    }
    public int getFP() {
        return numberOfFirstPositions;
    }
    public int getSP() {
        return numberOfSecondPositions;
    }
    public int getTP() {
        return numberOfThirdPositions;
    }
    public int getNumPoint() {
        return numberOfPoints;
    }
    public int getNumRaces() {
        return numberOfRaces;
    }

    //Setters
    public void setNumPoint(int p) {
        numberOfPoints += p;
    }
    public void setNumRaces(int r) {
        numberOfRaces += r;
    }

    public void setFP(int x) {
        numberOfFirstPositions += x;
    }
    public void setSP(int y) {
        numberOfSecondPositions += y;
    }
    public void setTP(int z) {
        numberOfThirdPositions += z;
    }

    public void setRP(int s) { // Member function for calculating points and asigning records of Special finish postions of a race 
        racePosition = s;
        if (racePosition == 1){ // 1:25 2:18 3:15 4:12 5:10 6:8 7:6 8:4 9:2 10:1
            numberOfFirstPositions +=1;
            numberOfPoints += 25;
            numberOfRaces += 1;
        }else if (racePosition == 2){
            numberOfSecondPositions += 1;
            numberOfPoints += 18;
            numberOfRaces += 1;
        }else if (racePosition == 3){
            numberOfThirdPositions +=1;
            numberOfPoints += 15;
            numberOfRaces += 1;
        }else if (racePosition == 4){
            numberOfPoints += 12;
            numberOfRaces += 1;
        }else if (racePosition == 5){
            numberOfPoints += 10;
            numberOfRaces += 1;
        }else if (racePosition == 6){
            numberOfPoints += 8;
            numberOfRaces += 1;
        }else if (racePosition == 7){
            numberOfPoints += 6;
            numberOfRaces += 1;
        }else if (racePosition == 8){
            numberOfPoints += 4;
            numberOfRaces += 1;
        }else if (racePosition == 9){
            numberOfPoints += 2;
            numberOfRaces += 1;
        }else if (racePosition == 10){
            numberOfPoints += 1;
            numberOfRaces += 1;
        }else if (racePosition == 0){
            numberOfPoints += 0;
            numberOfRaces += 0;
        }else{
            numberOfPoints += 0;
            numberOfRaces += 1;
        }
    }
    
    // constructors
    public Formula1Driver(int NR, int NP, int NFP, int NSP, int NTP){
        numberOfRaces = NR;
        numberOfPoints = NP;
        numberOfFirstPositions = NFP;
        numberOfSecondPositions = NSP;
        numberOfThirdPositions = NTP;
    }

    public Formula1Driver(int position){ //constructor Overloading  X  1:25 2:18 3:15 4:12 5:10 6:8 7:6 8:4 9:2 10:1
        racePosition = position;
        if (racePosition == 1){
            numberOfFirstPositions +=1;
            numberOfPoints += 25;
            numberOfRaces += 1;
        }else if (racePosition == 2){
            numberOfSecondPositions += 1;
            numberOfPoints += 18;
            numberOfRaces += 1;
        }else if (racePosition == 3){
            numberOfThirdPositions +=1;
            numberOfPoints += 15;
            numberOfRaces += 1;
        }else if (racePosition == 4){
            numberOfPoints += 12;
            numberOfRaces += 1;
        }else if (racePosition == 5){
            numberOfPoints += 10;
            numberOfRaces += 1;
        }else if (racePosition == 6){
            numberOfPoints += 8;
            numberOfRaces += 1;
        }else if (racePosition == 7){
            numberOfPoints += 6;
            numberOfRaces += 1;
        }else if (racePosition == 8){
            numberOfPoints += 4;
            numberOfRaces += 1;
        }else if (racePosition == 9){
            numberOfPoints += 2;
            numberOfRaces += 1;
        }else if (racePosition == 10){
            numberOfPoints += 1;
            numberOfRaces += 1;
        }else if (racePosition == 0){
            numberOfPoints += 0;
            numberOfRaces += 0;
        }else{
            numberOfPoints += 0;
            numberOfRaces += 1;
        }
    }

}