package com.evaans.core.models.impl;

import com.day.cq.wcm.api.Page;
import com.evaans.core.models.Author;
import com.mongodb.QueryBuilder;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = Author.class,
resourceType = AuthorImpl.RESOURCE_TYPE,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson" ,extensions = "json")
public class AuthorImpl implements Author {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);
    static final String RESOURCE_TYPE ="evaans/components/author";

    @ScriptVariable
    Page currentPage;


    @RequestAttribute(name = "rAttribute")
    private String reqAttribute;

    @OSGiService
    QueryBuilder queryBuilder;

    @ResourcePath(path = "/content/evaans/us/en/authorinfo")@Via("resource")
    Resource resource;

    @Inject
    @Via("resource")
    @Named("jcr:createdBy")
    String createdBy;




    @Inject
    @Via("resource")
    @Default(values = "Sample")
    String fname;

    @ValueMapValue
    @Default(values = "Sling")
    String lname;

    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public String getPageTitle(){
        return currentPage.getTitle();
    }

    @Override
    public  String getRequestAttribute(){
        return reqAttribute;
    }

    @Override
    public String getCreatedBy(){
        return createdBy;
    }

    @Override
    public  String getAuthorPage(){
        return resource.getName();
    }

    @PostConstruct
    protected void init() {
        LOG.info("\n Inside INIT {} : {}", currentPage.getTitle(),resource.getPath());
    }
}
