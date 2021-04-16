package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.ColourSensor;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.WobbleGoalRelease;

import java.util.HashMap;

public class Robot extends Subsystem {
    public ElapsedTime runtime;
    public ColourSensor colourSensor;
    public MecanumDrive mecanumDrive;
    public WobbleGoalRelease wobbleGoalRelease;

    public Robot(HardwareMap map)
    {
        this.colourSensor = new ColourSensor(map);
        this.mecanumDrive = new MecanumDrive(map);
        this.wobbleGoalRelease = new WobbleGoalRelease(map);
    }

    @Override
    public void update() {
        colourSensor.update();
        mecanumDrive.update();
        wobbleGoalRelease.update();
    }

}
