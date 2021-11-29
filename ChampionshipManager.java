import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;  
//import javax.swing.table.*;
//import javax.swing.event.*;
// import static javax.swing.JOptionPane.showMessageDialog;

public class ChampionshipManager extends Formula1ChampionshipManager{  

    String GDData[][] = Formula1ChampionshipManager.GrabDriverData();
    String GRData[][] = Formula1ChampionshipManager.GrabRaceData();
    public static Driver[] RaceDriverBKP = new Driver[DriverCount];

    public static String RaceName; 
    public static String RaceDate;

    public static ArrayList<Integer> StartPositionTracker = new ArrayList<Integer>(); // keeps track of Starting position for each driver from race
    public static ArrayList<Integer> FinalPositionTracker = new ArrayList<Integer>(); // keeps track of Finish position for each driver from race
    //public int Racenum = 1;
    public String driverNameR = "Driver"; 

    public static String RaceStatMSG = ""; 

    // Create the main panel
    private JPanel mainPanel = new JPanel(new BorderLayout());

    public static void main(String[] args) {                // Main Method for GUI Part
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Championship Manager");
                f.getContentPane().add(new ChampionshipManager(args).getComponent());
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Disabled Cause we dunt wanna close entire program wen gui is closed
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
        JButton randRace2button = new JButton("Generate a Random Race with %");
        JButton lookUpbutton = new JButton("Lookup All races of Driver");
        JButton clearbutton = new JButton("Clear GUI Console");

        //Text Field that doubles as inpput section + consol log
        JTextField TextFieldConsole = new JTextField();  
        TextFieldConsole.setColumns(134);
        TextFieldConsole.setText("\t\t\t\t\t    Welcome to the Championship Manager GUI!   |   This Textfield Acts as an 'Input Field' & a 'Console Log'!"); 

        // unused Text area code 
        JTextArea TxtArea=new JTextArea();
        JScrollPane scroll=new JScrollPane(TxtArea);
        TxtArea.setLineWrap(true);
        scroll.setBounds(320, 100, 800,200);

        //Lables
        JLabel RaceDetesLF =new JLabel(driverNameR+ " Race Data :                      ");    
        RaceDetesLF.setBounds(50,100, 250,20);   
        //JPanel lablePanel = new JPanel(); 
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
        //action wen [randRace1button] button is clicked.
        randRace1button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RacesCount==MaxNumRaces){
                    JOptionPane.showMessageDialog(null, "Cannot Create Anymore New Races Sorry! \nThe F1 Championship Season is Over!");
                }else {
                    //Update the form
                    int raceParticipant = 0;
                    ArrayList<Integer> numbers = new ArrayList<Integer>(); // keep track position numbers of each driver from race

                    RaceName = "Race " + String.valueOf(Racenum); //String <--------------- add this if u remove shyt top
                    //String RaceDate;
                    if (Racenum > 9){
                        RaceDate = String.valueOf(Racenum) + "/12/21";
                    }else{
                        RaceDate = "0" + String.valueOf(Racenum) + "/12/21";
                    }
            
                    for (int x = 0; x < MaxNumDrivers; x++) {  // generates random pos
                        if (!RaceDriver[x].getDriverN().equals("E")) {
                            Random racePosition = new Random();
                            boolean Race = true;
                            while (Race) {

                                int random = racePosition.nextInt(DriverCount+1);
                                if (!numbers.contains(random) && random !=0) {
                                    numbers.add(random);
                                    RaceDriver[x].getDriverS().setRP(random);

                                    for (int y = 0; y < MaxNumRaces; y++) {
                                        if (RaceDriver[x].getDRD(y).getRaceName().equals("NA")) {
                                            RaceDriver[x].setDRD(y, new DRData(RaceName, RaceDate, random)); //seter + constructor
                                            break;
                                        }
                                    }

                                    raceParticipant++;
                                    Race = false;
                                }
                            }    
                        }
                    }

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


                    for (int x = 0; x < MaxNumRaces; x++) {
                        if (RaceF1[x].getMapN().equals("NA")) {
                            RaceF1[x] = new Races(RaceName, RaceDate);
                            RacesCount ++;
                            JOptionPane.showMessageDialog(null,  "Added Data for : " + RaceF1[x].getMapN()+ " That took Place on " + RaceF1[x].getRaceD() + " With " + raceParticipant + " dirvers!");
                            break;
                        }
                    }
                    Racenum++;
                    
                    for (int x = 0; x < RacesCount; x++) {  // Stores all Race details line by line
                        if (!RaceF1[x].getMapN().equals("NA")) {
            
                            String BkpName = RaceF1[x].getMapN();
                            String BkpDate = RaceF1[x].getRaceD();
                            GRData[x][0] = BkpName;
                            GRData[x][1] = BkpDate;
                        }
                    }

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
                if (RacesCount==MaxNumRaces){
                    JOptionPane.showMessageDialog(null, "Cannot Create Anymore New Races Sorry! \nThe F1 Championship Season is Over!");
                }else {
                    RandomStartP();  // Runs a function that Gives Start Postions Randomly for all registerd Drivers
                    FinishPositions(TextFieldConsole);  // Runs a function that Generates Finish Positions for all Drivers based on the win % created from random Start Postions
                    RaceStatsReAsigner(Racenum); // Sends and updates the Data of each racer back to the main Array of Drivers 
                    ResetRaceSPTandFPT(); // resets the Tempt data created for the race

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

                    for (int x = 0; x < MaxNumRaces; x++) {
                        if (RaceF1[x].getMapN().equals("NA")) {
                            RaceF1[x] = new Races(RaceName, RaceDate);
                            RacesCount ++;
                            JOptionPane.showMessageDialog(null,  "Added Data for : " + RaceF1[x].getMapN()+ " That took Place on " + RaceF1[x].getRaceD() + " With " + DriverCount + " dirvers! \n\n" + RaceStatMSG);
                            break;
                        }
                    }
                    Racenum++;
                    
                    for (int x = 0; x < RacesCount; x++) {  // Stores all Race details line by line
                        if (!RaceF1[x].getMapN().equals("NA")) {
            
                            String BkpName = RaceF1[x].getMapN();
                            String BkpDate = RaceF1[x].getRaceD();
                            GRData[x][0] = BkpName;
                            GRData[x][1] = BkpDate;
                        }
                    }
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
                for (int i = 0; i < MaxNumDrivers;i++){
                    if (RaceDriver[i].getDriverN().equals(driverNameR)) {
                        TextFieldConsole.setText(driverNameR + " Is a Regiserd Driver in this Season of F1 Championship!        |        Loading All Race Data...");
                        //step 2 get the stats and start concatenation
                        for(int j = 0; j < MaxNumRaces; j++){
                            if (!RaceDriver[i].getDRD(j).getRaceName().equals("NA")){
                                String RN = RaceDriver[i].getDRD(j).getRaceName();
                                String RT = RaceDriver[i].getDRD(j).getRaceDate();
                                String RP = String.valueOf(RaceDriver[i].getDRD(j).getRacepostion());
    
                                //Concatdata = RN +" ("+ RT +")\nPosition : " + RP + "\n\n";
                                //RaceStats = RaceStats.concat(Concatdata);
                                RaceStats = RaceStats.concat(RN +" - ("+ RT +") :\nPosition : " + RP + "\n\n");
                            }
                        }
                        break;
                    }else {
                        TextFieldConsole.setText(driverNameR + " Is not a Regiserd Driver in this Season of F1 Championship!");
                    }
                }
                RaceDetesLF.setText(driverNameR+ " Race Data :   ");  
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

    //Get the main panel
    public JComponent getComponent() {
        return mainPanel;
    }

    // Part 5 methods below =======================
    //Step 1.0
    public static void RandomStartP() {
        //System.out.print("Generating Starting postions For the Race : \n");
        for (int x = 0; x < MaxNumDrivers; x++){ //Adding Registerd drivers from the main Driver array as a backup to ensure no modifications to RaceDriver till the end of the race
            if (!RaceDriver[x].getDriverN().equals("E")) {
                RaceDriverBKP[x] = RaceDriver[x];
                //System.out.print("\n Registerd : "+RaceDriver[x].getDriverN());
            }
        }

        for (int x = 0; x < DriverCount; x++) {  // Stores all driver details line by line
                Random raceSTARTposition = new Random();
                boolean Race = true;
                while (Race) {
                    int randomStartPosition = raceSTARTposition.nextInt(DriverCount+1);
                    if (!StartPositionTracker.contains(randomStartPosition) && randomStartPosition !=0) {
                        StartPositionTracker.add(randomStartPosition);
                        double WinPercent = WinPercent(randomStartPosition);
                        // System.out.print(RaceDriver[x] + " Started Race in Postion : " + random + "\nand has a " //UNCOMMENT ME FOR MORE INFO
                        // + WinPercent*100  + "% chance of coming First place\n\n");                               //UNCOMMENT ME FOR MORE INFO
                        RaceDriverBKP[x].setStartP(randomStartPosition);
                        RaceDriverBKP[x].setWinPercent(WinPercent);
                        Race = false;
                    }
                } 
            //}
        }
    }

    //Step 1.5
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

    //Step 2.0
    public static void FinishPositions(JTextField TextFieldConsole) {

        RaceStatMSG = ""; 
    
        //System.out.print("\n\nGenerating Final postions For the Race : \n");
        RaceWinnerDecider();

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
                RaceStatMSG = RaceStatMSG.concat(RaceDriverBKP[x].getDriverN() + " Won the Race with a Sucess rate of "+ RaceDriverBKP[x].getWinPercent()*100 +"% !\n");
                //System.out.print("\n"+RaceDriverBKP[x].getDriverN() + " Won the Race with a Sucess rate of "+ RaceDriverBKP[x].getWinPercent()*100 +"% !\n"); // console log msg
            }else {
                //System.out.print(RaceDriverBKP[x].getDriverN() + " Finished the Race in Postion : " + RaceDriverBKP[x].getFinishP() +"\n");  // UNCOMMENT FOR DEMONSTRATION PURPOSES
                RaceStatMSG = RaceStatMSG.concat(RaceDriverBKP[x].getDriverN() + " Finished the Race in Postion : " + RaceDriverBKP[x].getFinishP() +"\n");
            } 
        }
    }

