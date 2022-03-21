package com.evaans.core.services.impl;

import com.evaans.core.services.OSGIConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.*;

@Component(service = OSGIConfig.class, immediate = true)
@Designate(ocd=OSGIConfigImpl.ServiceConfig.class)

public class OSGIConfigImpl implements OSGIConfig {


    @ObjectClassDefinition(name = "Albert Configuration",
            description ="Sample Configuration")
    public @interface ServiceConfig{
        @AttributeDefinition(
                name = "Service Name",
                description  = "Enter Service Name",
                type = AttributeType.STRING
        )
        public String serviceName() default "Albert Service";

        @AttributeDefinition(
                name = "Count",
                description = "Enter the count",
                type = AttributeType.INTEGER
        )
        int serviceCount() default 1;

        @AttributeDefinition(
                name = "Live",
                description = "Please ticket for Live data",
                type = AttributeType.BOOLEAN
        )
        boolean isLiveData() default false;

        @AttributeDefinition(
                name = "Countries",
                description = "Enter countries Details",
                type = AttributeType.STRING
        )
        String[] getCountries() default {"de","in"};

        @AttributeDefinition(
                name = "RunModes",
                description = "Choose Runmodes",
                options = {
                        @Option(label = "Prod", value = "prod"),
                        @Option(label = "Dev" , value = "dev"),
                        @Option(label = "Stage", value = "stage")
                },
                type = AttributeType.STRING)
        String getRunModes() default "stage";
    }

    private String serviceName;
    private int serviceCount;
    private boolean liveData;
    private String[] countries;
    private String runModes;

    @Activate

    protected void activate(ServiceConfig serviceConfig){
     serviceName = serviceConfig.serviceName();
     serviceCount = serviceConfig.serviceCount();
     liveData = serviceConfig.isLiveData();
     countries = serviceConfig.getCountries();
     runModes = serviceConfig.getRunModes();
    }




    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public boolean isLiveData() {
        return liveData;
    }

    @Override
    public String[] getCountries() {
        return countries;
    }

    @Override
    public String getRunModes() {
        return runModes;
    }



}
