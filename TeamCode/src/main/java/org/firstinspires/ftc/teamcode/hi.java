package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class hi extends OpMode {

    //declaring motor variables
    private DcMotor rightDrive;
    private DcMotor leftDrive;

    @Override
    public void init() {
    }
        hardwareInit();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
         rightDrive.setPower(0.5);
        }
        if(gamepad1.b) {
            leftDrive.setPower(0.75);
        }
        if (gamepad1.right_stick_x > 0.1) {
            leftDrive.setPower(gamepad1.right_stick_x);
        }
        rightDrive.setPower(gamepad1.right_stick_y);
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