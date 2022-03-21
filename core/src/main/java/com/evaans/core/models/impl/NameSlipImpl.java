package com.evaans.core.models.impl;

import com.evaans.core.models.NameSlip;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import javax.inject.Inject;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = NameSlip.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class NameSlipImpl implements NameSlip{

    @ValueMapValue
    @Default(values = "Albert")
    String fname;

    @ValueMapValue
    @Default(values = "Thomas")
    String lname;
    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }
}
