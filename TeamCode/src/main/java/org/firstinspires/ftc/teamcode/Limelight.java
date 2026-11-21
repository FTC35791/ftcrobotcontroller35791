package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.hardware.limelightvision.Limelight3A;

public class Limelight extends OpMode {

    private Limelight3A limelight;

    private double CAMERA_HEIGHT = 18.42;
    private double CAMERA_ANGLE = 0;
    private double GOAL_HEIGHT = 40.64;
    private double distance;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class,"limelight");
        limelight.pipelineSwitch(0);
    }

    @Override
    public void start() {
        limelight.start();
    }
    @Override
    public void loop() {
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            distance = getDistance(llResult.getTy());
            telemetry.addData("Distance",distance);
        }
    }

    public double getDistance(double ty) {
        double angleToTarget = ty + CAMERA_ANGLE;
        double heightDifference = GOAL_HEIGHT - CAMERA_HEIGHT;
        return heightDifference/Math.tan(Math.toRadians(angleToTarget));
    }
}
