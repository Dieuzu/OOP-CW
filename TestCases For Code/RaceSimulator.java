import java.util.Random;
import java.util.ArrayList;

public class RaceSimulator {
    public static String RaceDriver [] = {"Bea", "Nessa", "Natural", "Tharini", "Imalsha", "Chamber", "Jett", 
                                         "Draj", "Kizura", "Speedy", "Yohani", "Random Dude 1","Random Dude 2",
                                         "Random Dude 3","Random Dude 4","Random Dude 5","Random Dude 6","Random Dude 7"};

    public static int [] StartPosition = new int[RaceDriver.length]; // make it part of class temp

    public static int [] FinishPosition = new int[RaceDriver.length]; // make it part of class temp

    public static double [] WinPercentPerDriver = new double[RaceDriver.length]; // make it part of class temp

    public static ArrayList<Integer> StartPositionTracker = new ArrayList<Integer>(); // keeps track of Starting position for each driver from race
    public static ArrayList<Integer> FinalPositionTracker = new ArrayList<Integer>(); // keeps track of Finish position for each driver from race

    public static void main(String[] args) { // MAIN METHOD! with Initializations + MenuFunction Call
        for (int i = 0; i < 10; i++){
            RandomStartP();
            RandomFinishP();
            Resetme();
        }
    }

    //Step 1
    public static void RandomStartP() {
        //System.out.print("Generating Starting postions For the Race : \n");
        for (int x = 0; x < RaceDriver.length; x++) {  // Stores all driver details line by line
            Random raceSTARTposition = new Random();
            boolean Race = true;
            while (Race) {

                int random = raceSTARTposition.nextInt(RaceDriver.length+1);
                if (!StartPositionTracker.contains(random) && random !=0) {
                    StartPositionTracker.add(random);
                    double WinPercent = WinPercent(random);
                    // System.out.print(RaceDriver[x] + " Started Race in Postion : " + random + "\nand has a " //UNCOMMENT ME FOR MORE INFO
                    // + WinPercent*100  + "% chance of coming First place\n\n");                               //UNCOMMENT ME FOR MORE INFO
                    StartPosition[x] = random;
                    WinPercentPerDriver[x] = WinPercent;
                    Race = false;
                }
            }    

        }
    }

    //Step 1.5
    public static double WinPercent(int x){
        double Percent = 0;
        if (x == 1){
            Percent = 0.4;

        }else if (x == 2){
            Percent = 0.3;

        }else if (x == 3 || x == 4 ){
            Percent = 0.1;

        }else if (x == 5 || x == 6 || x == 7 || x == 8 || x == 9){
            Percent = 0.02;

        }else {
            Percent = 0.0;
        }

        return Percent;
    }

    //Step 2
    public static void RandomFinishP() {
        
        System.out.print("\n\nGenerating Final postions For the Race : \n");

       // new sorter function
       RaceWinnerDecider();

        for(int x = 0; x < RaceDriver.length; x++) { //final printing code
            int position = 1;
    
            for (int i = 0; i < RaceDriver.length-1; i++) {              //Bubble sort technique ascending order
                for (int j = 0; j < RaceDriver.length-1 - i; j++) {
                    if (FinishPosition[j] > FinishPosition[j + 1]) {
                        int Holder1 = FinishPosition[j];
                        String Holder2 = RaceDriver[j];
                        double Holder3 = WinPercentPerDriver[j];

                        FinishPosition[j] = FinishPosition[j + 1];
                        RaceDriver[j] = RaceDriver[j + 1];
                        WinPercentPerDriver[j] = WinPercentPerDriver[j + 1];

                        FinishPosition[j + 1] = Holder1;
                        RaceDriver[j + 1] = Holder2;
                        WinPercentPerDriver[j + 1] = Holder3;
                    }
                }
            }

            if (FinishPosition[x] == position){  
                    System.out.print("\n"+RaceDriver[x] + " Won the Race with a Sucess rate of "+ WinPercentPerDriver[x]*100 +"% !\n");
            }else {
                System.out.print(RaceDriver[x] + " Finished the Race in Postion : " + FinishPosition[x] +"\n");
            }

        }
    }

    //Step 3
    public static void RaceWinnerDecider(){
        int Drivercount = 0;
        for (int x = 0; x < RaceDriver.length; x++) {  // Stores all driver details line by line
            double percent =  WinPercentPerDriver[x];

            if (!FinalPositionTracker.contains(1)){ 
                boolean ChanceofFIRST = new Random().nextDouble() <= percent;  //https://stackoverflow.com/questions/8183840/probability-in-java

                if((ChanceofFIRST == true || Drivercount == RaceDriver.length-1) && WinPercentPerDriver[x] ==0.0){
                    //System.out.print(RaceDriver[x] + " Won the Race with a Sucess rate of "+ WinPercentPerDriver[x]*100 +"% !\n\n");
                    //reset shyt
                    Resetme2(); 
                    RaceWinnerDecider();
                    break;
                } else if((ChanceofFIRST == true || Drivercount == RaceDriver.length-1) && WinPercentPerDriver[x] !=0.0){
                    //System.out.print(RaceDriver[x] + " Won the Race with a Sucess rate of "+ WinPercentPerDriver[x]*100 +"% !\n\n");
                    FinalPositionTracker.add(1);
                    FinishPosition[x] = 1;
                    Drivercount++;
                }else{
                    FinishPositionGenerator(x);
                    Drivercount++;
                }

            }else {
                FinishPositionGenerator(x);  
                Drivercount++;
            }
            //System.out.print(Drivercount);

        }
    }

    //Step 3.4
    public static void Resetme2(){
        FinishPosition = new int[RaceDriver.length];
        FinalPositionTracker = new ArrayList<Integer>(); // keep track position numbers of each driver from race
    }

    //Step 3.7
    public static void FinishPositionGenerator(int x){
        Random raceENDposition = new Random();
        boolean Race = true;
        while (Race) {

            int random = raceENDposition.nextInt(RaceDriver.length+1);
            if (!FinalPositionTracker.contains(random) && random !=0 && random !=1) {
                FinalPositionTracker.add(random);
                //System.out.print(RaceDriver[x] + " Finished the Race in Postion : " + random +"\n\n"); //+ " with a Sucess rate of "+ WinPercentPerDriver[x]*100 +"% !\n\n"
                FinishPosition[x] = random;
                Race = false;
            }
        }    
    }

    //Step 4
    public static void Resetme(){
        StartPosition = new int[RaceDriver.length];
        FinishPosition = new int[RaceDriver.length];
        WinPercentPerDriver = new double[RaceDriver.length];
        StartPositionTracker = new ArrayList<Integer>(); // keep track position numbers of each driver from race
        FinalPositionTracker = new ArrayList<Integer>(); // keep track position numbers of each driver from race
    }



}
