import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;  

public class ChampionshipManager extends Formula1ChampionshipManager{  

    String GDData[][] = Formula1ChampionshipManager.GrabDriverData(); // 2D Array Created for storing driver details to be displayed on GUI Table
    String GRData[][] = Formula1ChampionshipManager.GrabRaceData();   // 2D Array Created for storing Race details to be displayed on GUI Table

    public static Driver[] RaceDriverBKP = new Driver[DriverCount]; // Backuparray of all registerd drivers to manupilate the stats without affecting original Array

    public static ArrayList<Integer> StartPositionTracker = new ArrayList<Integer>(); // keeps track of Starting position for each driver from race
    public static ArrayList<Integer> FinalPositionTracker = new ArrayList<Integer>(); // keeps track of Finish position for each driver from race

    public String driverNameR = "Driver"; //Place holder string to Display Driver Race details on GUI

    public static String RaceStatMSG = "";  //Race Stats String for concatenation purposes to print final msg when button pressed

    // Create the main panel
    private JPanel mainPanel = new JPanel(new BorderLayout());

    public static void main(String[] args) {                // Main Method for GUI Part
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Championship Manager");
                f.getContentPane().add(new ChampionshipManager(args).getComponent());
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Disabled Cause we dunt wanna close entire program when gui is closed
                f.setSize(1500,522);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }

