package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
public class LimelightDistance extends OpMode {

    private Limelight3A limelight3A;

    private double CAMERA_HEIGHT_CM = 18.4 ;

    private double CAMERA_ANGLE = 0;

    private double GOAL_HEIGHT = 40.6;

    private double distance = 0;



    @Override
    public void init() {
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(0);

    }
    @Override
    public void start(){
        limelight3A.start();
    }
    @Override
    public void loop() {
        LLResult llResult = limelight3A.getLatestResult();

        if(llResult != null && llResult.isValid()){
            distance = getDistance(llResult.getTy());
        }
    }

    public double getDistance(double ty){
        double angleToTarget = CAMERA_ANGLE + ty;
        double heightDifference = GOAL_HEIGHT - CAMERA_HEIGHT_CM;

        return heightDifference / Math.tan(Math.toRadians(angleToTarget));
    }
}