package com.romanvoloboev.util;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author romanvoloboev
 */
public class DatabaseStoragePropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return !Boolean.valueOf(conditionContext.getEnvironment().getProperty("use.collections.storage"));
    }
}
