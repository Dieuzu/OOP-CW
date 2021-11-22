import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.LinkedList;

public class Formula1ChampionshipManager{

    public static Driver[] RaceDriver = new Driver[15]; 
    public static Races[] RaceF1 = new Races[10];
    public static int DriverCount = 0;
    public static int RacesCount =0;

    public static String DriverData2D[][] = new String[15][8]; // [row] [column]


    public static void main(String[] args) { // MAIN METHOD! with Initializations + MenuFunction Call

        for (int x = 0; x < 15; x++) //initializing the Driver type array with all data set to empty
            RaceDriver[x] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
        for (int y = 0; y < 10; y++) //initializing the races type array with all data set to empty
            RaceF1[y] = new Races("NA", "NA");

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
                System.out.print("\nStarted Add New Driver Script ✔\n");
                AddDriver(input);
                Resubmit(input);
            } else if (userInput.equals("2")) {
                System.out.print("\nStarted Delete Driver Script ✔\n");
                DelDriver(input);
            } else if (userInput.equals("3")) {
                System.out.print("\nStarted Change Driver Team Script ✔\n");
                ChangeTeamDriver(input);
            } else if (userInput.equals("4")) {
                System.out.print("\nStarted Driver Stat Checker Script ✔\n");
                CheckDriverStats(input);
            } else if (userInput.equals("5")) {
                System.out.print("\nStarted F1 Driver Leaderboard Script ✔\n");
                DisplayDRVRStats(input); // working on this first cause why not
            } else if (userInput.equals("6")) {
                System.out.print("\nStarted Add F1 Race Script ✔\n");
                AddRace(input);
            } else if (userInput.equals("7")) {
                System.out.print("\nStarted F1 Championship Manager Save Data Script ✔\n");
                SaveData();
            } else if (userInput.equals("8")) {
                System.out.print("\nStarted GUI Script ✔\n");
                //function()
                TwoDArrayDriverData();
                ChampionshipManager.main(args);
            } else if (userInput.equals("0")) {
                SaveData();
                System.out.print("\nThank you For Trying the Application!\n");
                MenuCheck = false;
            } else {
                System.out.print("\nThe entered input is Invalid!\n");
            }
        }
    }

    //1. Add Driver Function
    public static void AddDriver(Scanner input) {
        if (DriverCount==15){
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
            for (int i =0; i<15; i++){
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
            System.out.print("Enter Driver Position in Race [or 0 if not Participated in any Race] : ");
            DPos = Integer.parseInt(input.nextLine());
            if (DPos < 0 || DPos > 15){
                System.out.print("Pls Enter a Valid Position between 1-15\n");
            }else {
                PositionChecker = false;
            }
        }   
        for (int x = 0; x < 15; x++) {
            if (RaceDriver[x].getDriverN().equals("E")) {
                RaceDriver[x] = new Driver(DName, DLocation, new Team(DTeam), new Formula1Driver(DPos));
                DriverCount += 1;
                if (DPos == 0){
                    System.out.print("\n✔ Added Data for Driver : " + RaceDriver[x].getDriverN() 
                    + " in Team "+ RaceDriver[x].getDriverT().getTeamN()  
                    + " From " + RaceDriver[x].getDriverL() 
                    + "\n                       ↳ No Additional Stats Added For "+ RaceDriver[x].getDriverN()+"!");        
                    break;

                }else{
                    System.out.print("\n✔ Added Data for Driver : " + RaceDriver[x].getDriverN() 
                    + " in Team "+ RaceDriver[x].getDriverT().getTeamN()  
                    + " From " + RaceDriver[x].getDriverL() 
                    + " who came in position " + RaceDriver[x].getDriverS().getRP() 
                    + " with a total points of " + RaceDriver[x].getDriverS().getNumPoint());        
                    break;
                }
            }
        }
    }

    //1.5 Resubmit Add Driver function
    public static void Resubmit(Scanner input){
        //Recursion time YAY
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
                System.out.print("\nDriver " + RaceDriver[DDNo].getDriverN()+ " of Team " + RaceDriver[DDNo].getDriverT().getTeamN() + " Has been Removed From Formula 1 Roster ✔\n");
                RaceDriver[DDNo] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
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
    
    //4. Check Driver Stats
    public static void CheckDriverStats(Scanner input){
        ShowAllDriver();
        Boolean Driverstatschecker = true;
        while(Driverstatschecker){
            System.out.print("\nEnter the S.No of the Driver Whose Stats you wish to see : ");
            int Sindex = Integer.parseInt(input.nextLine());

            if (Sindex >= DriverCount || Sindex < 0) {
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

    //5. Display table of drivers ! THIS IS NOT COMPLETE
    public static void DisplayDRVRStats(Scanner input){
        Driver[] CloneRaceDriver = new Driver[15]; // cloning Driver array as a backup to ensure no sorted change is permanant
        for (int x = 0; x < 15; x++) {
            CloneRaceDriver[x] = RaceDriver[x];
        }

        for (int x = 0; x < 14; x++) {              //Bubble sort technique
            for (int y = 0; y < 14 - x; y++) {
                if (CloneRaceDriver[y].getDriverS().getNumPoint() < CloneRaceDriver[y + 1].getDriverS().getNumPoint()) {
                    Driver Holder = CloneRaceDriver[y];
                    CloneRaceDriver[y] = CloneRaceDriver[y + 1];
                    CloneRaceDriver[y + 1] = Holder;
                }
            }
        }

        for (int x = 0; x < 14; x++) {              //Bubble sort technique
            for (int y = 0; y < 14 - x; y++) {
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
        for (int x = 0; x < 15; x++) {
            if (!CloneRaceDriver[x].getDriverN().equals("E")) {
                System.out.printf(TableFormat, x+1, CloneRaceDriver[x].getDriverN(), CloneRaceDriver[x].getDriverT().getTeamN(), CloneRaceDriver[x].getDriverS().getNumRaces(), CloneRaceDriver[x].getDriverS().getNumPoint(), CloneRaceDriver[x].getDriverS().getFP());
            }
        }
        System.out.print("\n+------+--------------------+------------------+-------------------+----------------+-----------------------+");
    }
    
    //6.Add a Race Function 
    public static void AddRace(Scanner input){
            int raceParticipant = 0;
            System.out.print("\nEnter Race Name : ");
            String RaceName = input.nextLine(); 
            System.out.print("Enter Race Date : ");
            String RaceDate = input.nextLine(); 

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
                    AddDriver(input);
                    raceParticipant ++;
                }else if (AddUI.equals("no") || AddUI.equals("n")){
                    RaceNEWDriverAdd = false;
                }else{
                    System.out.print("\nPls Enter a Valid Answer!");
                }
            }
            
            for (int x = 0; x < 10; x++) {
                if (RaceF1[x].getMapN().equals("NA")) {
                    RaceF1[x] = new Races(RaceName, RaceDate);
                    RacesCount += 1;
                    System.out.print("\n✔ Added Data for New Race : " + RaceF1[x].getMapN()
                    + " That took Place on " + RaceF1[x].getRaceD() + " With " + raceParticipant + " dirvers!");
                    break;
                }
            }
    }

    //7.  Save Data
    public static void SaveData(){
        try (BufferedWriter bkp = new BufferedWriter(new FileWriter("Drivers.txt"))) { // taken from here : https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
            bkp.write(String.valueOf(DriverCount));  // Stores number of drivers in first line
            bkp.newLine();
            for (int x = 0; x < 15; x++) {        // Stores all driver details line by line
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
            }
            System.out.print("\nSucessfully Saved all F1 Registerd Driver data to Drivers.txt!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bkp = new BufferedWriter(new FileWriter("Races.txt"))) {
            bkp.write(String.valueOf(RacesCount));  // Stores number of Races in first line
            bkp.newLine();
            for (int x = 0; x < 10; x++) {        // Stores all Race details line by line
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
        
        for (int x = 0; x < 15; x++) {
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
            for (int x = 0; x < 15; x++) {
                
                String DName =Loader.nextLine(); 
                String DTeam = Loader.nextLine(); 
                String DLocation = Loader.nextLine(); 

                int Rnum = Integer.parseInt(Loader.nextLine()); 
                int Pnum = Integer.parseInt(Loader.nextLine());

                int FPnum = Integer.parseInt(Loader.nextLine());  
                int SPnum = Integer.parseInt(Loader.nextLine()); 
                int TPnum = Integer.parseInt(Loader.nextLine()); 

                RaceDriver[x] = new Driver(DName, DLocation, new Team(DTeam)); // this running an exclusive overloaded constructor
                RaceDriver[x].setDriverS(new Formula1Driver(Rnum, Pnum, FPnum, SPnum, TPnum)); // this running an exclusive overloaded constructor
                DiverSaveCheck = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Scanner Loader = new Scanner(new FileInputStream("Races.txt"));
            RacesCount = Integer.parseInt(Loader.nextLine());
            for (int x = 0; x < 10; x++) {
                
                String RMName = Loader.nextLine(); 
                String RMDate = Loader.nextLine(); 

                RaceF1[x] = new Races(RMName, RMDate);
                RaceSaveCheck = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    //experimental table drawer
    public static void TwoDArrayDriverData() {
        for (int x = 0; x < 15; x++) {  // Stores all driver details line by line
            String Name = RaceDriver[x].getDriverN();
            String Team = RaceDriver[x].getDriverT().getTeamN();
            String Location = RaceDriver[x].getDriverL();
            String NoRace = String.valueOf(RaceDriver[x].getDriverS().getNumRaces());
            String NoPoints = String.valueOf(RaceDriver[x].getDriverS().getNumPoint());
            String NoFP = String.valueOf(RaceDriver[x].getDriverS().getFP());
            String NoSP = String.valueOf(RaceDriver[x].getDriverS().getSP());
            String NoTP = String.valueOf(RaceDriver[x].getDriverS().getTP());

            DriverData2D[x][0] = Name;
            DriverData2D[x][1] = Team;
            DriverData2D[x][2] = Location;
            DriverData2D[x][3] = NoRace;
            DriverData2D[x][4] = NoPoints;
            DriverData2D[x][5] = NoFP;
            DriverData2D[x][6] = NoSP;
            DriverData2D[x][7] = NoTP;
        }
    }
}