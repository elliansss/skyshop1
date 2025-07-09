package org.skypro.skyshop1.service;

import org.skypro.skyshop1.model.search.SearchResult;
import org.skypro.skyshop1.model.search.Searchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final StorageService storageService;

    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String pattern) {
        return storageService.getAllSearchable().stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(pattern.toLowerCase()))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }

    public Collection<Searchable> getAllSearchableItems() {
        return storageService.getAllSearchable();
    }
}