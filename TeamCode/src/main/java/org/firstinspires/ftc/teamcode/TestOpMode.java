package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestOpMode extends BaseCodeTeleop {

    private double laucherSpeed = 0;
    private boolean reversedLauncher = false;

    @Override
    public void init() {
        super.hardwareInit();
    }
    /*
    0- left front
    1-right front
    2-left back
    3- right back
     */

    public void loop() {
        rightServo.setPower(super.SetServosSpeed(gamepad1.left_bumper));
        leftServo.setPower(-super.SetServosSpeed(gamepad1.right_bumper));

        leftFront.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[0]);
        rightFront.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[1]);
        leftBack.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[2]);
        rightBack.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[3]);

        setLauncher();

        telemetry.addData("launcher",laucherSpeed);
        telemetry.addData("launcher reversed?",reversedLauncher);
        telemetry.update();
    }

    public void setLauncher() {
        if (gamepad2.aWasReleased() && laucherSpeed < 1) {
            laucherSpeed += 0.05;
        } else if (gamepad2.aWasReleased() && laucherSpeed == 1) {
            laucherSpeed = 0;
        } else if (gamepad2.dpadUpWasReleased() && laucherSpeed < 1) {
            laucherSpeed += 0.01;
        } else if (gamepad2.dpadUpWasReleased() && laucherSpeed == 1) {
            laucherSpeed = 0;
        }
        if (gamepad2.yWasReleased()) {
            reversedLauncher = !reversedLauncher;
        }

        if (reversedLauncher && !gamepad2.x) {
            rightLauncher.setPower(-laucherSpeed);
            leftLauncher.setPower(laucherSpeed);
        } else if (!reversedLauncher && !gamepad2.x) {
            rightLauncher.setPower(laucherSpeed);
            leftLauncher.setPower(-laucherSpeed);
        }else {
            rightLauncher.setPower(0);
            leftLauncher.setPower(0);
        }
    }
    public void setIntake() {
        if (gamepad2.y) {
            intake.setPower(0.5);
        }
    }
}
