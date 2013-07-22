package com.randyfish.metadata.models;

import com.randyfish.metadata.models.entities.CategoryEntity;

/**
 * A friendly wrapper for the db object
 */
public class Category {

    private String id;
    private CategoryEntity entity;

    public Category(String id) {
        this.id = id;
    }

    public Category(CategoryEntity entity) {
        this.entity = entity;
        this.id = entity.id.toString();
    }

    public long getCategoryId() {
        return entity.categoryId;
    }

    public String getName() {
        return entity.name;
    }

}
