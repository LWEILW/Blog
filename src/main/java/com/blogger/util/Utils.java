package com.blogger.util;

import java.util.UUID;

public class Utils {

    // 自动生成UUID
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
