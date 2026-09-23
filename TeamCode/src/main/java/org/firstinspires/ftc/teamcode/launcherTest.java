package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class launcherTest extends OpMode {
    private DcMotor rightFront, leftFront;

    private double rightFrontPower, leftFrontPower;

    @Override
    public void init() {
        hardwareInit();
    }

    @Override
    public void loop() {


        leftFrontPower = gamepad2.left_trigger-gamepad2.right_trigger;
        rightFrontPower = leftFrontPower;


        //two motors need to be negative
        leftFront.setPower(-leftFrontPower);
        rightFront.setPower(rightFrontPower);

    }

    public void hardwareInit() {
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
