package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Mecanum extends OpMode {
    private DcMotor rightFront, leftFront, rightBack, leftBack;
    private double power;
    private double theta;
    private double sin;
    private double cos;
    private double max;
    private double turn;

    private double rightFrontPower, leftFrontPower, rightBackPower, leftBackPower;

    @Override
    public void init() {
        hardwareInit();
    }

    @Override
    public void loop() {
        power = (Math.sqrt(gamepad1.left_stick_x * gamepad1.left_stick_x) + (gamepad1.left_stick_y * gamepad1.left_stick_y));
        theta = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x);
        sin = Math.sin(theta - Math.PI / 4);
        cos = Math.sin(theta - Math.PI / 4);
        max = Math.max(Math.abs(sin), Math.abs(cos));

        leftFrontPower = (power * cos / max + turn);
        rightFrontPower = (power * sin / max + turn);
        leftBackPower = (power * sin / max + turn);
        rightBackPower = (power * cos / max + turn);

        if((power + Math.abs(turn)) > 1) {
            leftFrontPower /= power + turn;
            leftBackPower /= power + turn;
            rightFrontPower /= power + turn;
            rightBackPower /= power + turn;
        }

        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftBack.setPower(leftBackPower);
        rightBack.setPower(rightBackPower);

    }

    public void hardwareInit() {
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
