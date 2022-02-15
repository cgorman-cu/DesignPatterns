
/*
Source: https://www.geeksforgeeks.org/observer-pattern-set-2-implementation/
*/
import java.util.ArrayList;
import java.lang.Math;
import java.util.Iterator;
import java.util.Scanner;
class Main
{
    public static void main(String args[])
    {
      simulation sim1= new simulation();

    }
}
public abstract class vehicle {
	//Base constructor for vehicle
	vehicle(String plateNum, Garage home){
		this.plate = plateNum;
		this.shining = "dirty";
		this.runs = "broken";
		this.unlocked = true;
		this.home = home;
		home.addVehicle(this);
	}

	//Vehicle attributes
	private Garage home;
	private String plate;
	private String shining;
	private String runs;
	private String type;
	private boolean unlocked;
	//Getters
	public String getPlate(){
		return this.plate;
	}
	public String getShines(){
		return this.shining;
	}
  	public String getRuns(){
		return this.runs;
	}
	public String getType(){
		return this.type;
	}
  	public boolean getUnlocked(){
		return this.unlocked;
	}
	public Garage getHome(){
		return this.home;
	}
	//Returns the lock state as a string
	public String getLockState(){
		if(this.unlocked){
			return("unlocked");
		}
		else{
			return("locked");
		}
	}

  	//Setters
  	protected void setPlate(String init){
  		this.plate = init;
  	}
  	protected void setShines(String init){
  		this.shining = init;
  	}
  	protected void setRuns(String init){
  		this.runs = init;
  	}
  	protected void setUnlocked(boolean init){
  		this.unlocked = init;
  	}
  	protected void setType(String init){
  		this.type = init;
  	}
/****************************ENCAPSULATION********************************************************
All the vehicle attributes are hidden from other elements of the program and accessed through get
and set methods.  The garage and mechanic don't need immediate access to this data but they can
see it if they want to.  Subclasses can set their attributes but no other class should be updating
a vehicles attributes so those methods are protected.
*/

  	//MAIN METHODS

  	//Unlocks a car if possible -- guards are probably excessive
  	public boolean unlock(){
  		this.setUnlocked(true);
  		if (this.unlocked){
  			//Standard unlock print statement
  			System.out.println(this.type + " " + this.plate + " is " + this.getLockState());
  			return true;
  		}
  		else{
  			//If this happens we have major problems
  			System.out.println("Something impossible happened in vehicle unlock.  Exiting.");
  			System.exit(-1);
  			return false;
  		}
  	}

  	//Washes a car
  	public void wash(){
  		//Conditionals for shimmers vs shines
  		if ( Math.random() <= 0.3){
  			this.shimmers();
  		}
  		else{
  			this.shines();
  		}
  		System.out.println(this.type + " " + this.plate + " has been washed it " + this.shining);

  	}

  	//Updates shines
  	private void shines(){
  		this.shining = "shines";
  	}

  	//Updates shines to shimmers
  	private void shimmers(){
  		this.shining = "shimmers";
  	}

  	//Tunes a car returns true or false depending on whether it can be driven afterwards
  	public boolean tune(){
  		boolean ret;
  		if(Math.random() <= 0.25){
  			this.sputters();
  			ret =  false;
  		}
  		else{
  			this.runs();
  			ret = true;
  		}
  		System.out.println(this.type + " " + this.plate + " has been tuned it " + this.runs);
  		return ret;
  	}

  	//Sets to sputters
  	public void sputters(){
  		//Update runs to sputters
  		this.runs = "sputters";
  	}

  	//Sets to runs
  	public void runs(){
  		//Updates runs
  		this.runs = "runs";
  	}

  	//Takes the car for a drive if possible
  	public int drive(){
  		if(this.runs.equals("runs")){
  			//Standard output for test drive
  			System.out.println(this.type + " " + this.plate + " has been driven and returned to the Garage.");
  			return 1;
  		}
  		else{
  			//Conditional for if the vehicle is not running may want to handle this differently but works for now
  			System.out.println(this.type + " " + this.plate + " cannot be driven it currently " + this.runs);
  			return 0;
  		}

  	}

