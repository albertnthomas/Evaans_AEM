package com.evaans.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
        name = "Evaan Scheduler",
        description = "This is a simple Evaan Scheduler"
)

public @interface SchedulerConfiguration {

    @AttributeDefinition(
            name = "Evaan Scheduler",
            description = "This is a simple Evaan Scheduler",
            type = AttributeType.STRING)
    public String schedulerName() default "Custom Sling Scheduler Configuration";

    @AttributeDefinition(
            name = "Cron Expression",
            description = "Field to enter Cron expression",
            type = AttributeType.STRING)
    public String cronExpression() default "0/20 * * * * ?";

}