    public ChampionshipManager(String[] args){
        String column[]={"Name","Team", "Location", "No of Races", "Total Points", "No of 1st","No of 2nd","No of 3rd"}; // Driver stats Table Column
        String columnR[]={"Race Name","Race Date"};                                                                      // Race stats Table Column

        //GUI stuff STARTS here ===============================================================================================================>
        //Table codes
        JTable StatTable = new JTable(GDData,column); // Driver Stats Table 
        StatTable.setBounds(30,40,1000,300);
        StatTable.setAutoCreateRowSorter(true);       //Sorting method referanced from : https://stackhowto.com/how-to-sort-jtable-column-in-java-2-methods/

        JTable RaceTable=new JTable(GRData,columnR);  // Race Stats Table 
        RaceTable.setBounds(30,40,200,300); 
        RaceTable.setAutoCreateRowSorter(true);     

        //Add buttons
        JButton randRace1button = new JButton("Generate a Random Race");
        JButton randRace2button = new JButton("Generate a Random Race with Random Start Position and Win %");
        JButton lookUpbutton = new JButton("Lookup All Races of Driver");
        JButton clearbutton = new JButton("Clear GUI Console");

        //Text Field that doubles as input section + consol log!
        JTextField TextFieldConsole = new JTextField();  
        TextFieldConsole.setColumns(134);
        TextFieldConsole.setText("\t\t\t\t\t    Welcome to the Championship Manager GUI!   |   This Textfield Acts as an 'Input Field' & a 'Console Log'!"); 

        // Text area code 
        JTextArea TxtArea=new JTextArea();
        JScrollPane scroll=new JScrollPane(TxtArea);
        TxtArea.setLineWrap(true);
        scroll.setBounds(320, 100, 800,200);

        //Lables
        JLabel RaceDetesLF =new JLabel(driverNameR+ " Race Data :                      ");    
        RaceDetesLF.setBounds(50,100, 250,20);   
        RaceDetesLF.setAlignmentX(Component.CENTER_ALIGNMENT);


        //3 Sub panels for main pannel!
        JPanel topPanel = new JPanel(new BorderLayout());             //this panel holds the $Text Area$ + the 2 Tables [ [   ] |      |    ]
        JPanel topInnerPanel = new JPanel(new BorderLayout());        // this is an inner panel for topPanel            [ [   ] |      |    ]
        JPanel midPanel = new JPanel();                               //this panel holds the Textfield                  [   ==============  ]
        JPanel bottomPanel = new JPanel();                            //this panel holds the buttons                    [   ===  ===  ===   ]

        //Pannel Sorting! :
        topInnerPanel.add(RaceDetesLF,BorderLayout.NORTH );           //[      ]
        topInnerPanel.add(TxtArea,BorderLayout.CENTER );              //[      ]

        //Adding Text Area + the 2 Tables to the topPanel
        topPanel.add(new JScrollPane(topInnerPanel), BorderLayout.WEST );
        topPanel.add(new JScrollPane(StatTable), BorderLayout.CENTER);
        topPanel.add(new JScrollPane(RaceTable),BorderLayout.EAST);
        
        //Adding Textfield to the midPanel
        midPanel.add(TextFieldConsole);

        //Adding buttons to the bottomPanel
        bottomPanel.add(randRace1button);
        bottomPanel.add(randRace2button);
        bottomPanel.add(lookUpbutton);
        bottomPanel.add(clearbutton);

        //Adding the 3 Sub pannels to mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        //GUI stuff ENDS here ==================================================================================================================================   

        // Button Presses Actions!
        //action when [randRace1button] button is clicked.
        randRace1button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (RacesCount==RaceF1.length){
                    ChampDecider();
                    JOptionPane.showMessageDialog(null, "Unable to Create a New Race...\n\nThe F1 Championship Season is Over!\n\n" + F1SeasonChampion + " Emerged as the Final Champion! \n " );
                    TextFieldConsole.setText("The F1 Championship Season is Over! " + F1SeasonChampion + " Emerged as the Final Champion! \n ");
                }else if (DriverCount == 0 || DriverCount == 1 ) {
                    JOptionPane.showMessageDialog(null, "Unable to Create a New Race...\n\nplease Register at least 2 Drivers!" );
                }else {
                    RandomStartP(0,TextFieldConsole);
                    RaceStatsReAsigner(Racenum, 0);       // Sends and updates the Data of each racer back to the main Array of Drivers 
                    ResetRaceSPTandFPT();                 // Resets the Temporary data created for the race
                    RefreshRDTableArray(GDData, GRData);  // Re-gets all driver and race details to redraw tables
                    Racenum++;

                }
                StatTable.repaint(); //refreshes table
                RaceTable.repaint(); //refreshes table
                return;
            }
        });

        //action wen [randRace2button] button is clicked.
        randRace2button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //TextFieldConsole.setText("[Generate a Random Race with %] Has Been Pressed");
                if (RacesCount==RaceF1.length){
                    ChampDecider();
                    JOptionPane.showMessageDialog(null, "Unable to Create a New Race...\n\nThe F1 Championship Season is Over!\n\n" + F1SeasonChampion + " Emerged as the Final Champion! \n " );
                    TextFieldConsole.setText("The F1 Championship Season is Over! " + F1SeasonChampion + " Emerged as the Final Champion! \n ");
                }else if (DriverCount == 0 || DriverCount == 1 ) {
                    JOptionPane.showMessageDialog(null, "Unable to Create a New Race...\n\nplease Register at least 2 Drivers!" );
                }else {
                    RandomStartP(1, TextFieldConsole);    // Step 1. Runs a method that Gives Start Postions Randomly for all registerd Drivers
                    FinishPositions(TextFieldConsole);    // Step 2. Runs a method that Generates Finish Positions for all Drivers based on the win % created from random Start Postions
                    RaceStatsReAsigner(Racenum, 1);       // Step 3. Sends and updates the Data of each racer back to the main Array of Drivers 
                    ResetRaceSPTandFPT();                 // Resets the Temporary data created for the race
                    RefreshRDTableArray(GDData, GRData);
                    Racenum++;
                }
                StatTable.repaint(); //refreshes table
                RaceTable.repaint(); //refreshes table
                return;

            }  
        }); 

        //action wen [lookUpbutton] button is clicked.
        lookUpbutton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                //TextFieldConsole.setText("[lookUpbutton] Has Been Pressed");
                driverNameR = TextFieldConsole.getText();
                String RaceStats = "";
                // Step 1: check for the name of the driver
                for (int i = 0; i < RaceDriver.length;i++){
                    if (RaceDriver[i].getDriverN().equalsIgnoreCase(driverNameR)) {
                        TextFieldConsole.setText("Loading All "+RaceDriver[i].getDriverN()+" Formula 1 Season Race Data...");
                        //step 2 get the stats and start concatenation
                        for(int j = 0; j < RaceF1.length; j++){
                            if (!RaceDriver[i].getDRD(j).getRaceName().equals("NA")){
                                String RN = RaceDriver[i].getDRD(j).getRaceName();
                                String RT = RaceDriver[i].getDRD(j).getRaceDate();
                                String RP = String.valueOf(RaceDriver[i].getDRD(j).getRacepostion());
    
                                //Concatdata = RN +" ("+ RT +")\nPosition : " + RP + "\n\n";
                                //RaceStats = RaceStats.concat(Concatdata);
                                RaceStats = RaceStats.concat(RN +" - ("+ RT +") :\nPosition : " + RP + "\n\n");
                            }
                        }
                        RaceDetesLF.setText(RaceDriver[i].getDriverN()+ " Race Data :   ");  
                        break;
                    }else {
                        TextFieldConsole.setText(driverNameR + " Is not a Regiserd Driver in this Season of F1 Championship!");
                    }
                }
                
                TxtArea.setText(RaceStats);
            }  
        });
        //action wen [randRace2button] button is clicked.
        clearbutton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                TextFieldConsole.setText("");
            }  
        }); 

    }

    // Get the main panel
    public JComponent getComponent() {
        return mainPanel;
    }