  	//Locks a car returns true if possible else false
  	public boolean lock(){
  		this.unlocked = false;
  		if (!this.unlocked){
  			//Standard lock print statement
  			System.out.println(this.type + " " + this.plate + " is " + this.getLockState());
  			return true;
  		}
  		else{
  			//If this happens we have major problems
  			System.out.println("Something impossible happened in vehicle lock.  Exiting.");
  			System.exit(-1);
  			return false;
  		}
  	}
}

//Class for cars
abstract class car extends vehicle{
	car(String plateNum, Garage home){
		super(plateNum,home);
	}

}

//Bikes generally do not have doors that can be unlocked override that behavior
abstract class motorcycle extends vehicle{
	motorcycle(String plateNum, Garage home){
		super(plateNum,home);
	}
	//We cant unlock a bike or trike
	public boolean unlock(){
		this.setUnlocked(true);
		System.out.println(this.getType() + " " + this.getPlate() + " cannot be unlocked it is a " + this.getType());
		return false;
	}
	//We also cant lock a bike or trike
	public boolean lock(){
		this.setUnlocked(true);
		System.out.println(this.getType() + " " + this.getPlate() + " cannot be unlocked it is a " + this.getType());
		return false;
	}

}

//Class for trucks
abstract class truck extends vehicle{
	truck(String plateNum, Garage home){
		super(plateNum,home);
	}
}

//Construction vehicles are extra special dirty and will need to be cleaned a few times before they go to standard wash behavior
abstract class construction extends vehicle{
	private int dirtLevel = 3;
	construction(String plateNum, Garage home){
		super(plateNum,home);
	}
	public int getDirtLevel(){
		return dirtLevel;
	}
	public void setDirtLevel(int dirt){
		dirtLevel = dirt;
	}

	//The first three times we wash a construction vehicle it stays dirty
	public void wash(){
		if(getDirtLevel() > 0){
			System.out.println(this.getType() + " " + this.getPlate() + " is still " + this.getShines());
			setDirtLevel(getDirtLevel() - 1);
		}
		//We've done three washes go ahead and run the normal wash
		else{
			super.wash();
		}
	}
}

//Can just inherit from vehicle
class wagon extends car{
	wagon(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Wagon");
	}
}

//Can just inherit from vehicle
class suv extends car{
	suv(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("SUV");
	}
}

//Can just inherit from vehicle
class hatchback extends car{
	hatchback(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Hatchback");
	}
}

//Can just inherit from vehicle
class convertible extends car{
	convertible(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Convertible");
	}
}

//Can just inherit from vehicle
class bike extends motorcycle{
	bike(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Bike");
	}
}

//Can just inherit from vehicle
class trike extends motorcycle{
	trike(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Trike");
	}
}

//NEEDS SPECIAL CODE FOR CANT DRIVE CANT TUNE *****************************************************
class sidecar extends motorcycle{
	sidecar(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Sidecar");
		this.setRuns("has no engine");
	}
	//Sidecars have doors that can be unlocked or locked retstore base lock behavior
	public boolean unlock(){
  		this.setUnlocked(true);
  		if (this.getUnlocked()){
  			//Standard unlock print statement
  			System.out.println(this.getType() + " " + this.getPlate() + " is " + this.getLockState());
  			return true;
  		}
  		else{
  			//If this happens we have major problems
  			System.out.println("Something impossible happened in vehicle unlock.  Exiting.");
  			System.exit(-1);
  			return false;
  		}
  	}
	public boolean lock(){
  		this.setUnlocked(false);
  		if (!this.getUnlocked()){
  			//Standard lock print statement
  			System.out.println(this.getType() + " " + this.getPlate() + " is " + this.getLockState());
  			return true;
  		}
  		else{
  			//If this happens we have major problems
  			System.out.println("Something impossible happened in vehicle lock.  Exiting.");
  			System.exit(-1);
  			return false;
  		}
  	}
  	//Sidecars do not however have an engine that can be tuned or driven on their own
	public boolean tune(){
		System.out.println(this.getType() + " " + this.getPlate() + " cannot be tuned it " + this.getRuns());
		return false;
	}
	public int drive(){
		System.out.println(this.getType() + " " + this.getPlate() + " cannot be driven on its own it " + this.getRuns());
		return 1;
	}

}
//DONE*********************************************************************************************

