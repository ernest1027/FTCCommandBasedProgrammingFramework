package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;
import java.util.Arrays;

//A parallel command is a type of command that is used to run commands simultaneously, or in other words in parallel.
//A parallel command can contain nested parallel commands, sequential commands or any other class that implements the command interface.
public class Parallel implements Command{
    ArrayList<Command> commands;
    boolean[] completed;
    boolean complete = false;
    boolean stopped = false;
    int counter = 0;

    //The list of commands stores the commands that will be run
    public Parallel(ArrayList<Command> commands)
    {
        this.commands = commands;
        completed = new boolean[commands.size()];
    }

    //Runs the start method of every command in the list
    @Override
    public void start() {
        for(int i = 0; i<commands.size(); i++)
        {
            commands.get(i).start();
        }
    }

    /*
       The run method runs every command in the list that has not already been completed.
       If the command is completed, the completed array is updated and the counter is incremented.
     */
    @Override
    public void run() {
        for(int i = 0; i<commands.size(); i++)
        {
                commands.get(i).run();
                if(completed[i] != commands.get(i).isComplete() && commands.get(i).isComplete()) {
                    completed[i] = true;
                    counter++;
                }
        }
    }

    //Compares the counter to the size of the command list to determine if the parallel command is completed
    @Override
    public boolean isComplete() {
        return complete = complete || counter == commands.size();
    }

    //Runs the end method of each individual command
    @Override
    public void end() {
        for(int i = 0; i<commands.size(); i++)
        {
            commands.get(i).end();
        }
    }

    //Runs the stop method of each individual command
    @Override
    public void stop() {
        complete = true;
        stopped = true;
        for(int i = 0; i<commands.size(); i++)
        {
            commands.get(i).stop();
        }
    }

    //Resets the command so it can be started again
    @Override
    public void reset() {
        complete = false;
        stopped = false;
        counter = 0;
        Arrays.fill(completed, false);
    }

    //DEPRECATED
    //Runs the entire parallel command with one method call
    @Override
    public boolean runLoop() {
        this.start();
        while(!this.isComplete())
        {
            this.run();
        }
        if(!stopped)
        {
            this.end();
        }
        return true;
    }
}
