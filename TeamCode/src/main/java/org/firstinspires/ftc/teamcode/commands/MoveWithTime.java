package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class MoveWithTime implements Command{
    double duration;
    double startTime;
    Robot robot;
    double leftstickx, leftsticky, rightstickx;
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
        return startTime + duration > robot.runtime.milliseconds();
    }

    @Override
    public void end() {
        robot.mecanumDrive.stop();
    }
}
