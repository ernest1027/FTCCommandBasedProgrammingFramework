package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;
//The Control class sets the states of every subsystem based on the gamepad inputs.
public class Control {
    OpMode opMode;
    Gamepad gamepad1, gamepad2;
    Robot robot;
    public Control(OpMode opMode, Robot robot)
    {
        this.opMode = opMode;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.robot = robot;
    }
    public void update()
    {
        dpad();
        joysticks();
        bumpers();
        buttons();
        triggers();
    }

    public void dpad()
    {

    }
    public void joysticks()
    {
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        robot.mecanumDrive.setVelocity(drive, strafe, turn);
    }
    public void buttons()
    {
        if(gamepad1.a)robot.wobbleGoalRelease.togglePosition();
    }
    public void triggers()
    {

    }
    public void bumpers()
    {

    }
}