//Can just inherit from vehicle
class pickup extends truck{
	pickup(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Pickup");
	}
}

//NEEDS SPECIAL DRIVE LOGIC FOR CRASHES ***********************************************************
class monster extends truck{
	monster(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Monster truck");
	}

	public int drive(){
		if(this.getRuns().equals("runs")){
			//If the vehicle is in running condition see if it crashes
			if(Math.random() > 0.2){
				System.out.println(this.getType() + " " + this.getPlate() + " has been driven and returned to the Garage.");
				//Successful drive op return 0
				return 0;
			}
			else{
				//Bad things happen run all the crash logic
				System.out.println(this.getType() + " " + this.getPlate() + " has crashed.");
				return -1;
			}
		}
		else{
			//The vehicle is not in a drivable state
			System.out.println(this.getType() + " " + this.getPlate() + " cannot be driven it currently " + this.getRuns());
			return 1;
		}
	}
}
//NEEDS COORDINATION WITH Garage DONE**************************************************************

//Can just inherit from vehicle
class delivery extends truck{
	delivery(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Delivery van");
	}
}

//Bulldozers are even dirtier than normal construction vehicles
class bulldozer extends construction{
	bulldozer(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Bulldozer");
		this.setDirtLevel(5);
	}
}
/****************************POLYMORPHISM*********************************************************
This class is a good example of polymorphism.  It inherits most of its behavior from the vehicle
superclass but the wash behavior is overridden by the construction superclass because construction
vehicles are inherently dirty.  The bulldozer is assumed to beeven dirtier than the average
construction vehicle so it sets its dirt level even higher than the normal amount for a construction
vehicle.
*/

//Can just inherit from construction which inherited from vehicle
class tractor extends construction{
	tractor(String plateNum, Garage home){
		super(plateNum,home);
		this.setType("Tractor");
	}
}
class Garage implements Subject{
  //Member attributes
  ArrayList<Mechanic> mechanics;
  ArrayList<vehicle> vehicles;
  ArrayList<String> tasks = new ArrayList<String>();
  //Observer design patter
  private ArrayList<GarageAnnouncer> observerList;
  private GarageTime time;
  int daysRemaining ;
  int currDay=1;

  //Not useful under current build since a vehicle requires a garage to instantiate
  Garage(ArrayList<vehicle> inputVehicle, String mechName, int days){
    vehicles=inputVehicle;
    daysRemaining=days;
    Mechanic mech1 = new Mechanic("Joe");
    time= new GarageTime();
    time.setCurrTime(0);
    mechanics.add(mech1);
    tasks.add("unlock");
    tasks.add("wash");
    tasks.add("tune up");
    tasks.add("test drive");
    tasks.add("lockup");
  }

  //Sets up an empty garage that will run for however many days
  Garage(int days){
    daysRemaining = days;
    mechanics = new ArrayList<Mechanic>();
    vehicles = new ArrayList<vehicle>();
    observerList= new ArrayList<GarageAnnouncer>();
    time= new GarageTime();
    this.addObserver(time);
    time.setCurrTime(0);
    tasks.add("unlock");
    tasks.add("wash");
    tasks.add("tune up");
    tasks.add("test drive");
    tasks.add("lockup");
  }
  //Observer Patter Subject implementation
  @Override
  public void addObserver(GarageAnnouncer o){
    observerList.add(o);
  }
  @Override
  public void removeObserver(GarageAnnouncer o){
    observerList.remove(observerList.indexOf(o));
  }

  @Override
  public void Notify(){
    for (Iterator<GarageAnnouncer> it =
              observerList.iterator(); it.hasNext();)
        {
            GarageAnnouncer o = it.next();
            o.update();
        }
  }

