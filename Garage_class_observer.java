interface Subject {
  void addObserver(GarageAnnouncer obj);
  void removeObserver(GarageAnnouncer obj);
  void Notify();
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
