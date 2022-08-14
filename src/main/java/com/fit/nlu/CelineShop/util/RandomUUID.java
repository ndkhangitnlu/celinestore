package com.fit.nlu.CelineShop.util;

import java.util.UUID;

public class RandomUUID {

    public static String getRandomID() {
        UUID uuid = UUID.randomUUID();
        String randomUUID = uuid.toString();
        return randomUUID.replace("-", "");
    }
}

