package org.firsti3570nspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class PlainOldCode extends OpMode {

    //declaring motor variables
    private DcMotor rightDrive;
    private DcMotor leftDrive;

    @Override
    public void init() {
        //calling hardware init (see bottom of code)
        hardwareInit();
    }

    @Override
    public void loop() {

    }

    public void hardwareInit() {
        //setting motor variables to the actual motors in the robot
        rightDrive = hardwareMap.get(DcMotor.class,"rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class,"leftDrive");

        //setting motor zero power behavior to brake
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}

