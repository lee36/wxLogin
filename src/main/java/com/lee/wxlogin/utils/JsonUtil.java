package com.lee.wxlogin.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {
    public static Map StringToMap(String result) throws IOException {
        return new ObjectMapper().readValue(result,Map.class);
    }
}
