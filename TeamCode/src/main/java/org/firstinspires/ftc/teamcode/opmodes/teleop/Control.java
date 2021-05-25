package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.Command;
import org.firstinspires.ftc.teamcode.commands.MoveWithTime;
import org.firstinspires.ftc.teamcode.commands.MoveWobbleGoalServoAndMoveWithTime;

import java.util.HashMap;

//The Control class sets the states of every subsystem based on the gamepad inputs.
public class Control {
    OpMode opMode;
    Gamepad gamepad1, gamepad2;
    Robot robot;
    HashMap buttonPreviousValue, buttonCurrentValue;
    MoveWobbleGoalServoAndMoveWithTime mwgsamwt;
    MoveWithTime mwt;
    public Control(OpMode opMode, Robot robot)
    {
        this.opMode = opMode;
        this.gamepad1 = opMode.gamepad1;
        this.gamepad2 = opMode.gamepad2;
        this.robot = robot;

        //all commands used must be initialized here
        buttonPreviousValue = new HashMap<String, Boolean>();
        buttonCurrentValue = new HashMap<String, Boolean>();

        //Initialize button previous value
        buttonPreviousValue.put("a1", false);
        buttonPreviousValue.put("b1", false);
        buttonPreviousValue.put("LB1",false);
        buttonPreviousValue.put("RB1",false);



        //Initializes commands
        mwgsamwt = new MoveWobbleGoalServoAndMoveWithTime(robot, 2000, 0,1,0,1,0.5);
        mwt = new MoveWithTime(robot, 2000,0,1,0);
    }
    //Calls update method on all parts of the controller
    public void update()
    {
        dpad();
        joysticks();
        bumpers();
        buttons();
        triggers();
    }
    //Updates dpad and connected commands
    public void dpad()
    {

    }
    //Updates joysticks and connected commands
    public void joysticks()
    {
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        double strafe = gamepad1.left_stick_x;
        robot.mecanumDrive.setVelocity(drive, strafe, turn);
    }
    //Updates buttons and connected commands
    public void buttons()
    {
        //Updates current value for button
        buttonCurrentValue.put("a1", gamepad1.a);
        buttonCurrentValue.put("b1", gamepad1.b);
        buttonCurrentValue.put("LB1", gamepad1.left_bumper);
        buttonCurrentValue.put("RB1", gamepad1.right_bumper);

        //Calls run and stop button queries for a button and its corresponding command
        runCommandButton("a1",mwgsamwt);
        stopCommandButton("a1","b1",mwgsamwt);

        runCommandButton("LB1",mwt);
        stopCommandButton("LB1","RB1",mwt);
    }
    //Updates triggers and connected commands
    public void triggers()
    {

    }
    //updates bumpers and connected commands
    public void bumpers()
    {

    }

    //Binds button to running a command
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

    //Binds button to stopping a command
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
