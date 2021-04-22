package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Robot;

public class MoveWithColourSensor implements Command{

    Robot robot;
    int R, G, B, tolerance;
    double leftstickx, leftsticky, rightstickx;
    boolean complete = false;
    boolean stopped = false;
    public MoveWithColourSensor(Robot robot, int R, int G, int B, int tolerance, double leftstickx, double leftsticky, double rightstickx)
    {
        this.robot = robot;

        this.R = R;
        this.G = G;
        this.B = B;
        this.tolerance = tolerance;

        this.leftstickx = leftstickx;
        this.leftsticky = leftsticky;
        this.rightstickx = rightstickx;
    }
    @Override
    public void start() {
        robot.mecanumDrive.setVelocity(this.leftstickx, this.leftsticky, this.rightstickx);

    }
    @Override
    public void run() {
        robot.update();
    }

    @Override
    public boolean isComplete() {
        return complete = complete || Math.abs(robot.colourSensor.getColours()[0]-this.R) <= this.tolerance && Math.abs(robot.colourSensor.getColours()[1]-this.G) <= this.tolerance && Math.abs(robot.colourSensor.getColours()[2]-this.B) <= this.tolerance;
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
