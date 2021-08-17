package org.firstinspires.ftc.teamcode.commands.samplecommands;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.Command;

public class MoveWobbleGoalServo implements Command {
    Robot robot;
    double position,delay, startingTime;
    boolean complete = false;
    boolean stopped = false;

    //Initializes command
    public MoveWobbleGoalServo(Robot robot, double startPosition, double delay)
    {
        this.robot = robot;
        this.position = position;
        this.delay = delay;
    }

    //Sets the starting servo position and runtime
    @Override
    public void start() {
        robot.wobbleGoalRelease.setPosition(position);
        startingTime = robot.runtime.milliseconds();
    }

    //Updates robot
    @Override
    public void run() {
        robot.update();
    }

    //Checks if command is complete using runtime
    @Override
    public boolean isComplete() {
        return complete = complete || startingTime+delay > robot.runtime.milliseconds();
    }


    @Override
    public void end() {
    }

    //Stops command prematurely
    @Override
    public void stop() {
        complete = true;
        stopped = true;
    }

    //Resets command
    @Override
    public void reset() {
        stop();
        complete = false;
        stopped = false;
    }

    //DEPRECATED
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
