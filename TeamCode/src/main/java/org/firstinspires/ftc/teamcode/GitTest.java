package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp()
public class GitTest extends OpMode {

    private DcMotor rightDrive;
    private DcMotor leftDrive;

    @Override
    public void init() {
        rightDrive = hardwareMap.get(DcMotor.class,"Maddie");
        leftDrive = hardwareMap.get(DcMotor.class,"Niralya");
    }

    @Override
    public void loop() {
        int x = 5;

        if (gamepad1.left_stick_y >= 0.5) {
            setPowers(0.5,0.5); // sets wheel speed
        }

    }

    private void setPowers(double rightPower, double leftPower) {
        rightDrive.setPower(rightPower);
        leftDrive.setPower(leftPower);
    }

}
