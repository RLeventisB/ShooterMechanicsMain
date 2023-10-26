package me.deecaad.core.utils;

import me.deecaad.core.utils.IntegerRandomNumber;

public final class IntegerRandomNumberParseResult {
    public final boolean success;
    public final IntegerRandomNumber number;

    public static IntegerRandomNumberParseResult FAIL() {
        return new IntegerRandomNumberParseResult(false, new IntegerRandomNumber(0, 0));
    }

    public IntegerRandomNumberParseResult(boolean success, IntegerRandomNumber number) {
        this.success = success;
        this.number = number;
    }
}
