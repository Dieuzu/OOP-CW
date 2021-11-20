import java.util.Scanner;
public class DriverTestClass {
    public static Driver[] Test = new Driver[10];
    public static int DriverCount;
    public static void main(String[] args) {

        for (int x = 0; x < 10; x++) //initializing the Driver type array with all data set to empty
            Test[x] = new Driver("E", "E", new Team("E"), new Formula1Driver(0));
        Scanner input = new Scanner(System.in);
        AddDriver(input);
        resubmit(input);

    }

    public static void AddDriver(Scanner input) {
        int DPos = 0;
        System.out.print("\nEnter Driver's Name : ");
        String DName = input.nextLine(); 
        System.out.print("\nEnter Drivers Location : ");
        String DLocation = input.nextLine(); 
        System.out.print("\nEnter Drivers Team : ");
        String DTeam = input.nextLine(); 
    
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
        for (int x = 0; x < 10; x++) {
            if (Test[x].getDriverN().equals("E")) {
                Test[x] = new Driver(DName, DLocation, new Team(DTeam), new Formula1Driver(DPos));
                DriverCount += 1;
                System.out.print("\nAdded Data for Driver : " + Test[x].getDriverN() 
                                + " From " + Test[x].getDriverL() 
                                + " in Team "+ Test[x].getDriverT().getTeamN() 
                                + " who came in position " + Test[x].getDriverS().getRP() 
                                + " who has a total points of " + Test[x].getDriverS().getNumPoint()
                                + ". \nData Stored In Location " + x);
                                
                break;
            }
        }
    }

    //
    public static void resubmit(Scanner input){
        //Recursion time
        Boolean Resubmit = true;
        while (Resubmit){
            System.out.print("\n\nDo you want to add another driver? (yes/y/no/n) : ");
            String AddUI = input.nextLine(); 
            if (AddUI.equals("yes") || AddUI.equals("y")){
                AddDriver(input);
            }else if (AddUI.equals("no") || AddUI.equals("n")){
                Resubmit = false;
                System.out.print("\n\nAll Data Of all Drivers");
                for (int x = 0; x < 10; x++) {
                    if (!Test[x].getDriverN().equals("E")) {
                        System.out.print("\nData of Driver : " + Test[x].getDriverN()  
                        + " of Team "+ Test[x].getDriverT().getTeamN() 
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

}
