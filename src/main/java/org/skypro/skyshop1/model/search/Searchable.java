package org.skypro.skyshop1.model.search;

import java.util.UUID;

public interface Searchable {

    String getName();

    String getSearchTerm();

    UUID getId();

    String getContentType();

}