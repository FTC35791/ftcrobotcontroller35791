package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class lalala extends OpMode {

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
        // this is where you put the code to drive the robot
        // any code here will loop about 50 times per second
        if (gamepad1.a) {
            rightDrive.setPower(.2);
        }
        if (gamepad1.b) {
            leftDrive.setPower(.2);
        }
        rightDrive.setPower(gamepad1.right_stick_x);
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

