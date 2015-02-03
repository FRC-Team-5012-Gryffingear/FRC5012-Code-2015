package com.gryffingear.y2015.utilities;

public class GryffinMath {

	/**
	 * Compares two numbers to see if they are within an acceptable 
	 * tolerance of eachother.
	 * @param a
	 * @param b
	 * @param tolerance
	 * @return
	 */
	public static boolean equalsTolerance(double a, double b, double tolerance) {
		return Math.abs(a-b) < tolerance;
	}
	

    /**
     * Scales an input of a range between istart and istop to a range between
     * ostart and ostop
     *
     * @param value input value
     * @param istart input value's lower limit
     * @param istop input value's upper limit
     * @param ostart output value's lower limit
     * @param ostop output value's upper limit
     * @return the scaled value
     */
    public static float map(float value, float istart, float istop, float ostart, float ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    /**
     * Checks to see if input is between low and high
     *
     * @param input input value
     * @param low lower threshold
     * @param high upper threshold
     * @return true if low < input < high
     */
    public static boolean isInBand(double input, double low, double high) {
        return input > low && input < high;
    }

    /**
     * Creates a deadband.
     *
     * @param in
     * @param width
     * @return
     */
    public static double deadband(double in, double width) {
        if (Math.abs(in) < width) {
            in = 0;
        }
        return in;
    }
    
    /**
     * Truncates an input(value) to specified decimal places
     *
     * @param value
     * @param places
     * @return
     */
    public static double truncate(double value, double places) {
        double multiplier = Math.pow(10, places);
        return Math.floor(multiplier * value) / multiplier;
    }

    public static double fMod(double value, double x) {
        // Negate if and only if base is negative.
        // (Java's modulo isn't mathematically pretty in this way.)
        double sign = (value < 0) ? -1 : 1;
        return sign * (Math.abs(x) % Math.abs(value));
    }

    public static double cap(double in, double low, double high) {
        if (in < low) {
            in = low;
        }
        if (in > high) {
            in = high;
        }
        return in;
    }

    /**
     * Returns a curve similar to the square curve, but the negative range is
     * inverted K is a scaling constant. default to 1 if no scaling needed
     *
     * @param in
     * @param k
     * @return
     */
    public static double signedSquare(double in, double k) {
        return in * Math.abs(in) * k;
    }

}