  public void addVehicle(vehicle input){
    vehicles.add(input);
  }

  public void addVehicle(ArrayList<vehicle> input){
    for(vehicle item : input){
      vehicles.add(item);
    }
  }

  public void addMechanic(Mechanic input){
    mechanics.add(input);
  }

  public void addMechanic(){
    this.newMechanic();
  }

  public void crashed(vehicle crashee){
    //System.out.println("In crashed");
    vehicles.remove(crashee);
    System.out.println(mechanics.get(0).name + " has crashed " + crashee.getType() + " " + crashee.getPlate() + " and has been fired.");
    this.fireMechanic(mechanics.get(0));
    this.newMechanic();
  }

  private void fireMechanic(Mechanic fired){
    //Fire a mechanic that crashed a vehicle
    //System.out.println("In fired");
    mechanics.remove(fired);
  }

  private void newMechanic() {
    //System.out.println("In newMechanic");
    Mechanic newHire = new Mechanic();
    //How the Garage communicates with the Mechanic in the Observer Desgin Patter
    this.addObserver(newHire);
    mechanics.add(newHire);
  }
  //For one day of work
  //Does all the tasks on all vehicles in list
  public void perform_tasks() {
    //System.out.println("This is the tasks ");
    //System.out.println(tasks);
    //print_date();
    for (int i = 0; i < tasks.size(); i++) {

      String currTask = tasks.get(i);
      mechanics.get(0).setTask(currTask);
      time.AdvanceOne();

      //All observiable objects are updated. How subject communicates with observer
      Notify();

      if (currTask == "unlock") {
        //traverse list of vehicles and unlock
        for(int j=0; j<vehicles.size();j++){
          mechanics.get(0).unlock(vehicles.get(j));
        }
      }
      else if (currTask == "wash"){
        for(int j=0; j<vehicles.size();j++){
          mechanics.get(0).wash(vehicles.get(j));
        }
      }
      else if (currTask == "tune up"){
        for(int j=0; j<vehicles.size();j++){
          mechanics.get(0).tune_up(vehicles.get(j));
        }
      }
      else if (currTask == "test drive"){
        for(int j=0; j<vehicles.size();j++){
          //Check to see if the vehicle has crashed during the test drive
          if(!mechanics.get(0).test_drive(vehicles.get(j))){
            //Run crashed in cases of crashing
            this.crashed(vehicles.get(j));
            //Need to decrement j if the vehicle crashed otherwise we'll skip the next vehicle
            j--;
/****************************ABSTRACTION**********************************************************
The crash behavior here is a good example of abstraction.  The garage doesn't care how the crash
happened or how it got reported.  Also neither the vehicle nor the mechanic care about the crash
other than reporting it.  The garage hears about the crash and just handles it.
*/
          }
        }
      }

      else if (currTask == "lockup"){
        for(int j=0; j<vehicles.size();j++){
          mechanics.get(0).lockup(vehicles.get(j));
        }
      }

      else{
        System.out.println("no match");
      }
    }

    System.out.println("Mechanic "+mechanics.get(0).name+" has completed the work for Day "+currDay);
  }


  private void printDate(){
    System.out.println("Mechanic "+mechanics.get(0).name+" arrives at Garage on Day "+currDay);
  }

  //Runs the garage for the # of days requested
  public void runGarage(){
    for(int i=0; i<daysRemaining ; i++){
      this.printDate();
      time.setCurrTime(0);
      this.perform_tasks();
      currDay++;
    }
    System.out.println("All days of work have been completed. Garage is closed");
  }
}





abstract class GarageEmployee {
  String name;

  // Abstract methods (does not have a body)
  public abstract void unlock(vehicle input);
  public abstract void wash(vehicle input);
  public abstract void tune_up(vehicle input);
  public abstract boolean test_drive(vehicle input);
  public abstract void lockup(vehicle input);

  //Testing only no longer used
  //public abstract void print_date();
}
class Mechanic extends GarageEmployee implements GarageAnnouncer{
  String name;
  String currTask;

/****************************IDENTITY*************************************************************
A name is an example of Identity.  It is the identifying characteristic of a mechanic.
*/

