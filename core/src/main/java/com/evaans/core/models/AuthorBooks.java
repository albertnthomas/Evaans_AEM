package com.evaans.core.models;

import com.evaans.core.helper.MultifieldHelper;

import java.util.List;
import java.util.Map;

public interface AuthorBooks {

    String getAuthorName();
    List<String> getAuthorBooks();
    List<Map<String, String>> getBookDetailsWithMap();
    List<MultifieldHelper> getBookDetailsWithBean();
    List<MultifieldHelper> getBookDetailsWithNastedMultifield();
}
