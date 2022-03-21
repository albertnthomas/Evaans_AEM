package com.evaans.core.models.impl;

import com.evaans.core.models.FoodCart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.inject.Inject;
import java.util.*;

import static com.adobe.xfa.XFA.LOG;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = FoodCart.class,
resourceType = FoodCartImpl.RESOURCE_TYPE,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name ="jackson", extensions = "json", selector ="albert")

public class FoodCartImpl implements FoodCart {
    static final String RESOURCE_TYPE = "evaans/components/foodcart";

    private static final Logger LOG = LoggerFactory.getLogger(FoodCartImpl.class);
    @Inject
    Resource componentResource;

    @ValueMapValue
    @Default(values = "ABC Restaurant")
    private String restaurantname;

    @ValueMapValue
    private List<String> food;


    @Override
    public String getRestaurantName() {
        return restaurantname;
    }
    @JsonIgnore
    @Override
    public List<String> getFoodName() {
        if (food!=null){
            return new ArrayList<String>(food);
        }
        else
        {
            return Collections.emptyList();
        }

    }
    @JsonProperty(value = "Hotel Owner")
    public String ownerName(){
        return "Albert";
    }
    @JsonProperty(value = "Food Menu")
    @Override
    public List<Map<String, String>> getFoodDetailsWithMap() {
        List<Map<String,String>> foodDetailsMap = new ArrayList<>();
        try {
            Resource foodDetail =componentResource.getChild("bookdetailswithmap");
            if(foodDetail!=null){
                for (Resource food : foodDetail.getChildren()){
                    Map<String,String> foodMap = new HashMap<>();
                    foodMap.put("foodname",food.getValueMap().get("foodname",String.class));
                    foodMap.put("foodtype",food.getValueMap().get("foodtype",String.class));
                    foodMap.put("foodcost",food.getValueMap().get("foodcost",String.class));
                    foodDetailsMap.add(foodMap);
                }
            }

        }catch (Exception e){
            LOG.info("\n Error while getting {}", e.getMessage());
        }
        LOG.info("\n SIZE {}", foodDetailsMap.size());
        return foodDetailsMap;
    }


}
