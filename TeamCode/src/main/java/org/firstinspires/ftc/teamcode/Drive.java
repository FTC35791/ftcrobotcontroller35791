package org.firstinspires.ftc.teamcode;

public class Drive {
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

        return new double[]{leftFrontPower,-rightFrontPower,-leftFrontPower,rightBackPower};
    }
}
