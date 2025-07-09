package org.skypro.skyshop1.model.article;

import org.skypro.skyshop1.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String title;
    private final String content;
    private final UUID id;

    public Article(String title, String content, UUID id) {
        this.title = title;
        this.content = content;
        this.id = Objects.requireNonNull(id, "ID не может быть null");
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public String getSearchTerm() {
        return getTitle();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}