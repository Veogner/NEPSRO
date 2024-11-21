package org.firstinspires.ftc.teamcode.OfficalTeam;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;

@Config
public class OneConfigMultipleWorlds {

    public DcMotor leftBack, rightBack, leftFront, rightFront, Arm, RArm;
    public Servo Intake, RIntake;
    public IMU imu;

    public OneConfigMultipleWorlds () {

    }
    public void init(HardwareMap ahwmap){
        leftBack = ahwmap.get(DcMotor.class, "BaLeft");
        rightBack = ahwmap.get(DcMotor.class, "BaRight");
        rightFront = ahwmap.get(DcMotor.class, "FrRight");
        leftFront = ahwmap.get(DcMotor.class, "FrLeft");

        RArm = ahwmap.get(DcMotor.class, "RArm");
        Arm = ahwmap.get(DcMotor.class, "Arm");

        Intake = ahwmap.get(Servo.class, "Intake");
        RIntake = ahwmap.get(Servo.class, "RInt");

        Arm.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = ahwmap.get(IMU.class, "imu");
        imu.initialize(
                new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP)));

    }


    public void SliderRotate(int rotateTarget){
        int roaterPos;

        roaterPos = rotateTarget;
        RArm.setTargetPosition(roaterPos);
        RArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(RArm.getTargetPosition() != RArm.getCurrentPosition()){
            RArm.setPower(0.2);
        }
    }
}
