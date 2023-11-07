package com.bhn.marketplace.Framework.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class RandomValueGenerator {

    public static String generateRequestID() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssS").format(new Date());
    }

    public static String generateLoadValueInRange(double min, double max) {
        return String.format("%.2f", ThreadLocalRandom.current().nextDouble(min, max));
    }
}
