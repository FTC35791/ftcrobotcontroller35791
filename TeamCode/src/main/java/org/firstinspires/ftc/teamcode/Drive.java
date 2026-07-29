package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Drive extends OpMode {

    //declaring motor variables
    private DcMotor rightDrive;
    private DcMotor leftDrive;

    @Override
    public void init() {
        //calling hardware init (see bottom of code)
        hardwareInit();
    }

    @Override
    public void loop() {
        double forward = gamepad1.left_stick_y;
        double turn = gamepad1.left_stick_x;

        leftDrive.setPower(-(forward- turn)/2);
        rightDrive.setPower(forward+turn/2);
    }





    public void hardwareInit() {
        //setting motor variables to the actual motors in the robot
        rightDrive = hardwareMap.get(DcMotor.class,"rightDrive");
        leftDrive = hardwareMap.get(DcMotor.class,"leftDrive");

        //setting motor zero power behavior to brake
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}

