package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

public class MoveWithTime implements Command{
    double duration;
    double startTime;
    Robot robot;
    double leftstickx, leftsticky, rightstickx;
    boolean complete = false;
    boolean stopped = false;
    public MoveWithTime(Robot robot, double duration, double leftstickx, double leftsticky, double rightstickx)
    {
        this.duration = duration;
        this.robot = robot;
        this.leftstickx = leftstickx;
        this.leftsticky = leftsticky;
        this.rightstickx = rightstickx;
    }
    @Override
    public void start() {
        robot.mecanumDrive.setVelocity(this.leftstickx, this.leftsticky, this.rightstickx);
        startTime = robot.runtime.milliseconds();
    }
    @Override
    public void run() {
        robot.update();
    }


    @Override
    public boolean isComplete() {
        return complete = complete || startTime + duration > robot.runtime.milliseconds();
    }

    @Override
    public void end() {
        stop();
    }

    @Override
    public void stop() {
        complete = true;
        stopped = true;
        robot.mecanumDrive.stop();
    }

    @Override
    public void reset() {
        stop();
        complete = false;
        stopped = false;
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
}
