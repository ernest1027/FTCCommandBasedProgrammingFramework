package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ColourSensor;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalRelease;

import java.util.HashMap;

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

    public void start(){
        runtime.reset();
    }

    //Updates every subsystems
    public void update() {
        colourSensor.update();
        mecanumDrive.update();
        wobbleGoalRelease.update();
    }

    public void stop(){
        mecanumDrive.stop();
    }

}
