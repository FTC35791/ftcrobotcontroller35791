package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class intakeTest extends OpMode{


    private DcMotor leftFront;
    private GoBildaPinpointDriver pinpoint;


    @Override
    public void init() {
        hardwareInit();
    }

    @Override
    public void loop() {

       leftFront.setPower(gamepad1.left_stick_y/2);

    }

    public void hardwareInit() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}


