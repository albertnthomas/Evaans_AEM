package com.evaans.core.models.impl;

import com.evaans.core.models.PostCard;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = PostCard.class,
        resourceType = PostCardImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json", selector = "evaan")
public class PostCardImpl implements PostCard {
    private static final Logger LOG = LoggerFactory.getLogger(PostCardImpl.class);
    static final String RESOURCE_TYPE ="evaans/components/postcard";
    @ValueMapValue()
    @Default(values = "Evaan")
    String heading;

    @ValueMapValue()
    @Default(values = "Baby")
    String title;

    @ValueMapValue()
    @Default(values = "Like")
    String buttonName;

    @Override
    public String getHeading(){
        return heading;
    }

    @Override
    public String getTitle(){
        return title;
    }
    @JsonProperty(value = "json_name")
    public String JsonName(){
        return "JSON";
    }

    @Override
    public String getButtonName(){
        return buttonName;
    }

    @PostConstruct
    protected void init(){
        LOG.info("\n========Printing Logs=========");
    }

}
