package org.firstinspires.ftc.teamcode.commands;

//The command interface is contains the basic structure for a command.
//A command can contain nested commands as long as it complies with the Command interface (See Parallel and Sequential classes)
public interface Command{

    //Run at the start of a command
    public void start();

    //Should be called within a loop to continously update the robot during the command.
    public void run();

    //Checks if the command is complete
    public boolean isComplete();

    //Run at the end of a command
    public void end();

    //Run if the command is to be stopped prematurely
    public void stop();

    //Run after the command has ended/stopped so that the command can be called again.
    public void reset();

    //(DEPRECATED) Called once to run the command from start to end.
    public boolean runLoop();

}