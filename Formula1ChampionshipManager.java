import java.util.Scanner;
//import java.io.BufferedWriter;
//import java.io.FileInputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.LinkedList;

public class Formula1ChampionshipManager {

    public static Driver[] RaceDriver = new Driver[15]; 
    public static Races[] RaceF1 = new Races[10];
    //public static int boothNum = 0; <=======dafaq is this clean it later
    public static int DriverCount = 0;

    public static void main(String[] args) {

        for (int x = 0; x < 15; x++) //initializing the Driver type array with all data set to empty
            RaceDriver[x] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
        for (int y = 0; y < 10; y++) //initializing the races type array with all data set to empty
            RaceF1[y] = new Races("NA", "NA");

        PrintMenu(args);
    }

    public static void PrintMenu(String[] args) { // Print Menu method
        Scanner input = new Scanner(System.in);
        Boolean MenuCheck = true;
        while (MenuCheck) {

            System.out.print("\n======================== MENU ==============================");
            System.out.print("\nEnter 1: to Create a New Driver");
            System.out.print("\nEnter 2: to Delete a Driver");
            System.out.print("\nEnter 3: to Change Driver Team");
            System.out.print("\nEnter 4: to Check Stats Of a Driver");
            System.out.print("\nEnter 5: to Display F1 Driver Table");
            System.out.print("\nEnter 6: to Add a Race");
            System.out.print("\nEnter 7: to Save Data");
            System.out.print("\nEnter 8: to Start GUI");
            System.out.print("\nEnter 0: to Exit the Program");
            System.out.print("\n============================================================");

            System.out.print("\nEnter your Input : ");
            String userInput = input.nextLine();

            if (userInput.equals("1")) {
                System.out.print("\nEntered input is 1!\n");
                AddDriver(input);
                resubmit(input);
            } else if (userInput.equals("2")) {
                System.out.print("\nEntered input is 2!\n");
                DelDriver(input);
            } else if (userInput.equals("3")) {
                System.out.print("\nEntered input is 3!\n");
                ChangeTeamDriver(input);
            } else if (userInput.equals("4")) {
                System.out.print("\nEntered input is 4!\n");
                //function()
            } else if (userInput.equals("5")) {
                System.out.print("\nEntered input is 5!\n");
                DisplayDRVRStats(input); // working on this first cause why not
            } else if (userInput.equals("6")) {
                System.out.print("\nEntered input is 6!\n");
                //function()
            } else if (userInput.equals("7")) {
                System.out.print("\nEntered input is 7!\n");
                //function()
            } else if (userInput.equals("8")) {
                System.out.print("\nEntered input is 8!\n");
                //function()
                GUI.main(args);
            } else if (userInput.equals("0")) {
                System.out.print("\nThank you For Trying the Application!\n");
                MenuCheck = false;
            } else {
                System.out.print("\nThe entered input is Invalid!\n");
            }
        }
    }

    //Add a Race Function NOT COMPLETED
    public static void AddRace(Scanner input){
        //String RaceName = "E";
        String RaceName = "";
        Boolean RaceChecker = true;

        while (RaceChecker) {
            System.out.print("\nEnter Race Name : ");
            RaceName = input.nextLine(); 
        }

        System.out.print("\nEnter Race Date : ");
        String RaceDate = input.nextLine(); 

    }

    // CHANGE DRIVER FOR TEAM FUNCTIONS
    public static void ChangeTeamDriver(Scanner input){
        System.out.print("\nCurrently Participating Teams : ");
        for (int i =0; i<15; i++){
            if (!RaceDriver[i].getDriverN().equals("E")){
                System.out.print("\n SNo " + i + " : Team "+ RaceDriver[i].getDriverT().getTeamN() );
            }  
        }

        System.out.print("\nWhich Team Driver Would you like to modify (Enter SNo) : ");
        int UInput = Integer.parseInt(input.nextLine());

        System.out.print("\nEnter the New Driver's Name : ");
        String DName = input.nextLine(); 

        Boolean sureuwannadelete = true;
        while (sureuwannadelete){
            System.out.print("\nAre you sure you want to fire Team "+ RaceDriver[UInput].getDriverT().getTeamN() +" Main Driver " + RaceDriver[UInput].getDriverN() 
                              + " And Replace them with " + DName + " ? (yes/y/no/n) : "); //   
            String UInput2 = input.nextLine();
            if (UInput2.equals("yes") || UInput2.equals("y")){
                RaceDriver[UInput].setDriverN(DName);
                System.out.print("\nCongradulation " + RaceDriver[UInput].getDriverN() +" on joining team "+ RaceDriver[UInput].getDriverT().getTeamN()+ "!\n");
                sureuwannadelete = false;
            }else if (UInput2.equals("no") || UInput2.equals("n")){
                System.out.print("\nCancelling Driver change Request for Team " + RaceDriver[UInput].getDriverT().getTeamN()+ " ...\n");
                sureuwannadelete = false;
            }else{
                System.out.print("\nThis is Not a Valid Input!\n");
            }
        }
    }

