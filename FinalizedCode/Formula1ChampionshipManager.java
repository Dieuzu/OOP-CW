import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.Random;
//import java.util.ArrayList;
//import java.util.LinkedList;

public class Formula1ChampionshipManager{

    //Manupilate all max number of drivers and races with following variables
    public static final int MaxNumDrivers = 16;
    public static final int MaxNumRaces = 20;

    //Winner of entire season 
    public static String F1SeasonChampion;

    //Array of objects to hold driver and racer data
    public static Driver[] RaceDriver = new Driver[MaxNumDrivers]; 
    public static Races[] RaceF1 = new Races[MaxNumRaces];

    //Variables to keep track of Registerd Drivers and Completed races
    public static int DriverCount = 0;
    public static int RacesCount =0;

    // Variable to Create auto date and Race number 
    public static int Racenum = 1;
    public static String RaceName; 
    public static String RaceDate;

    public static void main(String[] args) { // MAIN METHOD! with Initializations + MenuFunction Call

        for (int x = 0; x < RaceDriver.length; x++) {//initializing the Driver type array with all data set to empty
            RaceDriver[x] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
            RaceDriver[x].DriverRaceInit();
        }    
        for (int y = 0; y < RaceF1.length; y++){ //initializing the races type array with all data set to empty
            RaceF1[y] = new Races("NA", "NA");
        }

        LoadPrgrmData(); // Loads all save data on Start!
        PrintMenu(args); // loads the menu for User
    }

