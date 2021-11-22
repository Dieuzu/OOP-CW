
import javax.swing.*;    
public class ChampionshipManager extends Formula1ChampionshipManager {    
    JFrame f;    
    ChampionshipManager(){    
        f=new JFrame();    
 
        String column[]={"NAME","TEAM", "LOCATION", "No. of RACES", "No. of POINTS", "No. of 1st","No. of 2nd","No. of 3rd"};         
        JTable jt=new JTable(DriverData2D,column);    
        jt.setBounds(30,40,200,300);          
        JScrollPane sp=new JScrollPane(jt);    
        f.add(sp);          
        f.setSize(300,400);    
        f.setVisible(true);    
    }     
    public static void main(String[] args) {    
        new ChampionshipManager();    
    }    
}  
