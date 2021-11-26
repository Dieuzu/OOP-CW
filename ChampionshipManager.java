import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.table.*;
//import javax.swing.event.*;
import java.util.Random;  
// import static javax.swing.JOptionPane.showMessageDialog;

public class ChampionshipManager extends Formula1ChampionshipManager{  
    
    String GDData[][] = Formula1ChampionshipManager.GrabDriverData();
    String GRData[][] = Formula1ChampionshipManager.GrabRaceData();
    //public int Racenum = 1;
    public String driverNameR = "Driver";

    // Create the main panel
    private JPanel mainPanel = new JPanel(new BorderLayout());

    public ChampionshipManager(String[] args){
        //Scanner input = new Scanner(System.in);

        String column[]={"Name","Team", "Location", "No of Races", "Total Points", "No of 1st","No of 2nd","No of 3rd"};
        String columnR[]={"Race Name","Race Date"};

        //GUI stuff starts here

        //Table codes
        JTable StatTable = new JTable(GDData,column); // Driver Stats Table 
        StatTable.setBounds(30,40,1000,300);
        StatTable.setAutoCreateRowSorter(true);      //Sorting method referanced from : https://stackhowto.com/how-to-sort-jtable-column-in-java-2-methods/

        JTable RaceTable=new JTable(GRData,columnR); // Race Stats Table 
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
        JPanel topPanel = new JPanel(new BorderLayout()); //this panel holds the $Text Area$ + the 2 Tables [ [   ] |      |    ]
        JPanel topInnerPanel = new JPanel(new BorderLayout()); // this is an inner panel for topPanel       [ [   ] |      |    ]
        JPanel midPanel = new JPanel();                   //this panel holds the Textfield                  [   ==============  ]
        JPanel bottomPanel = new JPanel();                //this panel holds the buttons                    [   ===  ===  ===   ]

        //Pannel Sorting! :
        topInnerPanel.add(RaceDetesLF,BorderLayout.NORTH );  //[      ]
        topInnerPanel.add(TxtArea,BorderLayout.CENTER );     //[      ]

        //Adding $Text Area$ + the 2 Tables to the topPanel
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


        // Button Presses!!!!

        //action wen [randRace1button] button is clicked.
        randRace1button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RacesCount==MaxNumRaces){
                    JOptionPane.showMessageDialog(null, "Cannot Create a new race sorry");
                }else {
                    //Update the form
                    int raceParticipant = 0;
                    ArrayList<Integer> numbers = new ArrayList<Integer>(); // keep track position numbers of each driver from race

                    String RaceName = "Race " + String.valueOf(Racenum); 
                    String RaceDate;
                    if (Racenum > 9){
                        RaceDate = String.valueOf(Racenum) + "/12/21";
                    }else{
                        RaceDate = "0" + String.valueOf(Racenum) + "/12/21";
                    }
            
                    for (int x = 0; x < MaxNumDrivers; x++) {  // Stores all driver details line by line
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
                TextFieldConsole.setText("[Generate a Random Race with %] Has Been Pressed");
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
    // start the application in thread-safe
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Championship Manager");
                f.getContentPane().add(new ChampionshipManager(args).getComponent());
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setSize(1500,522);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}