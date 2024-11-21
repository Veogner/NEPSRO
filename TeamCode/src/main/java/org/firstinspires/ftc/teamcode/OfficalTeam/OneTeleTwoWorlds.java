package org.firstinspires.ftc.teamcode.OfficalTeam;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;

@TeleOp(name = "N9362HD")
public class OneTeleTwoWorlds extends LinearOpMode {
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
         double drive = -gamepad1.left_stick_y;
         double strafe = gamepad1.left_stick_x;
         double turn = gamepad1.right_stick_x;

         double leftPower = drive + turn + strafe;
         double rightPower = drive - turn - strafe;

         robt.leftFront.setPower(leftPower/3);
         robt.leftBack.setPower(leftPower/3);
         robt.rightFront.setPower(rightPower/3);
         robt.rightBack.setPower(rightPower/3);

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
