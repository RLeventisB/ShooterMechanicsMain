package me.deecaad.weaponmechanics.utils;

import me.deecaad.core.utils.IntegerRandomNumber;
import me.deecaad.core.utils.RandomNumber;

public abstract class NumberHelper {
    NumberHelper() {
    }

    public static Number divideBy(Number num, double value) {
        if (num instanceof IntegerRandomNumber) {
            IntegerRandomNumber cast = (IntegerRandomNumber)num;
            int min = cast.min;
            int max = cast.max;
            return new IntegerRandomNumber((double)min / value, (double)max / value);
        }
        if (num instanceof RandomNumber) {
            RandomNumber cast = (RandomNumber)num;
            double min = cast.min;
            double max = cast.max;
            return new RandomNumber(min / value, max / value);
        }
        return num.doubleValue() / value;
    }

    public static Number multBy(Number num, double value) {
        if (num instanceof IntegerRandomNumber) {
            IntegerRandomNumber cast = (IntegerRandomNumber)num;
            int min = cast.min;
            int max = cast.max;
            return new IntegerRandomNumber((double)min * value, (double)max * value);
        }
        if (num instanceof RandomNumber) {
            RandomNumber cast = (RandomNumber)num;
            double min = cast.min;
            double max = cast.max;
            return new RandomNumber(min * value, max * value);
        }
        return num.doubleValue() * value;
    }

    public static Number sumBy(Number num, double value) {
        if (num instanceof IntegerRandomNumber) {
            IntegerRandomNumber cast = (IntegerRandomNumber)num;
            int min = cast.min;
            int max = cast.max;
            return new IntegerRandomNumber((double)min + value, (double)max + value);
        }
        if (num instanceof RandomNumber) {
            RandomNumber cast = (RandomNumber)num;
            double min = cast.min;
            double max = cast.max;
            return new RandomNumber(min + value, max + value);
        }
        return num.doubleValue() + value;
    }

    public static Number substractBy(Number num, double value) {
        if (num instanceof IntegerRandomNumber) {
            IntegerRandomNumber cast = (IntegerRandomNumber)num;
            int min = cast.min;
            int max = cast.max;
            return new IntegerRandomNumber((double)min - value, (double)max - value);
        }
        if (num instanceof RandomNumber) {
            RandomNumber cast = (RandomNumber)num;
            double min = cast.min;
            double max = cast.max;
            return new RandomNumber(min - value, max - value);
        }
        return num.doubleValue() - value;
    }
}
