package com.evaans.core.models;

import java.util.List;
import java.util.Map;

public interface FoodCart {
    String getRestaurantName();
    List<String> getFoodName();
    List<Map<String,String>> getFoodDetailsWithMap();
}
