package com.evaans.core.models.impl;

import com.evaans.core.models.OsgiConfigDemo;
import com.evaans.core.services.OSGIConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = OsgiConfigDemo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OsgiConfigDemoImpl implements OsgiConfigDemo {

    @OSGiService
    OSGIConfig osgiConfig;

    @Override
    public String getServiceName(){
        return osgiConfig.getServiceName();
    }

    @Override
    public int getServiceCount(){
        return osgiConfig.getServiceCount();
    }

    @Override
    public boolean isLiveData(){
        return osgiConfig.isLiveData();
    }

    @Override
    public String[] getCountries(){
        return osgiConfig.getCountries();
    }
    @Override
    public String getRunModes(){
        return osgiConfig.getRunModes();
    }


}
