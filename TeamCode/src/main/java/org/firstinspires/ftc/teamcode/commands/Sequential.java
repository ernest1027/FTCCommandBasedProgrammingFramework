package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;

//A sequential command is a type of command that is used to run commands one after the other, or in other words sequentially.
//A sequential command can contain nested sequential commands, parallel commands or any other class that implements the command interface.
public class Sequential implements Command{

    //The list of commands stores the commands that will be run in order
    ArrayList<Command> commands;
    int counter = 0;
    boolean complete = false;
    boolean stopped = false;
    public Sequential(ArrayList<Command> commands)
    {
        this.commands = commands;
    }

    //Runs the start method of the first command
    @Override
    public void start() {commands.get(0).start();}

    //Runs a command in the list based on the counter. Runs the end of the current command and the
    //start of the next command when the current command is complete.
    @Override
    public void run() {
        if(isComplete())return;
        commands.get(counter).run();
        if(commands.get(counter).isComplete()) {
            counter++;
            if (counter < commands.size()) {
                commands.get(counter).start();
                commands.get(counter).end();
            }
        }
    }

    //The isComplete method checks the completion by comparing the counter to the length of the commands list
    @Override
    public boolean isComplete() {
        return complete = complete || counter == commands.size();
    }

    //Runs the end method of the last command
    @Override
    public void end() {
        commands.get(commands.size()-1).end();
    }

    //Runs the stop method of the current command
    @Override
    public void stop() {
        complete = true;
        stopped = true;
        commands.get(counter).stop();
    }

    //Resets the command so it can be used again
    @Override
    public void reset() {
        complete = false;
        stopped = false;
        counter = 0;
    }

    //Deprecated
    //Runs the whole parallel loop with a single function call
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
