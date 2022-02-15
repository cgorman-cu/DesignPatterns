# Project3
Project3 Object Oriented Programming
Ben Wright and Cayden Gorman

## Running
In order to run the program execute the command *java Project3* from the main directory.

## Assumptions
We assume that there will be a user to provide feedback to the executable at runtime.  The program will need to know how many days to run for and some number of mechanic names depending on how often they crash a monster truck.  We also assume that if a task ends in a failed state such engine sputtering after a tune up the mechanic will simply attempt the job again the next day and not re run the job until it completes successfully.  Our final assumption is that the mechanic will attempt each job on every car rather than all jobs sequentially on each car.



## Default Behavior
Factories are created for each vehicle sub-class. These factors then create instances of a vehicle and assign them to a Garage.
The garage the mechanic execute jobs on all the cars in the garage each day until the number of days requested has been reached.
A Garage Clock keeps track of the time passed in the Garage.

## Issues Encountered
We had to redesign and reassign functionality in the Garage class to Mechanic class.
This was due to making the Mechanic observiable.
Instead of the Garage, the Mechanic class iterators through each vehicle and performs work.

Observer class had to be reworked since it was backwards.
The classes used in the Observer patters were backwards, since the subject classes should have been the observiable classes and the obseraviable classes should have been the subject classes

Functionality for adding vehicles and assignig to a garage was moved from the Garage to the Factory class.
Previous code had to be reworked to add Factory class.