    // Add Driver Function
    public static void AddDriver(Scanner input) {
        int DPos = 0;
        int ErrorCount = 0;
        String DTeam = "E";
        System.out.print("\nEnter Driver's Name : ");
        String DName = input.nextLine(); 
        System.out.print("\nEnter Drivers Location : ");
        String DLocation = input.nextLine();
        
        
        // unique team checker===============
        Boolean TeamCheck = true;
        while(TeamCheck){
            ErrorCount=0;
            System.out.print("\nEnter Drivers Team : ");
            DTeam = input.nextLine(); 
            for (int i =0; i<15; i++){
                if (RaceDriver[i].getDriverT().getTeamN().equals(DTeam)){
                    ErrorCount ++;
                }
            }
            if (ErrorCount>0){
                System.out.print("\nThis team Already has a Driver! Try again...");
            }
            else{
                TeamCheck = false;
            } 
        }

        Boolean PositionChecker = true;
        while (PositionChecker) { 
            System.out.print("\nEnter Driver Position in Race : ");
            DPos = Integer.parseInt(input.nextLine());
            if (DPos < 1 || DPos > 10){
                System.out.print("Pls Enter a Valid Position between 1-10");
            }else {
                DriverCount +=1;
                PositionChecker = false;
            }
        }   
        for (int x = 0; x < 15; x++) {
            if (RaceDriver[x].getDriverN().equals("E")) {
                RaceDriver[x] = new Driver(DName, DLocation, new Team(DTeam), new Formula1Driver(DPos));
                DriverCount += 1;
                System.out.print("\nAdded Data for Driver : " + RaceDriver[x].getDriverN() 
                                + " From " + RaceDriver[x].getDriverL() 
                                + " in Team "+ RaceDriver[x].getDriverT().getTeamN()  
                                + " who came in position " + RaceDriver[x].getDriverS().getRP() 
                                + " who has a total points of " + RaceDriver[x].getDriverS().getNumPoint()
                                + ". \nData Stored In Location " + x);        
                break;
            }
        }
    }
    //Resubmit Add Driver function
    public static void resubmit(Scanner input){
        //Recursion time? 
        Boolean Resubmit = true;
        while (Resubmit){
            System.out.print("\n\nDo you want to add another driver? (yes/y/no/n) : ");
            String AddUI = input.nextLine(); 
            if (AddUI.equals("yes") || AddUI.equals("y")){
                AddDriver(input);
            }else if (AddUI.equals("no") || AddUI.equals("n")){
                Resubmit = false;
                System.out.print("\n\nAll Data Of all Drivers");
                for (int x = 0; x < 15; x++) {
                    if (!RaceDriver[x].getDriverN().equals("E")) {
                        System.out.print("\nData of Driver : " + RaceDriver[x].getDriverN()  
                        + " of Team "+ RaceDriver[x].getDriverT().getTeamN()  
                        + " stored in location " + x);
                    }else {
                        System.out.print("\nNo Data contained in the postion " + x);
                    }
                } 
            }else{
                System.out.print("\nPls Enter a Valid Answer!");
            }
        }
    }

    // Delete the Driver 
    public static void DelDriver(Scanner input){
        System.out.print("\n\nData of all Added Drivers");
        for (int x = 0; x < 15; x++) {
            if (!RaceDriver[x].getDriverN().equals("E")) {
                System.out.print("\nData location "+ x+" : Contains Data of " + RaceDriver[x].getDriverN()  
                + " of Team "+ RaceDriver[x].getDriverT().getTeamN());
            }// write a code for filled up list
        }

        Boolean DelCheck = true;
        while (DelCheck){
            System.out.print("\nEnter the Data location number of the Driver that you wish to remove : ");
            int DDNo = Integer.parseInt(input.nextLine()); // DDNo = Delete Driver Number
            if (DDNo >= RaceDriver.length) {
                System.out.print("\nThe entered Index is Out of Range!: \n");
                continue;
            } else {
                System.out.print("\nDriver " + RaceDriver[DDNo].getDriverN()+ " of Team " + RaceDriver[DDNo].getDriverT().getTeamN() + " Has been Removed From Data location number " + DDNo + "\n");
                RaceDriver[DDNo] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
                // "do u want to delete another driver "code here if have time

                //=========================================
                System.out.print("\n\nAll Data Of all Drivers");
                for (int x = 0; x < 15; x++) {
                    if (!RaceDriver[x].getDriverN().equals("E")) {
                        System.out.print("\nData of Driver : " + RaceDriver[x].getDriverN()  
                        + " of Team "+ RaceDriver[x].getDriverT().getTeamN()  
                        + " stored in location " + x);
                    }else {
                        System.out.print("\nNo Data contained in the postion " + x);
                    }
                } 
                DelCheck = false;
            }
        }
    }

    // Display table of drivers ! THIS IS NOT COMPLETE
    public static void DisplayDRVRStats(Scanner input){
        Driver[] CloneRaceDriver = new Driver[15]; // cloning Driver array as a backup to ensure no sorted change is permanant
        for (int x = 0; x < 15; x++) {
            CloneRaceDriver[x] = RaceDriver[x];
        }

        for (int x = 0; x < 9; x++) {              //Bubble sort technique
            for (int y = 0; y < 14 - x; y++) {
                if (CloneRaceDriver[y].getDriverS().getNumPoint() < CloneRaceDriver[y + 1].getDriverS().getNumPoint()) {

                    Driver Holder = CloneRaceDriver[y];
                    CloneRaceDriver[y] = CloneRaceDriver[y + 1];
                    CloneRaceDriver[y + 1] = Holder;
                }
            }
        }

        // printing part
        String TableFormat = "\n| %-4d | %-18s | %-16s | %-17d | %-14d |";
        System.out.print("\n=============================== Formula 1 Driver Table ==============================");
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+");
        System.out.print("\n| Rank |    Driver Name     |       Team       |  Number of Races  |  Total Points  |");
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+");
        //some code Driver
        for (int x = 0; x < 15; x++) {
            if (!CloneRaceDriver[x].getDriverN().equals("E")) {
                System.out.printf(TableFormat, x+1, CloneRaceDriver[x].getDriverN(), CloneRaceDriver[x].getDriverT().getTeamN(), CloneRaceDriver[x].getDriverS().getNumRaces(), CloneRaceDriver[x].getDriverS().getNumPoint());
            }
        }
        //System.out.print("\n|                    |                  |                   |                |"); // Test code
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+");
    }

}