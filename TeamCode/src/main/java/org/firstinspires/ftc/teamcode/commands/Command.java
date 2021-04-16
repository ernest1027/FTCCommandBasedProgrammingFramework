package org.firstinspires.ftc.teamcode.commands;

//The command interface is contains the basic structure for a command. A command can be made for any state changes of the robot.
public interface Command{


    //The start function is called at the start of a command
    public void start();

    //The run function should be continuously called in a loop
    public void run();

    //The isComplete function is used to check if the function is complete
    public boolean isComplete();

    //The end function is called at once the command is complete
    public void end();

}