//============================================================================ Methods For Race Generation =============================================================================
    // Step 1.0. Generates a Random Postion (Start)
    public static void RandomStartP(int Option, JTextField TextFieldConsole) {
        RaceStatMSG = ""; 

        for (int x = 0; x < RaceDriver.length; x++){ //Adding Registerd drivers from the main Driver array as a backup to ensure no modifications to RaceDriver till the end of the race
            if (!RaceDriver[x].getDriverN().equals("E")) {
                RaceDriverBKP[x] = RaceDriver[x];
            }
        }

        for (int x = 0; x < DriverCount; x++) {  // Stores all driver details line by line
            Random raceSTARTposition = new Random();
            boolean Race = true;
            while (Race) {
                int randomStartPosition = raceSTARTposition.nextInt(DriverCount+1);
                if (!StartPositionTracker.contains(randomStartPosition) && randomStartPosition !=0) {
                    StartPositionTracker.add(randomStartPosition);
                    RaceDriverBKP[x].setStartP(randomStartPosition);

                    if (Option == 1) {
                        double WinPercent = WinPercent(randomStartPosition);
                        RaceDriverBKP[x].setWinPercent(WinPercent);
                    }
                    Race = false;
                }
            } 
        }
        
        if (Option != 1) { // this is only for the normal Randomizer Code (no start position/ win%) only generates a finish postion!
            //bubble sort to arrange Drivers in order of race postions (using a temp Array of classes thats a clone of RaceDriver)
            for(int x = 0; x < DriverCount; x++) { //final sorting code for printing
                int winner = 1;

                for (int i = 0; i < DriverCount-1; i++) {              //Bubble sort so order will be like : 1st , 2nd , 3rd,......last place
                    for (int j = 0; j < DriverCount-1 - i; j++) {

                        if (RaceDriverBKP[j].getStartP() > RaceDriverBKP[j + 1].getStartP()) {
                            Driver Holder = RaceDriverBKP[j];
                            RaceDriverBKP[j] = RaceDriverBKP[j + 1];
                            RaceDriverBKP[j + 1] = Holder;
                        }

                    }
                }

                if (RaceDriverBKP[x].getStartP() == winner){  
                    TextFieldConsole.setText(RaceDriverBKP[x].getDriverN() + "\nWon the Race!");
                    RaceStatMSG = RaceStatMSG.concat(RaceDriverBKP[x].getDriverN() + " Won the Race!\n\n");
                }else {
                    RaceStatMSG = RaceStatMSG.concat(RaceDriverBKP[x].getDriverN() + " Finished the Race in Postion : " + RaceDriverBKP[x].getStartP() +"\n");
                } 
            }
        }

    }

    // Step 1.1. We are Calculating Driver chance to win race using Step 1.0
    public static double WinPercent(int x){
        double Percent = 0;
        if (x == 1){ 
            Percent = 0.4;  // 40% chance to finish Race in 1st place

        }else if (x == 2){
            Percent = 0.3;  // 30% chance to finish Race in 1st place

        }else if (x == 3 || x == 4 ){
            Percent = 0.1;  // 10% chance to finish Race in 1st place

        }else if (x == 5 || x == 6 || x == 7 || x == 8 || x == 9){
            Percent = 0.02; // 2% chance to finish Race in 1st place

        }else {
            Percent = 0.0;  // 0% chance to be in 1st place (we are assuming its IMPOSSIBLE TO WIN RACE with 0% to simulate to real probability)
        }

        return Percent;
    }

    // Step 2.0. Based on Step 1 and 1.1 We start this step
    public static void FinishPositions(JTextField TextFieldConsole) {

        RaceStatMSG = ""; // Resets the Concatenation Msg to empty!
    
        RaceWinnerDecider(); // Runs Step 2.1 to decide Who wins the race based on their win % from step 1.1

        //bubble sort to arrange Drivers in order of race postions (using a temp Array of classes thats a clone of RaceDriver)
        for(int x = 0; x < DriverCount; x++) { //final printing code
            int winner = 1;

            for (int i = 0; i < DriverCount-1; i++) {              //Bubble sort so order will be like : 1st , 2nd , 3rd,......last place
                for (int j = 0; j < DriverCount-1 - i; j++) {

                    if (RaceDriverBKP[j].getFinishP() > RaceDriverBKP[j + 1].getFinishP()) {
                        Driver Holder = RaceDriverBKP[j];
                        RaceDriverBKP[j] = RaceDriverBKP[j + 1];
                        RaceDriverBKP[j + 1] = Holder;
                    }

                }
            }

            if (RaceDriverBKP[x].getFinishP() == winner){  
                TextFieldConsole.setText(RaceDriverBKP[x].getDriverN() + "\nWon the Race with a Sucess rate of "+ RaceDriverBKP[x].getWinPercent()*100 +"% !");
                RaceStatMSG = RaceStatMSG.concat(RaceDriverBKP[x].getDriverN() + " Won the Race with a Sucess rate of "+ RaceDriverBKP[x].getWinPercent()*100 +"% !\n\n");
            }else {
                RaceStatMSG = RaceStatMSG.concat(RaceDriverBKP[x].getDriverN() + " Finished the Race in Postion : " + RaceDriverBKP[x].getFinishP() 
                //+"\n=>They Started the Race in Postion " + RaceDriverBKP[x].getStartP() + " And had a " + RaceDriverBKP[x].getWinPercent()*100 + "% of winning.\n" // UNCOMMENT FOR DEMONSTRATION PURPOSES
                +"\n");
            } 
        }
    }

    // Step 2.1. Uses Step 1.1 to decide the Final Postions of Each driver in the race 
    public static void RaceWinnerDecider(){
        int DriverPartiCount = 0; // counts all the drivers that particpated in the race to ensure Somone always wins in first place with a win %

        for (int x = 0; x < DriverCount; x++) {  // Stores all driver details line by line
            double Percent2Win =  RaceDriverBKP[x].getWinPercent();

            if (!FinalPositionTracker.contains(1)){ 
                boolean ChanceOfFIRST = new Random().nextDouble() <= Percent2Win;  //https://stackoverflow.com/questions/8183840/probability-in-java

                if((ChanceOfFIRST == true || DriverPartiCount == DriverCount-1) && RaceDriverBKP[x].getWinPercent() == 0.0){
                    //This condition ensures the Match will always have a winner with a winning % and cannot win race with 0% success rate.
                    // so the entire race stats will be reset till perfect random race is generated without a 0% win rate driver winning the entire race
                    ResetTheCurrentRace(); 
                    RaceWinnerDecider(); // hahahaha back to more recursions 
                    break;
                } else if((ChanceOfFIRST == true || DriverPartiCount == DriverCount-1) && RaceDriverBKP[x].getWinPercent() !=0.0){
                    FinalPositionTracker.add(1);
                    RaceDriverBKP[x].setFinishP(1);
                    DriverPartiCount++;
                }else{ // if they dunt get 1st place in the "if condition" above they will be randomly given an availble postion based on max number of driver participating.
                    FinishPositionGenerator(x);
                    DriverPartiCount++;
                }

            }else {
                FinishPositionGenerator(x);  
                DriverPartiCount++;
            }
        }
    }

    // Step 2.1.1. this Resets the Current race in the event a driver with 0% chance beats the odds!
    public static void ResetTheCurrentRace(){
        for (int x = 0; x < DriverCount; x++) {
            RaceDriverBKP[x].setFinishP(0);
        }
        FinalPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Final postion Tracker for all drivers from current  race
    }

    // Step 2.1.2. This generates Finish postions for Drivers who DO NOT get in 1st place 
    public static void FinishPositionGenerator(int DriverIndex){
        Random raceENDposition = new Random();
        boolean Race = true;
        while (Race) {

            int RandomEndPos = raceENDposition.nextInt(DriverCount+1);
            if (!FinalPositionTracker.contains(RandomEndPos) && RandomEndPos !=0 && RandomEndPos !=1) {
                FinalPositionTracker.add(RandomEndPos);

                RaceDriverBKP[DriverIndex].setFinishP(RandomEndPos);
                Race = false;
            }
        }    
    }

    // Method to Reset all Temp Data Created during a Race creation!
    public static void ResetRaceSPTandFPT(){
        StartPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Start postion Tracker for all drivers from LAST race
        FinalPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Final postion Tracker for all drivers from LAST race
    }

    // Method to Store all Generated race data back into the main RaceDriver Array in Formula1ChampionshipManager.java
    public static void RaceStatsReAsigner(int Racenum, int Option){
        RaceName = "Race " + String.valueOf(Racenum); 

        if (Racenum > 9){
            RaceDate = String.valueOf(Racenum) + "/12/21";
        }else{
            RaceDate = "0" + String.valueOf(Racenum) + "/12/21";
        }
        
        if (Option == 1){ // this option is for % Randomizer code
            for (int x = 0; x < DriverCount; x++) {
                for (int y = 0; y < RaceDriver.length; y++) {
                    if (RaceDriverBKP[x].getDriverN().equals(RaceDriver[y].getDriverN())){
                        RaceDriver[y].getDriverS().setRP((RaceDriverBKP[x].getFinishP()));
    
                        for (int z = 0; z < RaceF1.length; z++) {
                            if (RaceDriver[y].getDRD(z).getRaceName().equals("NA")) {
                                RaceDriver[y].setDRD(z, new DRData(RaceName, RaceDate, RaceDriverBKP[x].getFinishP())); //seter + constructor
                                break;
                            }
                        }
                    }
                }
            }
        }else {         // this option is for normal Randomizer code
            for (int x = 0; x < DriverCount; x++) {
                for (int y = 0; y < RaceDriver.length; y++) {
                    if (RaceDriverBKP[x].getDriverN().equals(RaceDriver[y].getDriverN())){
                        RaceDriver[y].getDriverS().setRP((RaceDriverBKP[x].getStartP()));
    
                        for (int z = 0; z < RaceF1.length; z++) {
                            if (RaceDriver[y].getDRD(z).getRaceName().equals("NA")) {
                                RaceDriver[y].setDRD(z, new DRData(RaceName, RaceDate, RaceDriverBKP[x].getStartP())); //seter + constructor
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // Method to Refresh Both tables on GUI
    public static void RefreshRDTableArray(String GDData[][], String GRData[][]){
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

        for (int x = 0; x < RaceF1.length; x++) {
            if (RaceF1[x].getMapN().equals("NA")) {
                RaceF1[x] = new Races(RaceName, RaceDate);
                RacesCount ++;
                JOptionPane.showMessageDialog(null,  "Added Data for : " + RaceF1[x].getMapN()+ " That took Place on " + RaceF1[x].getRaceD() + " With " + DriverCount + " dirvers! \n\n" + RaceStatMSG);
                break;
            }
        }
        
        for (int x = 0; x < RacesCount; x++) {  // Stores all Race details line by line
            if (!RaceF1[x].getMapN().equals("NA")) {

                String BkpName = RaceF1[x].getMapN();
                String BkpDate = RaceF1[x].getRaceD();
                GRData[x][0] = BkpName;
                GRData[x][1] = BkpDate;
            }
        }
    }

    // Method to Check All Data and Determine the Champion of the entire Season!
    public static void ChampDecider(){
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
        F1SeasonChampion = CloneRaceDriver[0].getDriverN();
    }

}