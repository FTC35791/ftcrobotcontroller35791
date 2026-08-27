package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class Mecanum2 extends OpMode {
    private DcMotor rightFront, leftFront, rightBack, leftBack;
    private GoBildaPinpointDriver pinpoint;
    private double power;
    private double theta;
    private double sin;
    private double cos;
    private double max;
    private double turn;
    private double currentHeading;

    private double rightFrontPower, leftFrontPower, rightBackPower, leftBackPower;

    @Override
    public void init() {
        hardwareInit();
    }

    @Override
    public void loop() {

        currentHeading = pinpoint.getHeading(AngleUnit.RADIANS);
        telemetry.addData("heading",currentHeading);
        telemetry.update();

        power = (Math.sqrt(gamepad1.left_stick_x * gamepad1.left_stick_x) + (gamepad1.left_stick_y * gamepad1.left_stick_y));
        theta = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x);
        sin = Math.sin(theta - Math.PI / 4);
        cos = Math.cos(theta - Math.PI / 4);
        max = Math.max(Math.abs(sin), Math.abs(cos));
        turn = gamepad1.right_stick_x; //setting the variable

        leftFrontPower = (power * sin / max - turn);
        rightFrontPower = (power * cos / max + turn);
        leftBackPower = (power * cos / max - turn);
        rightBackPower = (power * sin / max + turn);

        if((power + Math.abs(turn)) > 1) {
            leftFrontPower /= power + turn;
            leftBackPower /= power + turn;
            rightFrontPower /= power + turn;
            rightBackPower /= power + turn;
        }

        //two motors need to be negative
        leftFront.setPower(leftFrontPower);
        rightFront.setPower(-rightFrontPower);
        leftBack.setPower(-leftBackPower);
        rightBack.setPower(rightBackPower);

    }

    public void hardwareInit() {
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class,"pinpoint");
        pinpoint.resetPosAndIMU();

        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}

