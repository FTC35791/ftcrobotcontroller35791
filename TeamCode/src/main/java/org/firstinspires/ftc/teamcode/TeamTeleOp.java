package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeamTeleOp extends BaseCodeTeleop {

    private double speed = 1;
    private double launcherSpeed = 0;
    private boolean firstIntakeOn, launcherOn;


    @Override
    public void init() {
        super.hardwareInit();
        launcherOn = false;
        firstIntakeOn = false;
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

        leftFront.setPower(speed * super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[0]);
        rightFront.setPower(speed * super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[1]);
        leftBack.setPower(speed * super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[2]);
        rightBack.setPower(speed * super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x)[3]);

        setLauncher();
        setIntake();
        setSpeed();

        telemetry.addData("launcher", launcherSpeed * 100);
        telemetry.update();
    }

    public void setLauncher() {
        if(gamepad2.aWasReleased() && !launcherOn) {
            launcherOn = true;
        }  else if(gamepad2.aWasReleased() && launcherOn) {
            launcherOn = false;
        }

        if(launcherOn) {
            if(gamepad2.leftBumperWasReleased()) {
                launcherSpeed = super.CLOSELAUNCHER;
            } else if(gamepad2.rightBumperWasReleased()) {
                launcherSpeed = FARLAUNCHER;
            }
        }

        if(gamepad2.dpad_up && launcherOn) {
            launcherSpeed += 0.01;
        } else if(gamepad2.dpad_down && launcherOn) {
            launcherSpeed -= 0.1;
        }

        if(launcherOn) {
            rightLauncher.setPower(-launcherSpeed);
            leftLauncher.setPower(launcherSpeed);
        } else {
            rightLauncher.setPower(0);
            leftLauncher.setPower(0);
        }


    }
    public void setIntake() {
        if (gamepad2.bWasReleased() && !firstIntakeOn) {
            intake.setPower(-1);
            firstIntakeOn = true;
        } if (gamepad2.bWasReleased() && firstIntakeOn) {
            intake.setPower(0);
            firstIntakeOn = false;
        }

        if(gamepad2.x) {
            secondIntake.setPower(1);
        } else {
            secondIntake.setPower(0);
        }
    }
    public void setServo() {

    }
    public void setSpeed() {
        if(gamepad1.left_trigger > 0.5) {
            speed = 0.5;
        } else {
            speed = 1;
        }
    }
}
