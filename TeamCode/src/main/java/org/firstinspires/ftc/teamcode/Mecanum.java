package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Mecanum extends OpMode {
    private DcMotor rightFront, leftFront, rightBack, leftBack;

    private double power;

    @Override
    public void init () {
        hardwareInit();
    }

   @Override
    public void loop () {
     power = (Math.sqrt(gamepad1.left_stick_x * gamepad1.left_stick_x) + (gamepad1.left_stick_y * gamepad1.left_stick_y));
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
