package com.evaans.core.models.impl;

import com.evaans.core.models.Alert;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = Alert.class,
        resourceType = AlertImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class AlertImpl implements Alert {
    private static final Logger LOG = LoggerFactory.getLogger(AlertImpl.class);
    static final String RESOURCE_TYPE ="evaans/components/alert";


    @ValueMapValue
    @Default(values = "This is an Alert message")
    String alertmsg;

    @ValueMapValue
    @Default(values = "01-Jan-2021")
    String time;

    @ValueMapValue

    boolean location;

    @Override
    public String getAlertMessage()
    {
        return alertmsg;
    }

    @Override
    public String getTime(){
        return time;
    }

    @Override
    public boolean getLocation()
    {
        return location;
    }

}
