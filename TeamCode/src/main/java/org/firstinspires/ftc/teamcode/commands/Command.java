package org.firstinspires.ftc.teamcode.commands;

//The command interface is contains the basic structure for a command. A command can be made for any state changes of the robot.
public interface Command{

    //The run method only needs to be called once and will run until completion unless the end or stop method is called prematurely.
    //The run method should check if the command is was previously completed.
    //Thereafter it will verify if it has just completed by calling the isComplete method. If it returns true, then
    //the end method is called. If not, whatever needs to be run is called.
    public boolean runLoop();

    //The start method is called at the start of a command
    public void start();



    public void run();

    //The isComplete method is used to check if the method is complete
    public boolean isComplete();

    //The end method is called at once the command is complete. It may run end-stage methods for the command before stopping all movement.
    //Should not be called outside of any class that implements command unless
    //the command needs to be ended prematurely. Otherwise, the run method will automatically call the end method.
    public void end();

    //The stop method ends the command without running any end methods (stops all movement).
    public void stop();

    public void reset();

}