    //0.  Print Menu method
    public static void PrintMenu(String[] args) { 
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
            System.out.print("============================================================");

            if (userInput.equals("1")) {
                System.out.print("\nStarted Add New Driver Script\n");
                AddDriver(input);
                Resubmit(input);
            } else if (userInput.equals("2")) {
                System.out.print("\nStarted Delete Driver Script\n");
                DelDriver(input); 
            } else if (userInput.equals("3")) {
                System.out.print("\nStarted Change Driver Team Script\n");
                ChangeTeamDriver(input);
            } else if (userInput.equals("4")) {
                System.out.print("\nStarted Driver Stat Checker Script\n");
                CheckDriverStats(input);
            } else if (userInput.equals("5")) {
                System.out.print("\nStarted F1 Driver Leaderboard Script\n");
                DisplayDRVRStats(input); // working on this first cause why not
            } else if (userInput.equals("6")) {
                System.out.print("\nStarted Add F1 Race Script\n");
                AddRace(input);
            } else if (userInput.equals("7")) {
                System.out.print("\nStarted F1 Championship Manager Save Data Script\n");
                SaveData();
            } else if (userInput.equals("8")) {
                System.out.print("\nStarted GUI Script\n");
                ChampionshipManager.main(args);

            } else if (userInput.equals("0")) {
                SaveData();
                System.out.print("\nThank you For Trying the Application!\n");
                MenuCheck = false;
            } else if (userInput.equalsIgnoreCase("Reset")) {
                BDDataWipe(input);
            } else {
                System.out.print("\nThe entered input is Invalid!\n");
            }
        }
    }

    //1. Add Driver Function
    public static void AddDriver(Scanner input) {
        if (DriverCount==RaceDriver.length){
            System.out.print("Sorry the F1 Championship Roster is Full and No new Drivers Can be Added!\n");
            return;
        }
        int DPos = 0;
        int ErrorCount = 0;
        String DTeam = "E";
        System.out.print("\nEnter Driver's Name : ");
        String DName = input.nextLine(); 
        System.out.print("Enter Drivers Location : ");
        String DLocation = input.nextLine();
        
        
        // Unique Team Checker
        Boolean TeamCheck = true;
        while(TeamCheck){
            ErrorCount=0;
            System.out.print("Enter Drivers Team : ");
            DTeam = input.nextLine(); 
            for (int i =0; i<RaceDriver.length; i++){
                if (RaceDriver[i].getDriverT().getTeamN().toLowerCase().equals(DTeam.toLowerCase())){
                    ErrorCount ++;
                }
            }
            if (ErrorCount>0){
                System.out.print("This team Already has a Driver! Pls try again...\n\n");
            }
            else{
                TeamCheck = false;
            } 
        }

        //add the data to free slot in Array of classes
        for (int x = 0; x < RaceDriver.length; x++) {
            if (RaceDriver[x].getDriverN().equals("E")) {
                RaceDriver[x] = new Driver(DName, DLocation, new Team(DTeam), new Formula1Driver(DPos));
                RaceDriver[x].DriverRaceInit();
                DriverCount += 1;
                System.out.print("\nAdded Data for Driver : " + RaceDriver[x].getDriverN() 
                + " in Team "+ RaceDriver[x].getDriverT().getTeamN()  
                + " From " + RaceDriver[x].getDriverL() 
                +"!");        
                break;
            }
        }
    }

    //Method Overloading of Add Driver Function
    public static void AddDriver(Scanner input, String RN, String RD) {
        if (DriverCount==RaceDriver.length){
            System.out.print("Sorry the F1 Championship Roster is Full and No new Drivers Can be Added!\n");
            return;
        }
        int DPos = 0;
        int ErrorCount = 0;
        String DTeam = "E";
        System.out.print("\nEnter Driver's Name : ");
        String DName = input.nextLine(); 
        System.out.print("Enter Drivers Location : ");
        String DLocation = input.nextLine();
        
        
        // Unique Team Checker
        Boolean TeamCheck = true;
        while(TeamCheck){
            ErrorCount=0;
            System.out.print("Enter Drivers Team : ");
            DTeam = input.nextLine(); 
            for (int i =0; i<RaceDriver.length; i++){
                if (RaceDriver[i].getDriverT().getTeamN().toLowerCase().equals(DTeam.toLowerCase())){
                    ErrorCount ++;
                }
            }
            if (ErrorCount>0){
                System.out.print("This team Already has a Driver! Pls try again...\n\n");
            }
            else{
                TeamCheck = false;
            } 
        }

        Boolean PositionChecker = true;
        while (PositionChecker) { 
            System.out.print("Enter Driver Position in Race : ");
            DPos = Integer.parseInt(input.nextLine());
            if (DPos < 0 || DPos > RaceDriver.length){
                System.out.print("Pls Enter a Valid Position between 1-" +RaceDriver.length+"\n");
            }else {
                PositionChecker = false;
            }
        }   
        for (int x = 0; x < RaceDriver.length; x++) {
            if (RaceDriver[x].getDriverN().equals("E")) {
                RaceDriver[x] = new Driver(DName, DLocation, new Team(DTeam), new Formula1Driver(DPos));
                RaceDriver[x].DriverRaceInit(); 
                
                // add stuff to add race data
                for (int z = 0; z < RaceF1.length; z++) {
                    if (RaceDriver[x].getDRD(z).getRaceName().equals("NA")) {
                        RaceDriver[x].setDRD(z, new DRData(RN, RD, DPos)); //seter + constructor
                        break;
                    }else{
                        System.out.print("\nWEIRDO ERROR!" );
                    }
                }
                DriverCount += 1;

                System.out.print("\nAdded Data for Driver : " + RaceDriver[x].getDriverN() 
                + " in Team "+ RaceDriver[x].getDriverT().getTeamN()  
                + " From " + RaceDriver[x].getDriverL() 
                + " who came in position " + RaceDriver[x].getDriverS().getRP() 
                + " in the Race : " + RN + " that was held on " + RD
                + " with a total points of " + RaceDriver[x].getDriverS().getNumPoint());        
                break;

            }
        }
    }

    //1.5 Resubmit Add Driver function
    public static void Resubmit(Scanner input){
        if (DriverCount==RaceDriver.length){
            return;
        }
        Boolean Resubmit = true;
        while (Resubmit){
            System.out.print("\n\nDo you want to add another driver? (yes/y/no/n) : ");
            String AddUI = input.nextLine(); 
            if (AddUI.equals("yes") || AddUI.equals("y")){
                AddDriver(input);
            }else if (AddUI.equals("no") || AddUI.equals("n")){
                Resubmit = false;
                ShowAllDriver();
            }else{
                System.out.print("\nPls Enter a Valid Answer!");
            }
        }
    }

    //2. Delete the Driver 
    public static void DelDriver(Scanner input){
        if (DriverCount==0){
            System.out.print("Sorry the System doesnt have any Drivers to Delete!\n");
            return;
        }
        ShowAllDriver();

        Boolean DelCheck = true;
        while (DelCheck){
            System.out.print("\nEnter the S.No of the Driver that you wish to remove : ");
            int DDNo = Integer.parseInt(input.nextLine()); // DDNo = Delete Driver Number
            if (DDNo >= RaceDriver.length) {
                System.out.print("\nThe entered S.No is Out of Range!: \n");
                continue;
            } else {
                System.out.print("\nDriver " + RaceDriver[DDNo].getDriverN()+ " of Team " + RaceDriver[DDNo].getDriverT().getTeamN() + " Has been Removed From Formula 1 Roster\n");
                RaceDriver[DDNo] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
                RaceDriver[DDNo].DriverRaceInit(); /// LOOOK INTO THIS SHYTTTTT
                //ShowAllDriver(); // COMMENT ME OUT AFTER ALL TESTING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                DriverCount --;
                DelCheck = false;
            }
        }
    }
    
    //3. CHANGE DRIVER FOR TEAM FUNCTIONS
    public static void ChangeTeamDriver(Scanner input){
        if (DriverCount==0){
            System.out.print("Sorry the System doesnt have any Registerd Teams!\n");
            return;
        }
        System.out.print("\nCurrently Participating Teams : ");
        for (int i =0; i<RaceDriver.length; i++){
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
    
    //4. Check Driver Stats
    public static void CheckDriverStats(Scanner input){
        ShowAllDriver();
        Boolean Driverstatschecker = true;
        while(Driverstatschecker){
            System.out.print("\nEnter the S.No of the Driver Whose Stats you wish to see : ");
            int Sindex = Integer.parseInt(input.nextLine());

            if (Sindex >= RaceDriver.length || Sindex < 0) {
                System.out.print("\nThe entered S.No is Out of Range!: \n");
                continue;
            }else {
                IndividualDriverStats(Sindex);
                System.out.print("\nWould you Like to Check Another Driver ? (yes/y/no/n) :");
                String UInput =  input.nextLine(); 
                if (UInput.equals("yes") || UInput.equals("y")){
                    ShowAllDriver(); // More Recursion (YES I AM ADDICTED TO RECURSION)
                }else if (UInput.equals("no") || UInput.equals("n")){
                    System.out.print("Ending the Driver Stat Script...\n");
                    Driverstatschecker = false;
                }else{
                    System.out.print("Enterd Response Doesnt Match! Going back to Main Menu...\n");
                    Driverstatschecker = false;
                }
            }
        }
    }

    //5. Display table of drivers ! 
    public static void DisplayDRVRStats(Scanner input){
        Driver[] CloneRaceDriver = new Driver[RaceDriver.length]; // cloning Driver array as a backup to ensure no sorted change is permanant
        for (int x = 0; x < RaceDriver.length; x++) {
            CloneRaceDriver[x] = RaceDriver[x];
        }

        for (int x = 0; x < RaceDriver.length-1; x++) {              //Bubble sort technique
            for (int y = 0; y < RaceDriver.length-1 - x; y++) {
                if (CloneRaceDriver[y].getDriverS().getNumPoint() < CloneRaceDriver[y + 1].getDriverS().getNumPoint()) {
                    Driver Holder = CloneRaceDriver[y];
                    CloneRaceDriver[y] = CloneRaceDriver[y + 1];
                    CloneRaceDriver[y + 1] = Holder;
                }
            }
        }

        for (int x = 0; x < RaceDriver.length-1; x++) {              //Bubble sort technique
            for (int y = 0; y < RaceDriver.length -1 - x; y++) {
                if (CloneRaceDriver[y].getDriverS().getNumPoint() == CloneRaceDriver[y + 1].getDriverS().getNumPoint()) {
                    if (CloneRaceDriver[y].getDriverS().getFP() < CloneRaceDriver[y + 1].getDriverS().getFP()) {
                        Driver Holder = CloneRaceDriver[y];
                        CloneRaceDriver[y] = CloneRaceDriver[y + 1];
                        CloneRaceDriver[y + 1] = Holder;
                    }
                }
            }
        }

        // printing part
        String TableFormat = "\n| %-4d | %-18s | %-16s | %-17d | %-14d | %-21d |";
        System.out.print("\n========================================== Formula 1 Driver Table ===========================================");
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+-----------------------+");
        System.out.print("\n| Rank |    Driver Name     |       Team       |  Number of Races  |  Total Points  | No. of 1st Place Wins |");
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+-----------------------+");
        //some code Driver
        for (int x = 0; x < RaceDriver.length; x++) {
            if (!CloneRaceDriver[x].getDriverN().equals("E")) {
                System.out.printf(TableFormat, x+1, CloneRaceDriver[x].getDriverN(), CloneRaceDriver[x].getDriverT().getTeamN(), CloneRaceDriver[x].getDriverS().getNumRaces(), CloneRaceDriver[x].getDriverS().getNumPoint(), CloneRaceDriver[x].getDriverS().getFP());
            }
        }
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+-----------------------+");
    }
    
    //6.Add a Race Function 
    public static void AddRace(Scanner input){
        if (RacesCount==RaceF1.length){
            ChampionshipManager.ChampDecider();
            System.out.print("Unable to Create a New Race...\n\nThe F1 Championship Season is Over!\n\n" + F1SeasonChampion + " Emerged as the Final Champion! \n " );
        }else if (DriverCount == 0 || DriverCount == 1 ) {
            System.out.print("Unable to Create a New Race...\n\nplease Register at least 2 Drivers!" );
        }else {
            int raceParticipant = 0;

            RaceName = "Race " + String.valueOf(Racenum); 

            if (Racenum > 9){
                RaceDate = String.valueOf(Racenum) + "/12/21";
            }else{
                RaceDate = "0" + String.valueOf(Racenum) + "/12/21";
            }
            System.out.print("\nCreated "+ RaceName + " Sceduled for " + RaceDate+ "\n");
            Racenum++;

            boolean RaceDriverStatU = true;
            while (RaceDriverStatU) {
                System.out.print("\nDo you wish to Update stats of existing driver who took part in this Race ?(yes/y/no/n) : ");
                String AddUI = input.nextLine(); 
                if (AddUI.equals("yes") || AddUI.equals("y")){
                    ShowAllDriver();
                    System.out.print("Enter the S.No of the Driver whose stats you wish to update: ");
                    int SNo = Integer.parseInt(input.nextLine()); 
                    if (SNo >= RaceDriver.length) {
                        System.out.print("The entered S.No is Out of Range!: \n");
                        continue;
                    } else {
                        System.out.print("\nWhat Position did "+ RaceDriver[SNo].getDriverN() + " Finish the Race in ? : ");
                        int NPos = Integer.parseInt(input.nextLine());
                        RaceDriver[SNo].getDriverS().setRP(NPos);

                        for (int z = 0; z < RaceF1.length; z++) {
                            if (RaceDriver[SNo].getDRD(z).getRaceName().equals("NA")) {
                                RaceDriver[SNo].setDRD(z, new DRData(RaceName, RaceDate, NPos)); //seter + constructor
                                break;
                            }
                        }

                        raceParticipant ++;
                        System.out.print("Updated "+ RaceDriver[SNo].getDriverN() + " Stats in the Race " + RaceName + "! ");
                        System.out.print(RaceDriver[SNo].getDriverN() + " now has a total of " + RaceDriver[SNo].getDriverS().getNumPoint() + "!"); // NUKE THIS LINE IN THE END
                    }
                }else if (AddUI.equals("no") || AddUI.equals("n")){
                    RaceDriverStatU = false;
                }else{
                    System.out.print("\nPls Enter a Valid Answer!");
                }
            }

            boolean RaceNEWDriverAdd = true;
            while (RaceNEWDriverAdd){
                System.out.print("\nDo you wish to Add a NEW Driver to this Race ?(yes/y/no/n) : ");
                String AddUI = input.nextLine(); 
                if (AddUI.equals("yes") || AddUI.equals("y")){
                    AddDriver(input, RaceName, RaceDate);
                    raceParticipant ++;
                }else if (AddUI.equals("no") || AddUI.equals("n")){
                    RaceNEWDriverAdd = false;
                }else{
                    System.out.print("\nPls Enter a Valid Answer!");
                }
            }
            
            for (int x = 0; x < RaceF1.length; x++) {
                if (RaceF1[x].getMapN().equals("NA")) {
                    RaceF1[x] = new Races(RaceName, RaceDate);
                    RacesCount += 1;
                    System.out.print("\nCompleted Adding Data for New Race : " + RaceF1[x].getMapN()
                    + " That took Place on " + RaceF1[x].getRaceD() + " With " + raceParticipant + " dirvers!");
                    break;
                }
            }
        }    

    }

    //7.  Save Data
    public static void SaveData(){

        try (BufferedWriter bkp = new BufferedWriter(new FileWriter("Drivers.txt"))) { // taken from here : https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
            bkp.write(String.valueOf(DriverCount));  // Stores number of drivers in first line
            bkp.newLine();
            for (int x = 0; x < RaceDriver.length; x++) {        // Stores all driver details line by line
                bkp.write(RaceDriver[x].getDriverN());
                bkp.newLine();
                bkp.write(RaceDriver[x].getDriverT().getTeamN());
                bkp.newLine();
                bkp.write(RaceDriver[x].getDriverL());
                bkp.newLine();
                bkp.write(String.valueOf(RaceDriver[x].getDriverS().getNumRaces()));
                bkp.newLine();
                bkp.write(String.valueOf(RaceDriver[x].getDriverS().getNumPoint()));
                bkp.newLine();
                bkp.write(String.valueOf(RaceDriver[x].getDriverS().getFP()));
                bkp.newLine();
                bkp.write(String.valueOf(RaceDriver[x].getDriverS().getSP()));
                bkp.newLine();
                bkp.write(String.valueOf(RaceDriver[x].getDriverS().getTP()));
                bkp.newLine();
                // System.out.print("\nDriver part 1 Done Properly for"+RaceDriver[x].getDriverN()+ "!\n");

                // Experimental ----
                for (int y = 0; y < RaceF1.length; y++){ // RaceF1.length
                    bkp.write(RaceDriver[x].getDRD(y).getRaceName());   // Race name
                    bkp.newLine();
                    bkp.write(RaceDriver[x].getDRD(y).getRaceDate());              // Race Date
                    bkp.newLine();
                    bkp.write(String.valueOf(RaceDriver[x].getDRD(y).getRacepostion()));  // Race postion
                    bkp.newLine();
                    // System.out.print("\nSucessfully Saved "+ RaceDriver[x].getDriverN()+ " Data for Race " + RaceDriver[x].getDRD(y).getRaceName() + " that took place on " 
                    // + RaceDriver[x].getDRD(y).getRaceDate() + " where he came in postion : " + RaceDriver[x].getDRD(y).getRacepostion());
                }
                // Experimental ----
                // System.out.print("\nDriver part 2 Done Properly for"+RaceDriver[x].getDriverN()+ "!\n");
            }
            System.out.print("\nSucessfully Saved all F1 Registerd Driver data to Drivers.txt!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bkp = new BufferedWriter(new FileWriter("Races.txt"))) {
            bkp.write(String.valueOf(RacesCount));  // Stores number of Races in first line
            bkp.newLine();
            for (int x = 0; x < RaceF1.length; x++) {        // Stores all Race details line by line
                bkp.write(RaceF1[x].getMapN());
                bkp.newLine();
                bkp.write(RaceF1[x].getRaceD());
                bkp.newLine();

            }
            System.out.print("\nSucessfully Saved all F1 Race data to Races.txt!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //======================================================= EXTRA FUNCTIONS FOR SIMPLICITY =======================================================

    //Check all Driver Functions!
    public static void ShowAllDriver() {
        DriverCount=0;
        String TableFormat = "\n| %-4d | %-18s | %-16s |";
        System.out.print("\nDisplaying all Registerd F1 Drivers : \n");
        System.out.print("\n+------+--------------------+------------------+");
        System.out.print("\n| S.No |    Driver Name     |       Team       |");
        System.out.print("\n+------+--------------------+------------------+");
        
        for (int x = 0; x < RaceDriver.length; x++) {
            if (!RaceDriver[x].getDriverN().equals("E")) {
                System.out.printf(TableFormat, x, RaceDriver[x].getDriverN(), RaceDriver[x].getDriverT().getTeamN());
                //backup Counter Fixer
                DriverCount ++;
            }
        } 
        System.out.print("\n+------+--------------------+------------------+\n");
    }

    //Check Select Driver Stats!
    public static void IndividualDriverStats(int x){
        System.out.println("\n=================== S.No: "+x+" ===================");
        System.out.println("Name                    : "+RaceDriver[x].getDriverN());
        System.out.println("Team                    : "+RaceDriver[x].getDriverT().getTeamN());
        System.out.println("Location                : "+RaceDriver[x].getDriverL());
        System.out.println("-----------------------------------------------");
        System.out.println("Number of Races         : "+RaceDriver[x].getDriverS().getNumRaces());
        System.out.println("Total Points Earned     : "+RaceDriver[x].getDriverS().getNumPoint());
        System.out.println("-----------------------------------------------");
        System.out.println("No. of 1st Place Wins   : "+RaceDriver[x].getDriverS().getFP());
        System.out.println("No. of 2nd Place Wins   : "+RaceDriver[x].getDriverS().getSP());
        System.out.println("No. of 3rd Place Wins   : "+RaceDriver[x].getDriverS().getTP());
        System.out.println("================================================");
    }

    //X. Load Data 
    public static void LoadPrgrmData() {
        boolean DiverSaveCheck = false;
        boolean RaceSaveCheck = false;

        try {
            Scanner Loader = new Scanner(new FileInputStream("Drivers.txt"));  // taken from here : https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
            DriverCount = Integer.parseInt(Loader.nextLine());
            for (int x = 0; x < RaceDriver.length; x++) {
                
                String DName =Loader.nextLine(); 
                String DTeam = Loader.nextLine(); 
                String DLocation = Loader.nextLine(); 

                int Rnum = Integer.parseInt(Loader.nextLine()); 
                int Pnum = Integer.parseInt(Loader.nextLine());

                int FPnum = Integer.parseInt(Loader.nextLine());  
                int SPnum = Integer.parseInt(Loader.nextLine()); 
                int TPnum = Integer.parseInt(Loader.nextLine()); 

                RaceDriver[x] = new Driver(DName, DLocation, new Team(DTeam));                  // this running an exclusive overloaded constructor
                RaceDriver[x].setDriverS(new Formula1Driver(Rnum, Pnum, FPnum, SPnum, TPnum)); // this running an exclusive overloaded constructor
                // Experimental ----
                for (int y = 0; y < RaceF1.length; y++){

                    String RaceName = Loader.nextLine(); 
                    String RaceDate = Loader.nextLine(); 
                    int RacePos = Integer.parseInt(Loader.nextLine());

                    RaceDriver[x].setDRD(y,new DRData(RaceName, RaceDate, RacePos)) ;
                }
                // Experimental ----
                DiverSaveCheck = true;
            }
        } catch (IOException e) {
          // e.printStackTrace();
        }

        try {
            Scanner Loader = new Scanner(new FileInputStream("Races.txt"));
            RacesCount = Integer.parseInt(Loader.nextLine());
            for (int x = 0; x < RaceF1.length; x++) {
                
                String RMName = Loader.nextLine(); 
                String RMDate = Loader.nextLine(); 

                RaceF1[x] = new Races(RMName, RMDate);
                RaceSaveCheck = true;
            }
        } catch (IOException e) {
           // e.printStackTrace();
        }

        if (DiverSaveCheck && RaceSaveCheck) {
            System.out.print("\nSucessfully Restored Driver Data And Race Data from Last Session!\n");
        }else if (DiverSaveCheck && !RaceSaveCheck){
            System.out.print("\nSucessfully Restored Driver Data from Last Session!\n");
        }else if (!DiverSaveCheck && RaceSaveCheck){
            System.out.print("\nSucessfully Restored Race Data from Last Session!\n");
        }else{
            System.out.print("\nWelcome to Formula 1 Championship Manager!\n");
        }
    }

    //experimental Driver table drawer
    public static String[][] GrabDriverData() {
        String GDData[][] = new String[DriverCount][8];

        for (int x = 0; x < DriverCount; x++) {  // Stores all driver details line by line
            if (!RaceDriver[x].getDriverN().equals("E")) {
                String NoPoints;
                String Name = RaceDriver[x].getDriverN();
                String Team = RaceDriver[x].getDriverT().getTeamN();
                String Location = RaceDriver[x].getDriverL();
                String NoRace = String.valueOf(RaceDriver[x].getDriverS().getNumRaces());

                if (RaceDriver[x].getDriverS().getNumPoint() > 99){
                    NoPoints = String.valueOf(RaceDriver[x].getDriverS().getNumPoint());
                }else if (RaceDriver[x].getDriverS().getNumPoint() > 9) {
                    NoPoints = "0"+String.valueOf(RaceDriver[x].getDriverS().getNumPoint());
                }else{
                    NoPoints = "00"+String.valueOf(RaceDriver[x].getDriverS().getNumPoint());
                }
                
                String NoFP = String.valueOf(RaceDriver[x].getDriverS().getFP());
                String NoSP = String.valueOf(RaceDriver[x].getDriverS().getSP());
                String NoTP = String.valueOf(RaceDriver[x].getDriverS().getTP());
    
                GDData[x][0] = Name;
                GDData[x][1] = Team;
                GDData[x][2] = Location;
                GDData[x][3] = NoRace;
                GDData[x][4] = NoPoints;
                GDData[x][5] = NoFP;
                GDData[x][6] = NoSP;
                GDData[x][7] = NoTP;
            }
        }
        return GDData;
    }

    //experimental Race table drawer
    public static String[][] GrabRaceData() {
        String GRData[][] = new String[RaceF1.length][2];

        for (int x = 0; x < RacesCount; x++) {  // Stores all Race details line by line
            if (!RaceF1[x].getMapN().equals("NA")) {

                String Name = RaceF1[x].getMapN();
                String Date = RaceF1[x].getRaceD();

    
                GRData[x][0] = Name;
                //GRData[x][1] = Participants; //maybe later ? if have time
                GRData[x][1] = Date;
            }
        }
        return GRData;
    }

    //Dev Data Resetter For testing 
    public static void BDDataWipe(Scanner input) {
        System.out.print("\nSucessfully Enterd The Secret Developer Reset Method!\n");

        System.out.print("\n|Enter 'Delete' to Wipe all Race Data of All Drivers \n|Enter Input : ");
        String ResetResponse = input.nextLine(); 

        if (ResetResponse.equals("Delete")){
            for (int x = 0; x < RaceDriver.length; x++) {//Re setting the Driver type array with all race data set to empty
                int ResetNum = 0;

                RaceDriver[x].setDriverS(new Formula1Driver(ResetNum, ResetNum, ResetNum, ResetNum, ResetNum));
                RaceDriver[x].DriverRaceInit();
            }    
            for (int y = 0; y < RaceF1.length; y++){ //Re-initializing the races type array with all data set to empty
                RaceF1[y] = new Races("NA", "NA");
            }
            RacesCount =0;
            Racenum = 1;

            System.out.print("\n          ,-------,     ");
            System.out.print("\n       ,--'-----:---`--,  ");
            System.out.print("\n      ==(o)-------(o)==J  ");
            System.out.print("\n``````````````````````````````");
            System.out.print("\nSucessfully Reset All Drivers Race Data!");
        }else{
            System.out.print("\nPls Enter a Valid Answer!");
        }
    }

}