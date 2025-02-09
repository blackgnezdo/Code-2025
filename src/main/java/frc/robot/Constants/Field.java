// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.Constants;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Field {
  public static final double WIDTH = 8.21;
  public static final double LENGTH = 16.54;
  
  public static final Map<String, Supplier<Pose2d>> AUTO_MOVE_POSITIONS = Map.of(
    "AMP", pose2d(1.85, 7.5, 90.0),
    "SOURCE", pose2d(15.4, 1.0, -60.0),
    "SPEAKER", pose2d(1.5, 5.5, 180.0),
    "TRAP", pose2d(6, WIDTH / 2, 180.0)
  );

  public static final List<Supplier<Translation2d>> NOTE_POSITIONS = List.of(
    point2d(Units.inchesToMeters(114), WIDTH / 2.0 + Units.inchesToMeters(57) * 2.0),
    point2d(Units.inchesToMeters(114), WIDTH / 2.0 + Units.inchesToMeters(57) * 1.0),
    point2d(Units.inchesToMeters(114), WIDTH / 2.0 + Units.inchesToMeters(57) * 0.0),
    point2d(LENGTH / 2.0, WIDTH / 2.0 + Units.inchesToMeters(66) * 2.0),
    point2d(LENGTH / 2.0, WIDTH / 2.0 + Units.inchesToMeters(66) * 1.0),
    point2d(LENGTH / 2.0, WIDTH / 2.0 + Units.inchesToMeters(66) * 0.0),
    point2d(LENGTH / 2.0, WIDTH / 2.0 + Units.inchesToMeters(66) * -1.0),
    point2d(LENGTH / 2.0, WIDTH / 2.0 + Units.inchesToMeters(66) * -2.0)
  );
  
  public static final Supplier<Translation3d> SPEAKER = point3d(0.23, WIDTH / 2.0 + Units.inchesToMeters(57) * 1.0, 2.055);

  public static final double SPEAKER_WIDTH = 1.0;
  public static final double SPEAKER_HEIGHT = 0.45;
  public static final double SPEAKER_ANGLE = Units.degreesToRadians(14.0);
  public static final double NOTE_THICKNESS = Units.inchesToMeters(1.0);
  public static final double NOTE_LENGTH    = Units.inchesToMeters(14.0);

  public static final List<Supplier<Translation2d>> SHOT_POSITIONS = List.of(
    // point2d(4.5, 6.50),
    // point2d(BLUE_WING_X, 1.75),
    // point2d(4.3, 5.3)
    // point2d(1.5, 5.5),
    // point2d(1.5, 3.5),
    // point2d(1.5, 7.0)
    point2d(2.0, 3.5),
    () -> new Translation2d(flipIfRed(1.5).get(), Field.SPEAKER.get().getY())
  );

  public static Supplier<Pose2d> pose2d(double x, double y, double degrees) {
    return () -> flipIfRed(new Pose2d(x, y, Rotation2d.fromDegrees(degrees))).get();
  }

  public static Supplier<Translation2d> point2d(double x, double y) {
    return () -> flipIfRed(new Translation2d(x, y)).get();
  }
  
  public static Supplier<Translation3d> point3d(double x, double y, double z) {
    return () -> flipIfRed(new Translation3d(x, y, z)).get();
  }

  public static Supplier<Translation2d> flipIfRed(Translation2d position) {
    return () -> new Translation2d(flipIfRed(position.getX()).get(), position.getY());
  }

  public static Supplier<Translation3d> flipIfRed(Translation3d position) {
    return () -> new Translation3d(flipIfRed(position.getX()).get(), position.getY(), position.getZ());
  }

  public static Supplier<Pose2d> flipIfRed(Pose2d pose) {
    return () -> new Pose2d(flipIfRed(pose.getTranslation()).get(), Constants.IS_BLUE_TEAM.get() ? pose.getRotation() : Rotation2d.fromDegrees(-(pose.getRotation().getDegrees() + 90) - 90));
  }

  public static Supplier<Double> flipIfRed(double x) {
    return () -> Constants.IS_BLUE_TEAM.get() ? x : LENGTH - x;
  }
}
