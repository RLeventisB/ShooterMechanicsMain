package me.deecaad.core.utils;

import me.deecaad.core.utils.RandomNumber;

public final class RandomNumberParseResult {
    public final boolean success;
    public final RandomNumber number;

    public static RandomNumberParseResult FAIL() {
        return new RandomNumberParseResult(false, new RandomNumber(0, 0));
    }

    public RandomNumberParseResult(boolean success, RandomNumber number) {
        this.success = success;
        this.number = number;
    }
}
