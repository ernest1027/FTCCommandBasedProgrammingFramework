package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

public class MoveWithTime implements Command{
    double duration;
    double startTime;
    Robot robot;
    double leftstickx, leftsticky, rightstickx;
    boolean complete = false;
    boolean stopped = false;

    //Initializes command
    public MoveWithTime(Robot robot, double duration, double leftstickx, double leftsticky, double rightstickx)
    {
        this.duration = duration;
        this.robot = robot;
        this.leftstickx = leftstickx;
        this.leftsticky = leftsticky;
        this.rightstickx = rightstickx;
    }

    //Initializes runtime and sets velocity
    @Override
    public void start() {
        robot.mecanumDrive.setVelocity(this.leftstickx, this.leftsticky, this.rightstickx);
        startTime = robot.runtime.milliseconds();
    }

    //Updates all subsystems
    @Override
    public void run() {
        robot.update();
    }


    //Returns true when command is complete. Checks if the robot has moved for the provided duration parameter
    @Override
    public boolean isComplete() {
        return complete = complete || startTime + duration > robot.runtime.milliseconds();
    }

    //Sets the mecanum drive to 0
    @Override
    public void end() {
        robot.mecanumDrive.stop();
    }

    //Stops command prematurely. Sets the mecanum drive to 0
    @Override
    public void stop() {
        complete = true;
        stopped = true;
        robot.mecanumDrive.stop();
    }

    //Resets the command
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
