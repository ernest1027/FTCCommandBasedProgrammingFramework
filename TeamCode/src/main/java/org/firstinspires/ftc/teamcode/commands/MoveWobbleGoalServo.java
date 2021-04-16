package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class MoveWobbleGoalServo implements Command{
    Robot robot;
    double position,delay, startingTime;
    public MoveWobbleGoalServo(Robot robot, double position, double delay)
    {
        this.robot = robot;
        this.position = position;
        this.delay = delay;
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
        return startingTime+delay > robot.runtime.milliseconds();
    }

    @Override
    public void end() {
    }
}
