package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WobbleGoalRelease extends Subsystem{
    public Servo wobbleGoalServo;
    double position;

    public WobbleGoalRelease(HardwareMap map) {
        wobbleGoalServo = map.get(Servo.class, "wobble goal servo");
    }

    //Setter method for wobble goal position
    public void setPosition(double position)
    {
        this.position = position;
    }

    //Update method to set position for wobble goal servo
    @Override
    public void update(){
       wobbleGoalServo.setPosition(position);
    }


}
