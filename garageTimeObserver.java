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
