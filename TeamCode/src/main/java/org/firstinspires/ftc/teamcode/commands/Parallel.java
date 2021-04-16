package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;

//A parallel command is a type of command that is used to run commands simultaneously, or in other words in parallel.
//A sequential command can contain nested parallel commands, sequential commands or any other class that implements the command interface.
public class Parallel implements Command{
    ArrayList<Command> commands;
    boolean[] completed;
    int counter = 0;

    //The list of commands stores the commands that will be run in order
    public Parallel(ArrayList<Command> commands)
    {
        this.commands = commands;
        completed = new boolean[commands.size()];
    }


    //Runs the start function of every command in the list
    public void start() {
        for(int i = 0; i<commands.size(); i++)
        {
            commands.get(i).start();
        }
    }

    /*
        The run function runs every command in the list if it has not already been completed.
        If the command is completed, the completed array is updated and the counter is incremented.
     */
    public void run() {
        for(int i = 0; i<commands.size(); i++)
        {
            if(!completed[i])
            {
                commands.get(i).run();
                completed[i] = commands.get(i).isComplete();
                if(completed[i]) {
                    commands.get(i).end();
                    counter++;
                }

            }
        }
    }

    //Compares the counter to the size of the command list to determine if the parallel command is completed
    public boolean isComplete() {
        return counter == commands.size();
    }

    @Override
    public void end() {

    }


}
