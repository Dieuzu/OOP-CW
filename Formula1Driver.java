
public class Formula1Driver {
    private int numFirstPos = 0;
    private int numSecondPos = 0;
    private int numThirdPos = 0;
    private int racePosition = 0;
    private int numPoints = 0;
    private int numRaces = 0;

    //Getters 
    public int getRP() {
        return racePosition;
    }
    public int getFP() {
        return numFirstPos;
    }
    public int getSP() {
        return numSecondPos;
    }
    public int getTP() {
        return numThirdPos;
    }
    public int getNumPoint() {
        return numPoints;
    }
    public int getNumRaces() {
        return numRaces;
    }

    //setters
    public void setRP(int s) {
        racePosition = s;
        if (racePosition == 1){
            numFirstPos +=1;
            numPoints += 25;
            numRaces += 1;
        }else if (racePosition == 2){
            numSecondPos += 1;
            numPoints += 18;
            numRaces += 1;
        }else if (racePosition == 3){
            numThirdPos +=1;
            numPoints += 15;
            numRaces += 1;
        }else if (racePosition == 4){
            numPoints += 12;
            numRaces += 1;
        }else if (racePosition == 5){
            numPoints += 10;
            numRaces += 1;
        }else if (racePosition == 6){
            numPoints += 8;
            numRaces += 1;
        }else if (racePosition == 7){
            numPoints += 6;
            numRaces += 1;
        }else if (racePosition == 8){
            numPoints += 4;
            numRaces += 1;
        }else if (racePosition == 9){
            numPoints += 2;
            numRaces += 1;
        }else if (racePosition == 10){
            numPoints += 1;
            numRaces += 1;
        }else if (racePosition == 0){
            numPoints += 0;
            numRaces += 0;
        }else{
            numPoints += 0;
            numRaces += 1;
        }
    }
    public void setFP(int x) {
        numFirstPos += x;
    }
    public void setSP(int y) {
        numSecondPos += y;
    }
    public void setTP(int z) {
        numThirdPos += z;
    }
    public void setNumPoint(int p) {
        numPoints += p;
    }
    public void setNumRaces(int r) {
        numRaces += r;
    }
    
    // constructors
    public Formula1Driver(int position){ // 1:25 2:18 3:15 4:12 5:10 6:8 7:6 8:4 9:2 10:1
        racePosition = position;
        if (racePosition == 1){
            numFirstPos +=1;
            numPoints += 25;
            numRaces += 1;
        }else if (racePosition == 2){
            numSecondPos += 1;
            numPoints += 18;
            numRaces += 1;
        }else if (racePosition == 3){
            numThirdPos +=1;
            numPoints += 15;
            numRaces += 1;
        }else if (racePosition == 4){
            numPoints += 12;
            numRaces += 1;
        }else if (racePosition == 5){
            numPoints += 10;
            numRaces += 1;
        }else if (racePosition == 6){
            numPoints += 8;
            numRaces += 1;
        }else if (racePosition == 7){
            numPoints += 6;
            numRaces += 1;
        }else if (racePosition == 8){
            numPoints += 4;
            numRaces += 1;
        }else if (racePosition == 9){
            numPoints += 2;
            numRaces += 1;
        }else if (racePosition == 10){
            numPoints += 1;
            numRaces += 1;
        }else if (racePosition == 0){
            numPoints += 0;
            numRaces += 0;
        }else{
            numPoints += 0;
            numRaces += 1;
        }

    }

}
