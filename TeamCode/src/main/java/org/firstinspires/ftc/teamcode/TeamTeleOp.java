package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeamTeleOp extends BaseCodeTeleop {

    private double speed = 1;
    private double launcherSpeed = 0;
    private boolean rightServoOn, leftServoOn, firstIntakeOn;


    @Override
    public void init() {
        super.hardwareInit();
        rightServoOn = false;
        leftServoOn = false;
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

        telemetry.addData("launcher", launcherSpeed *100);
        telemetry.update();
    }

    public void setLauncher() {
        if(gamepad2.aWasReleased()) {

            if(gamepad2.left_bumper) {
                launcherSpeed = FARLAUNCHER;
            } else if (gamepad2.right_bumper) {
                launcherSpeed = CLOSELAUNCHER;
            } else {
                launcherSpeed = PERFECTLAUNCHER;
            }
        } else if(gamepad2.dpad_up) {
            launcherSpeed += 0.01;
        } else if(gamepad2.dpad_down) {
            launcherSpeed -= 0.1;
        } else if(gamepad2.y) {
            launcherSpeed = 0;
        }

        rightLauncher.setPower(-launcherSpeed);
        leftLauncher.setPower(launcherSpeed);

    }
    public void setIntake() {
        if (gamepad2.left_bumper && !firstIntakeOn) {
            intake.setPower(-1);
        } if (gamepad2.left_bumper && firstIntakeOn) {
            intake.setPower(0);
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
