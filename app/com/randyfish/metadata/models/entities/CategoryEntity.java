package com.randyfish.metadata.models.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 * DB entity for a category
 */
@Entity(value = "categories", noClassnameStored = true)
public class CategoryEntity {

    @Id
    public ObjectId id;

    public ObjectId parentId;

    public Long categoryId;

    public String name;

}
