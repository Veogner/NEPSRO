package org.firstinspires.ftc.teamcode.OfficalTeam;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;

@TeleOp(name = "N9362HD - E")
public class TwoTeleTwoWorlds extends LinearOpMode {
    OneConfigMultipleWorlds robt = new OneConfigMultipleWorlds();

    @Override
    public void runOpMode() {
        // Initialize the hardware
        robt.init(hardwareMap);
        robt.Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        robt.RArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robt.RArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robt.Arm.setPower(0);


        waitForStart();

        while(opModeIsActive()) {

            double speedMultiplier = 0.35 + (1 - 0.35) * gamepad1.left_trigger;

            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.start) {
                robt.imu.resetYaw();
            }

            double botHeading = robt.imu.getRobotYawPitchRollAngles().getYaw    (AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = ((rotY + rotX + rx) / denominator) * speedMultiplier;
            double backLeftPower = ((rotY - rotX + rx) / denominator) * speedMultiplier;
            double frontRightPower = ((rotY - rotX - rx) / denominator) * speedMultiplier;
            double backRightPower = ((rotY + rotX - rx) / denominator) * speedMultiplier;

            robt.leftFront.setPower(frontLeftPower);
            robt.leftBack.setPower(backLeftPower);
            robt.rightFront.setPower(frontRightPower);
            robt.rightBack.setPower(backRightPower);
            x

            telemetry.addData("CurrentPOS ", robt.Arm.getCurrentPosition());
            telemetry.update();

            if(gamepad2.x){
                robt.SliderRotate(1600);
            }
            if(gamepad2.a){
                robt.SliderRotate(330);
            }
            if(gamepad2.y){
                robt.SliderRotate(600);
            }
            if(gamepad2.b) {
                robt.SliderRotate(1200);
            }

            if(gamepad2.right_trigger > 0){
                double moveSLUP = gamepad2.right_trigger;
                robt.Arm.setPower(moveSLUP * 0.5);
            }
            if(gamepad2.left_trigger > 0){
                double moveSLDWN = gamepad2.left_trigger;
                robt.Arm.setPower(moveSLDWN * -0.6);
            }


            if(gamepad2.dpad_left){
                robt.Intake.setPosition(1.0);
            } else if (gamepad2.dpad_right) {
                robt.Intake.setPosition(0.0);
            } else{
                robt.Intake.setPosition(0.5);
            }
            telemetry.addData("Servo posiition: ", robt.RIntake.getPosition());
            telemetry.update();
            if(gamepad2.left_bumper){
                robt.RIntake.setDirection(Servo.Direction.FORWARD);
                robt.RIntake.setPosition(0.0);
                telemetry.addData("Servo posiition: ", robt.RIntake.getPosition());
                telemetry.update();
            } else if (gamepad2.right_bumper) {
                robt.RIntake.setDirection(Servo.Direction.FORWARD);
                robt.RIntake.setPosition(0.28);
                telemetry.addData("Servo posiition: ", robt.RIntake.getPosition());
                telemetry.update();
            }

            telemetry.addData("CurrentPOS ", robt.Arm.getCurrentPosition());
            telemetry.update();
        }


    }

}
