package org.firstinspires.ftc.teamcode.subsystems;
//A subsystem is a component of the robot. Each subsystem contains at most three types of methods.
//The first type is a "state setter" method which sets the value of a subsystem's states.
//The second type is the update method which updates the subsystem by assigning the state values onto the subsystem.
//The third type is a "state getter" method which returns the state of the subsystem.
public abstract class Subsystem {
    public abstract void update();
}
