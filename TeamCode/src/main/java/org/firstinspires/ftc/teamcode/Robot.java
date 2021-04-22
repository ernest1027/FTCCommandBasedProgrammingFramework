package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ColourSensor;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalRelease;

import java.util.HashMap;

//The Robot class contains all of the subsystems and the elapsed time object.
public class Robot{
    public ElapsedTime runtime = new ElapsedTime();
    public ColourSensor colourSensor;
    public MecanumDrive mecanumDrive;
    public WobbleGoalRelease wobbleGoalRelease;

    public Robot(HardwareMap map)
    {
        this.colourSensor = new ColourSensor(map);
        this.mecanumDrive = new MecanumDrive(map);
        this.wobbleGoalRelease = new WobbleGoalRelease(map);
    }

    //Run at the start of the op mode
    public void start(){
        runtime.reset();
    }

    //Updates every subsystem. Should be run within inside a loop to regularly update the robot.
    public void update() {
        colourSensor.update();
        mecanumDrive.update();
        wobbleGoalRelease.update();
    }

    //Run at the end of the op mode
    public void stop(){
        mecanumDrive.stop();
    }

}
