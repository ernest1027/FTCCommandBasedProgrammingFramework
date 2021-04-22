package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.Command;
import org.firstinspires.ftc.teamcode.commands.MoveWobbleGoalServoAndMoveWithTime;

import java.util.HashMap;

//The Control class sets the states of every subsystem based on the gamepad inputs.
public class Control {
    OpMode opMode;
    Gamepad gamepad1, gamepad2;
    Robot robot;
    HashMap buttonPreviousValue, buttonCurrentValue;
    MoveWobbleGoalServoAndMoveWithTime mwgsamwt;
    public Control(OpMode opMode, Robot robot)
    {
        this.opMode = opMode;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.robot = robot;

        //all commands used must be initialized here
        buttonPreviousValue = new HashMap<String, Boolean>();
        buttonCurrentValue = new HashMap<String, Boolean>();

        buttonPreviousValue.put("a1", false);
        buttonPreviousValue.put("b1", false);




        mwgsamwt = new MoveWobbleGoalServoAndMoveWithTime(robot, 2000, 0,1,0,1,0.5);

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
        buttonCurrentValue.put("a1", gamepad1.a);
        buttonCurrentValue.put("b1", gamepad1.b);

        runCommandButton("a1",mwgsamwt);
        stopCommandButton("a1","b1",mwgsamwt);
    }
    public void triggers()
    {

    }
    public void bumpers()
    {

    }

    public void runCommandButton(String buttonName, Command command)
    {
        if(buttonPreviousValue.get(buttonName).equals(false) && buttonCurrentValue.get(buttonName).equals(true))
        {
            mwgsamwt.start();
            buttonPreviousValue.put("a", true);
        }
        if(buttonPreviousValue.get("a").equals(true))
        {
            mwgsamwt.run();
            if(mwgsamwt.isComplete())
            {
                mwgsamwt.end();
                mwgsamwt.reset();
                buttonPreviousValue.put("a",false);
            }
        }
    }

    public void stopCommandButton(String runButtonName, String stopButtonName, Command command)
    {
        if(buttonPreviousValue.get(stopButtonName).equals(false) && buttonCurrentValue.get(stopButtonName).equals(true) && buttonPreviousValue.get(runButtonName).equals(true))
        {
           mwgsamwt.stop();
           mwgsamwt.reset();

           buttonPreviousValue.put(runButtonName,false);
           buttonPreviousValue.put(stopButtonName,true);
        }
        if(buttonPreviousValue.get(stopButtonName).equals(true) && buttonCurrentValue.equals(false))
        {
            buttonPreviousValue.put(stopButtonName,false);
        }
    }

}
