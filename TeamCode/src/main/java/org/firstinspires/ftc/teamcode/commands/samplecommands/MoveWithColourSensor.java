package org.firstinspires.ftc.teamcode.commands.samplecommands;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.Command;

public class MoveWithColourSensor implements Command {

    Robot robot;
    int R, G, B, tolerance;
    double leftstickx, leftsticky, rightstickx;
    boolean complete = false;
    boolean stopped = false;

    //Initializes command
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

    //Sets the velocity at the start of the command
    @Override
    public void start() {
        robot.mecanumDrive.setVelocity(this.leftstickx, this.leftsticky, this.rightstickx);

    }

    //Runs the update method of all subsystems
    @Override
    public void run() {
        robot.update();
    }

    //Returns true if the function is complete. Checks if the current colour sensor value is within the margin of error of the desired value
    @Override
    public boolean isComplete() {
        return complete = complete || Math.abs(robot.colourSensor.getColours()[0]-this.R) <= this.tolerance && Math.abs(robot.colourSensor.getColours()[1]-this.G) <= this.tolerance && Math.abs(robot.colourSensor.getColours()[2]-this.B) <= this.tolerance;
    }

    //Sets the mecanum drive to 0
    @Override
    public void end() {
        robot.mecanumDrive.stop();
        robot.update();
    }

    //Stops command prematurely. Sets the mecanum drive to 0
    @Override
    public void stop() {
        complete = true;
        stopped = true;
        robot.mecanumDrive.stop();
        robot.update();
    }

    //Resets the command
    @Override
    public void reset() {
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
