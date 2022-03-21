package com.evaans.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.annotation.PostConstruct;


@Model(adaptables = SlingHttpServletRequest.class)

public class NewsFeed {
    private static final String PN_FULL_WIDTH = "useFullWidth";

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private ValueMap properties;

    private String classList;
    private HeroImage.Image image;

    @PostConstruct
    private void initModel() {
        classList = getClassList();
        image = getImage();
    }

    public String getClassList() {
        if (classList != null) {
            return classList;
        }
        classList = "we-HeroImage";
        if ("true".equals(properties.get(PN_FULL_WIDTH, ""))) {
            classList += " width-full";
        }
        return classList;
    }
    public HeroImage.Image getImage() {
        if (image != null) {
            return image;
        }
        com.adobe.cq.wcm.core.components.models.Image image = request.adaptTo(com.adobe.cq.wcm.core.components.models.Image.class);
        if(image != null) {
            this.image = new HeroImage.Image(image.getSrc());
        }
        return this.image;
    }

}
