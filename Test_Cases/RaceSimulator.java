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

import java.util.Random;
import java.util.ArrayList;

public class RaceSimulator { 

   /* This Class was Desined to Tackle the question that Required Random Start Postions for Driver and then decide the Win%  for them before they get their 
    * Final Postions based on Win% they attained from Start Postions! as such the entire code was made in a simple way compared to my actual coursework code 
    * just to test the working of it completly and ensure the final results are actually generated based on win%
    *
    * Im sure there could be a more optimised way for this, but i did this entire code to ensure every requirement requested by the CW documentation was met!
    * This part was the most fun thing for me to work on during this CW and was quite an exciting Learning experiance Along with Swing!
    */

    //Array of Driver Names
    public static String RaceDriver [] = {"Chamber", "Jett", "Dieuzu", "Killjoy", "Kizura", "Viper", "Cypher", 
                                         "Phoenix", "Reyna", "Raze", "Breach", "Skye", "Yoru", "Astra","Jynx"};

    // This Array holds the Start Postions of Each Driver from RaceDriver in same order as the names.
    public static int [] StartPosition = new int[RaceDriver.length]; 
    // This Array holds the Win% For Each Driver from RaceDriver based on their StartPostions in same order as the driver names.
    public static double [] WinPercentPerDriver = new double[RaceDriver.length];
    // This Array holds the Finish Postions of Each Driver from RaceDriver in same order as the names.
    public static int [] FinishPosition = new int[RaceDriver.length];

    public static ArrayList<Integer> StartPositionTracker = new ArrayList<Integer>(); // Keeps track of Randomly generated Starting position for each driver from race
    public static ArrayList<Integer> FinalPositionTracker = new ArrayList<Integer>(); // Keeps track of Randomly generated Finish position for each driver from race

    public static void main(String[] args) { 
        for (int i = 0; i < 5; i++){ // Runs the entire thing 5 times to see 5 different races results!
            System.out.print("\n--------------------------------------------------------------------------------");
            System.out.print("\nGenerating Start postions For the Race "+ (i+1));
            System.out.print("\n--------------------------------------------------------------------------------\n");
            RandomStartP();
            System.out.print("--------------------------------------------------------------------------------");
            System.out.print("\nGenerating Final postions For the Race "+ (i+1));
            System.out.print("\n--------------------------------------------------------------------------------");
            RandomFinishP();
            System.out.print("--------------------------------------------------------------------------------\n");
            System.out.print("====================================================================================================================== END OF RACE " + (i+1));
            Resetme();
        }
    }

    // Step 1.0. Generates a Random Postion (Start)
    public static void RandomStartP() {
        for (int x = 0; x < RaceDriver.length; x++) {  // Stores all driver details line by line
            Random raceSTARTposition = new Random();
            boolean Race = true;
            while (Race) {

                int random = raceSTARTposition.nextInt(RaceDriver.length+1);
                if (!StartPositionTracker.contains(random) && random !=0) {
                    StartPositionTracker.add(random);
                    double WinPercent = WinPercent(random);
                    System.out.print(RaceDriver[x] + " Started Race in Postion : " + random + " and has a " 
                    + WinPercent*100  + "% chance of coming First place\n");                               
                    StartPosition[x] = random;
                    WinPercentPerDriver[x] = WinPercent;
                    Race = false;
                }
            }    

        }
    }

    // Step 1.1. We are Calculating Driver chance to win race using Step 1.0
    public static double WinPercent(int x){
        double Percent = 0;
        if (x == 1){
            Percent = 0.4;   // 40% chance to finish Race in 1st place

        }else if (x == 2){
            Percent = 0.3;   // 30% chance to finish Race in 1st place

        }else if (x == 3 || x == 4 ){
            Percent = 0.1;   // 10% chance to finish Race in 1st place

        }else if (x == 5 || x == 6 || x == 7 || x == 8 || x == 9){
            Percent = 0.02;  // 2% chance to finish Race in 1st place

        }else {
            Percent = 0.0;  // 0% chance to be in 1st place (we are assuming its IMPOSSIBLE TO WIN RACE with 0% to simulate to real probability)
        }

        return Percent;
    }

    // Step 2.0. Based on Step 1 and 1.1 We start this step
    public static void RandomFinishP() {
       RaceWinnerDecider(); // Runs Step 2.1 to decide Who wins the race based on their win % from step 1.1

        for(int x = 0; x < RaceDriver.length; x++) { //final printing code
            int position = 1;
    
             //bubble sort to arrange Drivers in order of race postions (using a temp Array of classes thats a clone of RaceDriver)
            for (int i = 0; i < RaceDriver.length-1; i++) {              
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

    // Step 2.1. Uses Step 1.1 to decide the Final Postions of Each driver in the race 
    public static void RaceWinnerDecider(){
        int Drivercount = 0; // counts all the drivers that particpated in the race and got a postion to ensure there will be a first place winer with a win % > 0
        
        for (int x = 0; x < RaceDriver.length; x++) {  // Stores all driver details line by line
            double percent =  WinPercentPerDriver[x];

            if (!FinalPositionTracker.contains(1)){ 
                boolean ChanceofFIRST = new Random().nextDouble() <= percent;  //https://stackoverflow.com/questions/8183840/probability-in-java

                if((ChanceofFIRST == true || Drivercount == RaceDriver.length-1) && WinPercentPerDriver[x] ==0.0){
                    //This condition ensures the Match will always have a winner with a winning % and cannot win race with 0% success rate.
                    // so the entire race stats will be reset till perfect random race is generated without a 0% win rate driver winning the entire race
                    Resetme2(); 
                    RaceWinnerDecider();
                    break;
                } else if((ChanceofFIRST == true || Drivercount == RaceDriver.length-1) && WinPercentPerDriver[x] !=0.0){
                    FinalPositionTracker.add(1);
                    FinishPosition[x] = 1;
                    Drivercount++;
                }else{// if they dunt get 1st place in the "if condition" above they will be randomly given an availble postion based on max number of driver participating.
                    FinishPositionGenerator(x);
                    Drivercount++;
                }

            }else {// if somone has already got 1st place remaining postions will be randomly given out!
                FinishPositionGenerator(x);  
                Drivercount++;
            }
        }
    }

    // Step 2.1.1. this Resets the Current race in the event a driver with 0% chance beats the odds!
    public static void Resetme2(){
        FinishPosition = new int[RaceDriver.length];
        FinalPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Final postion Tracker for all drivers from current  race
    }

    // Step 2.1.2. This generates Finish postions for Drivers who DO NOT get in 1st place 
    public static void FinishPositionGenerator(int x){
        Random raceENDposition = new Random();
        boolean Race = true;
        while (Race) {

            int random = raceENDposition.nextInt(RaceDriver.length+1);
            if (!FinalPositionTracker.contains(random) && random !=0 && random !=1) {
                FinalPositionTracker.add(random);
                FinishPosition[x] = random;
                Race = false;
            }
        }    
    }

    // Method to Reset all Temp Data Created during a Race creation!
    public static void Resetme(){
        StartPosition = new int[RaceDriver.length];
        FinishPosition = new int[RaceDriver.length];
        WinPercentPerDriver = new double[RaceDriver.length];
        StartPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Start postion Tracker for all drivers from LAST race
        FinalPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Final postion Tracker for all drivers from LAST race
    }

}