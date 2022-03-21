package com.evaans.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.evaans.core.models.ServiceDemo;
import com.evaans.core.services.DemoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = ServiceDemo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ServiceDemoImpl implements ServiceDemo {
    private static final Logger LOG= LoggerFactory.getLogger(ServiceDemoImpl.class);


    @OSGiService
    DemoService demoService;

    @Override
    public Iterator<Page> getPagesList()
    {
        return demoService.getPages();

    }

}
