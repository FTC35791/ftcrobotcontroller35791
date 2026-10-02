package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;

@TeleOp
abstract public class BaseCodeTeleop extends OpMode {
    protected DcMotor rightFront;
    protected DcMotor leftFront;
    protected DcMotor rightBack;

    protected DcMotor leftBack;

    protected CRServo rightServo,leftServo;



    public double[] getSpeeds(double x1, double y1, double x2) {

        double power,theta,sin,cos,max,turn,leftFrontPower,leftBackPower,rightFrontPower,rightBackPower;

        power = (Math.sqrt(x1 * x1) + (y1*y1));
        theta = Math.atan2(y1, x1);
        sin = Math.sin(theta - Math.PI / 4);
        cos = Math.cos(theta - Math.PI / 4);
        max = Math.max(Math.abs(sin), Math.abs(cos));
        turn = x2; //setting the variable

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

        return new double[]{leftFrontPower,-rightFrontPower,-leftBackPower,rightBackPower};
    }
    public double SetServosSpeed(boolean Power){
        return (Power? 0.5:0);
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
        rightServo= hardwareMap.get(CRServo.class,"rightServo");
        leftServo= hardwareMap.get(CRServo.class,"leftServo");

    }
}
