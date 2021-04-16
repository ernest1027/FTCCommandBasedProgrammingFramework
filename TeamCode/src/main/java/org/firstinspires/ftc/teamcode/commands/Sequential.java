package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;

//A sequential command is a type of command that is used to run commands one after the other, or in other words sequentially.
//A sequential command can contain nested sequential commands, parallel commands or any other class that implements the command interface.
public class Sequential implements Command{

    //The list of commands stores the commands that will be run in order
    ArrayList<Command> commands;
    int counter = 0;
    public Sequential(ArrayList<Command> commands)
    {
        this.commands = commands;
    }

    //Runs the start function of the first command
    public void start() {
        commands.get(0).start();
    }

    /*
        The run function checks if the entire set of sequential commands is completed. If not, the run function will
        run the current command, then check if it is completed. If the current command is completed, the end function
        will be run, the counter will be incremented, and if there is another sequential command, the start
        function of the next command will be run.
     */
    public void run() {
        if(this.isComplete()){
            commands.get(counter).run();
            if(commands.get(counter).isComplete())
            {
                commands.get(counter).end();
                counter++;
                try{
                    commands.get(counter).start();
                }
                catch(Exception e)
                {

                }

            }
        }
        else
        {
            end();
        }
    }

    //The isComplete function checks the completion by comparing the counter to the length of the commands list
    @Override
    public boolean isComplete() {
        return counter == commands.size();
    }

    @Override
    public void end() {

    }
}
