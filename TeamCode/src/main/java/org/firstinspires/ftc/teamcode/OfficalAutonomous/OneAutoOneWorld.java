package org.firstinspires.ftc.teamcode.OfficalAutonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;



import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.OfficalTeam.OneConfigMultipleWorlds;


@Autonomous(name = "Competition Offical Auto", group = "RVSABR")
public class OneAutoOneWorld extends LinearOpMode {

    Pose2d pose = new Pose2d(8, 61.75, Math.toRadians(270));
    MecanumDrive drive = new MecanumDrive(hardwareMap, pose);


    @Override
    public void runOpMode(){
        Pose2d startingPose = new Pose2d(8, 61.75, Math.toRadians(270));


        //BlueBucket
        TrajectoryActionBuilder moveToSpecimen = drive.actionBuilder(startingPose)
                .strafeToConstantHeading(new Vector2d(8, 37.75))
                .waitSeconds(2.0);

        TrajectoryActionBuilder moveBack = drive.actionBuilder(startingPose)
                .strafeToConstantHeading(new Vector2d(8, 39.75))
                .waitSeconds(1.0);

        TrajectoryActionBuilder pushSamples = drive.actionBuilder(startingPose)
                .strafeToConstantHeading(new Vector2d(8, 39.75))
                .splineToConstantHeading(new Vector2d(34.5, 10.1), Math.toRadians(273))
                .strafeToConstantHeading(new Vector2d(46, 12.1))
                .waitSeconds(0.5)
                .strafeToConstantHeading(new Vector2d(46, 60.1))
                .waitSeconds(0.5)
                .strafeToConstantHeading(new Vector2d(46, 12.1))
                .strafeToConstantHeading(new Vector2d(53, 12.1))
                .strafeToConstantHeading(new Vector2d(53, 53.1))
                .waitSeconds(0.5)
                .strafeToConstantHeading(new Vector2d(53, 12.1))
                .strafeToConstantHeading(new Vector2d(61, 12.1))
                .strafeToConstantHeading(new Vector2d(61, 48.5));

        TrajectoryActionBuilder park = drive.actionBuilder(startingPose)
                .strafeToConstantHeading(new Vector2d(57, 48.5))
                .splineToLinearHeading(new Pose2d(28.2, 15, Math.toRadians(180)), Math.toRadians(225))
                .waitSeconds(1.0)
                .strafeToConstantHeading(new Vector2d(26.2, 15));


        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.addData("Position during Init", 0);
            telemetry.update();
        }
        telemetry.addData("Starting Position", startingPose);
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            if (isStopRequested()) return;

            Action bB1 = moveToSpecimen.build();
            Action bB2 = moveBack.build();
            Action bB3 = pushSamples.build();
            Action bB4 = park.build();


            Actions.runBlocking(
                    new SequentialAction(
                            bB1,
                            bB2,
                            bB3,
                            bB4
                    )
            );
        }

    }
}
