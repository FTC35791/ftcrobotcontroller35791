package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "Pinpoint Encoder Direction Test", group = "Diagnostics")
public class PinpointEncoderTest extends LinearOpMode {

    private GoBildaPinpointDriver odo;
    private DcMotor rightFront, rightBack, leftBack,leftFront;
    private double rightFrontPower, leftFrontPower, rightBackPower, leftBackPower;


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

    @Override
    public void runOpMode() {

        hardwareInit();
        // Initialize Pinpoint I2C device configured in the Robot Controller Hardware Map
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        /*
         * goBILDA Swingarm Pod Specs:
         * 13.26291192 counts per mm (8192 ticks / 48mm wheel circumference)
         */
        //odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaEncoder.GOBILDA_SWINGARM_POD);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        // Set initial encoder directions (FORWARD or REVERSE)
        odo.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD, // X-Pod (Forward/Reverse motion)
                GoBildaPinpointDriver.EncoderDirection.FORWARD  // Y-Pod (Strafe motion)
        );

        // Reset positions to 0 at start
        odo.resetPosAndIMU();

        telemetry.addData("Status", "Initialized. Press Play to test pods.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // MUST call update() every loop to refresh readings from the Pinpoint computer
            odo.update();

            // Status checks
            GoBildaPinpointDriver.DeviceStatus status = odo.getDeviceStatus();

            telemetry.addData("Device Status", status);
            telemetry.addData("Loop Time (ms)", odo.getLoopTime());
            telemetry.addData("Frequency (Hz)", odo.getFrequency());

            telemetry.addLine("\n--- ENCODER POD READINGS ---");
            // Raw Ticks
            telemetry.addData("X Pod Raw Ticks", odo.getEncoderX());
            telemetry.addData("Y Pod Raw Ticks", odo.getEncoderY());

            // Processed Pose
            telemetry.addData("X Distance (mm)", "%.2f", odo.getPosX(DistanceUnit.MM));
            telemetry.addData("Y Distance (mm)", "%.2f", odo.getPosY(DistanceUnit.MM));
            telemetry.addData("Heading (Deg)", "%.2f", odo.getHeading(AngleUnit.DEGREES));

            telemetry.addLine("\n--- DIRECTION VERIFICATION GUIDE ---");
            telemetry.addLine("• Push robot FORWARD: X Ticks should INCREASE (+)");
            telemetry.addLine("• Push robot LEFT: Y Ticks should INCREASE (+)");
            telemetry.addLine("• Rotate COUNTER-CLOCKWISE: Heading should INCREASE (+)");

            telemetry.update();
        }
    }
}

