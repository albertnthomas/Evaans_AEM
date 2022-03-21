package com.evaans.core.models.impl;

import javax.annotation.Resource;
import java.util.Collection;

public interface ImageList {

    Collection<ListItem> getListItems();

    interface ListItem {

        Resource getImage();

    }

}
