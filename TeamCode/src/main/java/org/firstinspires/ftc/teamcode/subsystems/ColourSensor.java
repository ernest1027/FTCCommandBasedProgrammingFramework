package org.firstinspires.ftc.teamcode.subsystems;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ColourSensor extends Subsystem{

    private ColorSensor sensorColor;
    private DistanceSensor sensorDistance;
    private float[] hsvValues;
    private float[] values;
    private double SCALE_FACTOR;
    private int relativeLayoutId;
    private View relativeLayout;
    public ColourSensor(HardwareMap hardwareMap)
    {
        sensorColor = hardwareMap.get(ColorSensor.class, "colour");
        sensorDistance = hardwareMap.get(DistanceSensor.class, "colour");
        hsvValues = new float[]{0F, 0F, 0F};
        values = hsvValues;
        SCALE_FACTOR = 255;
        relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
    }

    //Update method for colour sensor
    @Override
    public void update() {
        Color.RGBToHSV((int) (sensorColor.red() * SCALE_FACTOR),
                (int) (sensorColor.green() * SCALE_FACTOR),
                (int) (sensorColor.blue() * SCALE_FACTOR),
                hsvValues);
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });
    }

    //State getter method that returns RGB values as an integer between 0 and 255 inclusive
    public int[] getColours(){
        int[] values = new int[3];
        values[0] = sensorColor.red();
        values[1] = sensorColor.green();
        values[2] = sensorColor.blue();
        return values;
    }

}
