## About
This is a custom framework for First Tech Challenge robotics SDK to enable command based programming. This framework allows nested parallel and sequential commands during both autonomous/teleoperated periods to be simplified and run synchronously/asynchronously as desired. The framework also defines how subsytems should be implemented in order to work properly with commands. This was inspired by some of the paradigms found in the First Robotics Competition (FRC) library that have yet to make its way to the FTC SDK.

## Where to Find Files
Navigate to /TeamCode/src/main/java/org/firstinspires/ftc/teamcode/

## How it Works
The features of this framework can mainly be catgeorized into 2 parts

### Subsytems
Using an abstract subsystem class, the features of a subsystem are defined. Each subsytem object contains state properties that define the current state of the subsystem. The states are refreshed every robot cycle through the main robot.java class. Each subsystem contains at most three types of methods. The first type is a "state setter" method which sets the value of a subsystem's states. The second type is the update method which updates the subsystem by assigning the state values onto the subsystem. The third type is a "state getter" method which returns the state of the subsystem. By modularizing each subsystem and defining an abstract class, every subteam was able to contribute their subsystem class and easily implement it into the main development branch.

### Command based programming
As previously mentioned, the command based programming portion of the framework was inspired by my time working with the FRC WPILib. In my first FTC season, I struggled with the implementation of synchronous/asynchronous tasks. As such, I have implemented a commnad interface that is used for any action that change the state of the robot. Specifically, the states of the robots are only changed by classes that implement the command interface. Additionally, I had defined parallel and sequential command classes to further simplify the development of complicated autonomous routines. Parallel and sequential commands can now be nested within each other multiple times. This part of the framework allowed senior programmers to implement the  complicated command classes, whilst junior programmers were able to work on more high-level tasks such as mapping the commands to controller buttons or putting several commands together to create an autonomous routine.

