package com.evaans.core.models.impl;

import com.adobe.cq.wcm.core.components.models.Image;
import com.evaans.core.models.News;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.factory.ModelFactory;

import javax.annotation.PostConstruct;
import java.util.Date;

@Model(adaptables = {SlingHttpServletRequest.class},
       adapters = {News.class},
       resourceType = {NewsImpl.RESOURCE_TYPE},
       defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsImpl implements News {

    protected static final String RESOURCE_TYPE = "evaans/components/news";

    @Self
    private SlingHttpServletRequest request;

    @OSGiService
    private ModelFactory modelFactory;

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private Date date;

    private Image image;

    @PostConstruct
    private void init() {
        image = modelFactory.getModelFromWrappedRequest(request, request.getResource(), Image.class);
    }
    @Override
    public String getHeading(){
            return heading;
    }
    @Override
    public String getTitle(){
        return title;
    }
    @Override
    public Date getDate(){
        return date;
    }
}
