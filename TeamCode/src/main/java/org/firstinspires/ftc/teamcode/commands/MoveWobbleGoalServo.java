package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

public class MoveWobbleGoalServo implements Command{
    Robot robot;
    double position,delay, startingTime;
    boolean complete = false;
    boolean stopped = false;
    public MoveWobbleGoalServo(Robot robot, double position, double delay)
    {
        this.robot = robot;
        this.position = position;
        this.delay = delay;
    }
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
    @Override
    public void start() {
        robot.wobbleGoalRelease.setPosition(position);
        startingTime = robot.runtime.milliseconds();
    }

    @Override
    public void run() {
        robot.update();
    }
    @Override
    public boolean isComplete() {
        return complete = complete || startingTime+delay > robot.runtime.milliseconds();
    }

    @Override
    public void end() {
        stop();
    }

    @Override
    public void stop() {
        complete = true;
        stopped = true;
    }
    @Override
    public void reset() {
        stop();
        complete = false;
        stopped = false;
    }
}
