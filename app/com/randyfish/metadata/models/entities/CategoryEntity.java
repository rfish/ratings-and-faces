package com.randyfish.metadata.models.entities;

import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 * DB entity for a category
 */
public class CategoryEntity {

    @Id
    public ObjectId id;

    public ObjectId parentId;

    public Long categoryId;

    public String name;

}
