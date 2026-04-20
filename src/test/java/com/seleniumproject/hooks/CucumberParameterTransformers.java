package com.seleniumproject.hooks;

import com.seleniumproject.context.TestContext;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;

public class CucumberParameterTransformers {

    private final TestContext testContext;

    public CucumberParameterTransformers(TestContext testContext) {
        this.testContext = testContext;
    }

    @DefaultParameterTransformer
    public Object defaultParameterTransformer(Object fromValue, Type toValueType) {
        if (fromValue instanceof String
                && "java.util.Map<java.lang.String, java.lang.String>".equals(toValueType.getTypeName())) {
            return testContext.resolveMap((String) fromValue);
        }

        return fromValue;
    }
}