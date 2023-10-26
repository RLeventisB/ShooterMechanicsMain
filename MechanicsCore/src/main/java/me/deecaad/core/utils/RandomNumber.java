package me.deecaad.core.utils;

import me.deecaad.core.MechanicsCore;
import me.deecaad.core.utils.IRandomNumber;
import me.deecaad.core.utils.NumberUtil;
import me.deecaad.core.utils.RandomNumberParseResult;

public final class RandomNumber
        extends IRandomNumber {
    public double min;
    public double max;

    public static RandomNumberParseResult parseString(Object value) {
        if (value == null) {
            return RandomNumberParseResult.FAIL();
        }
        String text = value.toString();
        int substringIndex = -1;
        if (text.startsWith("rnd(")) {
            substringIndex = 4;
        } else if (text.startsWith("rng(")) {
            substringIndex = 4;
        } else if (text.startsWith("random(")) {
            substringIndex = 7;
        }
        if (substringIndex == -1) {
            return RandomNumberParseResult.FAIL();
        }
        String trimmedText = text.substring(substringIndex, text.length() - 1);
        String[] values = trimmedText.split(",", 2);
        try {
            double min = Double.parseDouble(values[0].trim());
            double max = Double.parseDouble(values[1].trim());
            return new RandomNumberParseResult(true, new RandomNumber(min, max));
        }
        catch (Exception e) {
            MechanicsCore.debug.info(e.toString());
            return RandomNumberParseResult.FAIL();
        }
    }

    public RandomNumber(Number minNumber, Number maxNumber) {
        this.min = minNumber.doubleValue();
        this.max = maxNumber.doubleValue();
    }

    @Override
    public int intValue() {
        return (int)this.randomFunction();
    }

    @Override
    public long longValue() {
        return (long)this.randomFunction();
    }

    @Override
    public double doubleValue() {
        return this.randomFunction();
    }

    @Override
    public float floatValue() {
        return (float)this.randomFunction();
    }

    public double randomFunction() {
        return NumberUtil.random(this.min, this.max);
    }
}
