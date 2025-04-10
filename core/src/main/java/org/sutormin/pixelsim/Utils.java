package org.sutormin.pixelsim;

public class Utils {
    /**
     * Closens a number to zero by a given amount.
     *
     * @param n the number to be closened to zero
     * @param a how much to closen n to zero
     * @return n closened to zero by a
     * @see Math
     */
    public static double closenToZero(double n, double a) {
        if (Math.abs(n) < a) {
            return 0;
        } else if (n > 0) {
            return n - a;
        } else if (n < 0) {
            return n + a;
        } else {
            return 0;
        }
    }

    /**
     * Rounds a number to the nearest given number.
     *
     * @param n the number to be rounded
     * @param c what to round n to
     * @return n rounded to nearest c
     * @see Math
     */
    public static double roundToNearest(double n, double c) {
        return Math.round(n / c) * c;
    }

    /**
     * Clamps N between A and B.
     * If N is between A and B the it return N.
     * If N is below A then it returns A.
     * If N is above B then it returns B.
     *
     * @param n the number to be clamped between a and b
     * @param a the minimum amount to clamp to
     * @param b the maximum amount to clamp to
     * @return n clamped between a and b
     * @see Math
     */
    public static double clamp(double a, double b, double n) {
        return Math.max(a, Math.min(b, n));
    }

    public static boolean isColliding(GameObject gameObject1, GameObject gameObject2) {
        return gameObject2.pos[0] + gameObject2.width > gameObject1.pos[0] &&
            gameObject2.pos[1] + gameObject2.height > gameObject1.pos[1] &&
            gameObject1.pos[0] + gameObject1.width > gameObject2.pos[0] &&
            gameObject1.pos[1] + gameObject1.height > gameObject2.pos[1];
    }
}
