package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive extends Subsystem{
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearRightMotor;
    private DcMotor rearLeftMotor;

    private double frontRightPower;
    private double frontLeftPower;
    private double rearRightPower;
    private double rearLeftPower;

    public MecanumDrive(HardwareMap map) {
        frontLeftMotor = map.dcMotor.get("FL");
        frontRightMotor = map.dcMotor.get("FR");
        rearRightMotor = map.dcMotor.get("BR");
        rearLeftMotor = map.dcMotor.get("BL");
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setVelocity(double leftstickx, double leftsticky, double rightstickx) {
        frontLeftPower = leftsticky + rightstickx + leftstickx;
        frontRightPower = leftsticky - rightstickx - leftstickx;
        rearLeftPower = leftsticky + rightstickx - leftstickx;
        rearLeftPower = leftsticky - rightstickx + leftstickx;
    }

    public void stop(){
        this.setVelocity(0,0,0);
        this.update();
    }
    @Override
    public void update(){
        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        rearLeftMotor.setPower(rearLeftPower);
        rearRightMotor.setPower(rearRightPower);
    }

}