    //Step 3.0
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

    //Step 3.4
    public static void ResetTheCurrentRace(){
        for (int x = 0; x < DriverCount; x++) {
            RaceDriverBKP[x].setFinishP(0);
        }
        FinalPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Final postion Tracker for all drivers from current  race
        //System.out.print("\n <DEBUG LOG> : Driver with 0% chance to get 1st Place Won the Race! A Race RESET was done \n"); // UNCOMMENT ME if need to Debug
    }

    //Step 3.7
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

    //Step 4.0
    public static void ResetRaceSPTandFPT(){
        StartPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Start postion Tracker for all drivers from LAST race
        FinalPositionTracker = new ArrayList<Integer>(); // Resets the Array List of Final postion Tracker for all drivers from LAST race
    }

    public static void RaceStatsReAsigner(int Racenum){
        RaceName = "Race " + String.valueOf(Racenum); 

        if (Racenum > 9){
            RaceDate = String.valueOf(Racenum) + "/12/21";
        }else{
            RaceDate = "0" + String.valueOf(Racenum) + "/12/21";
        }
        

            for (int x = 0; x < DriverCount; x++) {
                for (int y = 0; y < MaxNumDrivers; y++) {
                    if (RaceDriverBKP[x].getDriverN().equals(RaceDriver[y].getDriverN())){
                        //System.out.print("\n Found a Match for "+ RaceDriverBKP[x].getDriverN());
    
                        RaceDriver[y].getDriverS().setRP((RaceDriverBKP[x].getFinishP()));
                        
                        //System.out.print("\n Updated Stats for "+ RaceDriverBKP[x].getDriverN());
    
                        for (int z = 0; z < MaxNumRaces; z++) {
                            if (RaceDriver[y].getDRD(z).getRaceName().equals("NA")) {
                                RaceDriver[y].setDRD(z, new DRData(RaceName, RaceDate, RaceDriverBKP[x].getFinishP())); //seter + constructor
                                //System.out.print("\n Updated driver Race data for "+ RaceDriverBKP[x].getDriverN());
                                break;
                            }
                        }
                    }
                }
            }

    }
}