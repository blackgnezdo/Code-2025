package com.team6962.lib.utils;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Unit;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;

public final class MeasureMath {
    private MeasureMath() {
    }

    /**
     * Equivalent to Math.min of two measures.
     * @param <U> The unit type (inferred from the first measure)
     * @param <M> The measure type (inferred from the first measure)
     * @param a The first measure
     * @param b The second measure
     * @return The smaller of the two measures, of type M
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit, M extends Measure<U>> M min(M a, Measure<U> b) {
        return (M) a.unit().of(Math.min(a.in(a.unit()), b.in(a.unit())));
    }

    /**
     * Equivalent to Math.max of two measures.
     * @param <U> The unit type (inferred from the first measure)
     * @param <M> The measure type (inferred from the first measure)
     * @param a The first measure
     * @param b The second measure
     * @return The larger of the two measures, of type M
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit, M extends Measure<U>> M max(M a, Measure<U> b) {
        return (M) a.unit().of(Math.max(a.in(a.unit()), b.in(a.unit())));
    }

    /**
     * Clamps a measure between two other measures, equivalent to
     * MathUtil.clamp(value, min, max) or Math.min(Math.max(value, min), max).
     * @param <U> The unit type (inferred from value)
     * @param <M> The measure type (inferred from value)
     * @param value The value to clamp
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value, of type M
     */
    @SuppressWarnings("unchecked")
    public static <U extends Unit, M extends Measure<U>> M clamp(M value, Measure<U> min, Measure<U> max) {
        return (M) value.unit().of(Math.min(Math.max(value.in(value.unit()), min.in(value.unit())), max.in(value.unit())));
    }

    public static Distance arcLength(Distance radius, Angle angle) {
        return Meters.of(radius.in(Meters) * angle.in(Radians));
    }

    public static LinearVelocity angularToLinearVelocity(Distance radius, AngularVelocity angularVelocity) {
        return MetersPerSecond.of(radius.in(Meters) * angularVelocity.in(RadiansPerSecond));
    }

    public static AngularVelocity linearToAngularVelocity(Distance radius, LinearVelocity linearVelocity) {
        return RadiansPerSecond.of(linearVelocity.in(MetersPerSecond) / radius.in(Meters));
    }
}
