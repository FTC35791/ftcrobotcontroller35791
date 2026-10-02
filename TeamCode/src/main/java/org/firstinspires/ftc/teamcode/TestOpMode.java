package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestOpMode extends BaseCodeTeleop{
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

    public void loop(){
        rightServo.setPower(super.SetServosSpeed(gamepad1.left_bumper));
        leftServo.setPower(-super.SetServosSpeed(gamepad1.right_bumper));

        leftFront.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y,gamepad1.right_stick_x)[0]);
        rightFront.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y,gamepad1.right_stick_x)[1]);
        leftBack.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y,gamepad1.right_stick_x)[2]);
        rightBack.setPower(super.getSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y,gamepad1.right_stick_x)[3]);

    }
}
