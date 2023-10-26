package me.deecaad.core.utils;

import me.deecaad.core.MechanicsCore;
import me.deecaad.core.utils.IRandomNumber;
import me.deecaad.core.utils.IntegerRandomNumberParseResult;
import me.deecaad.core.utils.NumberUtil;

public final class IntegerRandomNumber
        extends IRandomNumber {
    public int min;
    public int max;

    public IntegerRandomNumber(Number minNumber, Number maxNumber) {
        this.min = minNumber.intValue();
        this.max = maxNumber.intValue();
    }

    public static IntegerRandomNumberParseResult parseString(Object value) {
        if (value == null) {
            return IntegerRandomNumberParseResult.FAIL();
        }
        String text = value.toString();
        int substringIndex = -1;
        if (text.startsWith("rndi(")) {
            substringIndex = 5;
        } else if (text.startsWith("rngi(")) {
            substringIndex = 5;
        } else if (text.startsWith("randomi(")) {
            substringIndex = 8;
        }
        if (substringIndex == -1) {
            return IntegerRandomNumberParseResult.FAIL();
        }
        String trimmedText = text.substring(substringIndex, text.length() - 1);
        String[] values = trimmedText.split(",", 2);
        try {
            int min = Integer.parseInt(values[0].trim());
            int max = Integer.parseInt(values[1].trim());
            return new IntegerRandomNumberParseResult(true, new IntegerRandomNumber(min, max));
        }
        catch (Exception e) {
            MechanicsCore.debug.info(e.toString());
            return IntegerRandomNumberParseResult.FAIL();
        }
    }

    @Override
    public int intValue() {
        return this.randomFunction();
    }

    @Override
    public long longValue() {
        return this.randomFunction();
    }

    @Override
    public double doubleValue() {
        return this.randomFunction();
    }

    @Override
    public float floatValue() {
        return this.randomFunction();
    }

    public int randomFunction() {
        return NumberUtil.random(this.min, this.max);
    }
}
