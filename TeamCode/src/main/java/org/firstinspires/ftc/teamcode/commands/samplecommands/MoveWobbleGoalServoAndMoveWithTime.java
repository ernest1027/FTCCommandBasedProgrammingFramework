package org.firstinspires.ftc.teamcode.commands.samplecommands;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.commands.Command;
import org.firstinspires.ftc.teamcode.commands.Parallel;

import java.util.ArrayList;

public class MoveWobbleGoalServoAndMoveWithTime implements Command {
    Parallel parallel;

    //Initializes parallel command
    public MoveWobbleGoalServoAndMoveWithTime(Robot robot, double duration, double leftstickx, double leftsticky, double rightstickx, double position, double delay){
        MoveWithTime mwt = new MoveWithTime(robot, duration, leftstickx, leftsticky, rightstickx);
        MoveWobbleGoalServo mwgs = new MoveWobbleGoalServo(robot, position, delay);
        ArrayList<Command> commands = new ArrayList<Command>();
        commands.add(mwt);
        commands.add(mwgs);
        parallel = new Parallel(commands);
    }

    @Override
    public void start() {
        parallel.start();
    }
    @Override
    public void run() {
        parallel.run();
    }

    @Override
    public boolean isComplete() {
       return parallel.isComplete();
    }

    @Override
    public void end() {
        parallel.end();
    }

    @Override
    public void stop() {
        parallel.stop();
    }

    @Override
    public void reset() {
        parallel.reset();
    }

    @Override
    public boolean runLoop() {
        return parallel.runLoop();
    }

}