  Mechanic() {
    //Get the mechanic's name from the user
    name="Joe1";
    currTask="";
    //System.out.println("My Name: "+ name);
  }
  Mechanic(String input){
    name = input;
    currTask="";
  }

  //Runs an unlock on a vehicle
  public void unlock(vehicle input) {
    System.out.println(name+" unlocked " + input.getPlate());
    input.unlock();
  }

  //Runs a wash job on a vehicle
  public void wash(vehicle input) {
    System.out.println(name+" washed " + input.getPlate());
    input.wash();
  }

  //Runs the tune up job
  public void tune_up(vehicle input){
    System.out.println(name+" tuned " + input.getPlate());
    input.tune();
  }

  //Test drives a vehicle returns false if crashed otherwise true
  public boolean test_drive(vehicle input){
    System.out.println(name+" test drives " + input.getPlate());
    if(input.drive() == -1){
      //Handles crashed
      return false;
    }
    else{
      //Handles all other conditions
      return true;
    }
  }

  //Runs a lock job on a vehicle
  public void lockup(vehicle input){
    System.out.println(name+" locked " + input.getPlate());
    input.lock();
  }
  public void setTask(String task){
    currTask=task;
  }
  public String getTask(){
    return this.currTask;
  }
  public void update(){


    display();
  }
  public void display(){
    System.out.println("Hi, this is the Garage Announcer.  The Mechanic "+ name+" is about to "+currTask+" the vehicles!”.");
  }

  /*Testing only prints how many days they have left... a trait they no longer have
  public void print_date(){
    System.out.println("Mechanic "+name+" has "+ daysRemaining+ " left");
  }
*/

}
//Subject implements Garage class
interface Subject {
  void addObserver(GarageAnnouncer obj);
  void removeObserver(GarageAnnouncer obj);
  void Notify();
}
//GarageAnnouncer impliments mechanic
interface GarageAnnouncer{
  void update();
}

class GarageTime implements GarageAnnouncer{
  int currTime;
  int AmPmTime;
  GarageTime(){
    currTime=0;
  }
  public void AdvanceOne(){
    currTime++;
  }
  public int getCurrTime(){
    return currTime;
  }
  public void setCurrTime(int time){
    currTime=time;

  }
  public void update(){
    System.out.println("Hi this is the Garage Announcer");
    displayTime();
  }
  public void displayTime(){
    if (currTime <=4){
      AmPmTime=currTime+8;
      System.out.println("It is "+AmPmTime+" AM");
    }
    else if(currTime>4){
      AmPmTime=currTime-4;
      System.out.println("It is "+AmPmTime+" PM");
    }

  }
}







public class simulation{
	int days;


	simulation(){
		//How long do we run for
		int days;
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
   		System.out.println("Enter # of Days: ");
    	String daysRemainStr = myObj.nextLine();
    	days = Integer.parseInt(daysRemainStr);

		//Instantiate an empty garage
		Garage simGarage = new Garage(days);

    class Main
    {
        public static void main(String args[])
        {
          simulation sim1= new simulation();

        }
    }
		//Set up a bunch of vehicles and add them to the garage
		vehicle vehicle1;
		vehicle1 = new wagon("ABC123",simGarage);
		vehicle1 = new wagon("ABC123",simGarage);
		vehicle1 = new suv("ABC124",simGarage);
		vehicle1 = new hatchback("ABC125",simGarage);
		vehicle1 = new convertible("ABC126",simGarage);
		vehicle1 = new bike("ABC127",simGarage);
		vehicle1 = new trike("ABC128",simGarage);
		vehicle1 = new sidecar("ABC129",simGarage);
		vehicle1 = new pickup("ABC130",simGarage);
		vehicle1 = new monster("ABC131",simGarage);
		vehicle1 = new delivery("ABC132",simGarage);
		vehicle1 = new bulldozer("ABC133",simGarage);
		vehicle1 = new tractor("ABC134",simGarage);
		simGarage.addMechanic();

		simGarage.runGarage();
	}
}
