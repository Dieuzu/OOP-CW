import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.Random;  
// import static javax.swing.JOptionPane.showMessageDialog;

public class ChampionshipManager extends Formula1ChampionshipManager{   // https://stackhowto.com/how-to-update-a-row-in-jtable/
    
    // Create the main panel
    private JPanel mainPanel = new JPanel(new BorderLayout());
    String GDData[][] = Formula1ChampionshipManager.GrabDriverData();
    String GRData[][] = Formula1ChampionshipManager.GrabRaceData();
    public int Racenum = 1;
    // String data[][]={{"Speedy", "JRM", "DOH", "3","69","200","1","4",},    
    //                  {"Kizura",  "JRM", "UAE", "3","32","100","0","2",},    
    //                  {"Draj","Cupcake", "SL", "3","47","99","6","0",}
    //                 }; 

    public ChampionshipManager(String[] args){
        Scanner input = new Scanner(System.in);
        //old code 
        String column[]={"NAME","TEAM", "LOCATION", "No. of RACES", "No. of POINTS", "No. of 1st","No. of 2nd","No. of 3rd"};
        String columnR[]={"RACE NAME","RACE DATE"};
        String TempRData[][]={{"MONKEY","03/12/19"},{"MONKEY","08/06/92"},{"MONKEY","06/02/92"},{"MONKEY","15/02/04"},{"MONKEY","25/11/21"},{"MONKEY","15/04/90"},};

        JTable RaceTable=new JTable(GRData,columnR);  
        //JTable RaceTable=new JTable(GDData,column);   
        RaceTable.setBounds(30,40,300,300); 
        RaceTable.setAutoCreateRowSorter(true);     //referanced from : https://stackhowto.com/how-to-sort-jtable-column-in-java-2-methods/
        // old code above
        //Add button
        JButton addButton = new JButton("+ Add");
        //Update button
        JButton updateButton = new JButton("Generate a Random Race");
        //Button panel
        JPanel ButtonPannel = new JPanel();
        //Add buttons to panel
        ButtonPannel.add(addButton);
        ButtonPannel.add(updateButton);
    
        //Create the JTextFields panel
        JPanel TablePanel = new JPanel(new BorderLayout());
        //Add the JTextFields to the panel

        //Add the panels and the table to the main panel
        mainPanel.add(TablePanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(RaceTable), BorderLayout.CENTER);
        mainPanel.add(ButtonPannel, BorderLayout.SOUTH);

//--------------------------------------------------kiz start--------------------------------------------------------------------

        //button panel
        JPanel buttonPanel = new JPanel();

        //Text Fields  
        JTextField tf1 = new JTextField();  
        //tf1.setBounds(50,100, 200,30);  
        tf1.setColumns(70); 
        JPanel textPanel = new JPanel();

        //action listener
        addButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                tf1.setText("KIZ IS DA BEST!!!");
            }  
        }); 

        //Lables
        JLabel l =new JLabel("SUS IMPOSTER SUSSY BAKA EREN YEAGER");  
        l.setBounds(50,100, 250,20);   
        JPanel lablePanel = new JPanel(); 
        l.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Table and table panel
        JPanel tableOnePanel = new JPanel();
        
        JTable StatTable = new JTable(GDData,column);
        StatTable.setBounds(30,40,1000,300);
        JPanel tableTwoPanel = new JPanel();
        StatTable.setAutoCreateRowSorter(true);

        //extra panels added here
        JPanel topPanel = new JPanel(new BorderLayout());

        //adding everything into the panels
        //tableOnePanel.add(new JScrollPane(RaceTable));
        //tableOnePanel.setBounds(30,40,1000,300);

        //tableTwoPanel.add(new JScrollPane(StatTable));
        //tableOnePanel.setBounds(30,40,500,300);

        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);

        //lablePanel.add(l);

        textPanel.add(tf1);

        topPanel.add(new JScrollPane(StatTable), BorderLayout.CENTER);
        topPanel.add(new JScrollPane(RaceTable),BorderLayout.EAST);
        topPanel.add(l, BorderLayout.WEST );
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

//---------------------------------------------------kiz end-------------------------------------------------------------------

/// new shyts 
        // This code is called when the Update button is clicked.
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (RacesCount==MaxNumRaces){
                    JOptionPane.showMessageDialog(null, "Cannot Create a new race sorry");
                }else {
                    //Update the form
                    int raceParticipant = 0;
                    ArrayList<Integer> numbers = new ArrayList<Integer>(); // keep track position numbers of each driver from race

                    // System.out.print("\nEnter Race Name : ");
                    // String RaceName = input.nextLine(); 
                    // System.out.print("Enter Race Date : ");
                    // String RaceDate = input.nextLine(); 

                    String RaceName = "Race " + String.valueOf(Racenum); 
                    String RaceDate;
                    if (Racenum > 9){
                        RaceDate = String.valueOf(Racenum) + "/12/21";
                    }else{
                        RaceDate = "0" + String.valueOf(Racenum) + "/12/21";
                    }

                    //System.out.print("Generating Stats for all Registerd Drivers within the system...");
            
                    for (int x = 0; x < MaxNumDrivers; x++) {  // Stores all driver details line by line
                        if (!RaceDriver[x].getDriverN().equals("E")) {
                            Random racePosition = new Random();
                            boolean Race = true;
                            while (Race) {
                                
                                int random = racePosition.nextInt(DriverCount+1);
                                if (!numbers.contains(random) && random !=0) {
                                    numbers.add(random);
                                    RaceDriver[x].getDriverS().setRP(random);
                                    //STORE RACE DATA AND POS IN HERE CODe
                                    for (int y = 0; y < MaxNumRaces; y++) {
                                        if (RaceDriver[x].getDRD(y).getRaceName().equals("NA")) {
                                            RaceDriver[x].setDRD(y, new DRData(RaceName, RaceDate, random)); //seter + constructor
                                            break;
                                        }
                                    }
                                    //         //STORE RACE DATA AND POS IN HERE CODe ABOVE
                                    raceParticipant++;
                                   // System.out.print("\n"+RaceDriver[x].getDriverN()+" got pos : " + random); // comment me out later
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
                f.setSize(1500,600);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }
}