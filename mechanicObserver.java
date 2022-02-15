interface GarageAnnouncer{
  void update();
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
