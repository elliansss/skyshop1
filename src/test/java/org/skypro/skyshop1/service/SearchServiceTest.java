package org.skypro.skyshop1.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop1.model.search.SearchResult;
import org.skypro.skyshop1.model.search.Searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void testSearch_NoObjectsInStorageService() {
        when(storageService.getAllSearchable()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("test");

        assertEquals(0, results.size());
    }

    @Test
    public void testSearch_ObjectsExistButNoneMatch() {
        Searchable searchable1 = createMockSearchable("Product A", UUID.randomUUID());
        Searchable searchable2 = createMockSearchable("Product B", UUID.randomUUID());
        when(storageService.getAllSearchable()).thenReturn(List.of(searchable1, searchable2));

        Collection<SearchResult> results = searchService.search("nonexistent");

        assertEquals(0, results.size());
    }

    @Test
    public void testSearch_MatchingObjectExists() {

        UUID id = UUID.randomUUID();
        Searchable searchable1 = createMockSearchable("Test Product", id);
        Searchable searchable2 = createMockSearchable("Another Product", UUID.randomUUID());
        when(storageService.getAllSearchable()).thenReturn(List.of(searchable1, searchable2));

        Collection<SearchResult> results = searchService.search("test");

        assertEquals(1, results.size());
        SearchResult result = results.iterator().next();
        assertEquals("Test Product", result.getName());
        assertEquals(id.toString(), result.getId());
    }

    @Test
    public void testSearch_CaseInsensitiveSearch() {

        UUID id = UUID.randomUUID();
        Searchable searchable = createMockSearchable("Test Product", id);
        when(storageService.getAllSearchable()).thenReturn(Collections.singletonList(searchable));


        Collection<SearchResult> results = searchService.search("tEsT");

        assertEquals(1, results.size());
    }

    private Searchable createMockSearchable(String name, UUID id) {
        return new Searchable() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getSearchTerm() {
                return name;
            }

            @Override
            public UUID getId() {
                return id;
            }

            @Override
            public String getContentType() {
                return "TEST";
            }
        };
    }
}