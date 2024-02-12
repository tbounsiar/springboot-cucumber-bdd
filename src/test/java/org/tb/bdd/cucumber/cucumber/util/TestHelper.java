package org.tb.bdd.cucumber.cucumber.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestHelper {

    private static final Logger LOG = LoggerFactory.getLogger(TestHelper.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String readFile(String path) {
        try {
            return StreamUtils.copyToString(new ClassPathResource(path).getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Assert.fail("Cannot read file " + path + " as UTF-8");
        }
        return null;
    }

    public static <T> List<T> readFile(String path, Class<T> classType) {
        return listFromJson(readFile(path), classType);
    }

    public static <T> List<T> listFromJson(String json, Class<T> classType) {
        List<T> list = null;
        try {
            list = objectMapper.readValue(
                    json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, classType)
            );
        } catch (IOException e) {
            Assert.fail("Cannot parse the json file " + json);
        }
        return list;
    }

    public static <T> T fromJson(String json, Class<T> classType) {
        T t = null;
        try {
            t = objectMapper.readValue(
                    json,
                    classType
            );
        } catch (IOException e) {
            Assert.fail("Cannot parse the json file " + json);
        }
        return t;
    }
}
