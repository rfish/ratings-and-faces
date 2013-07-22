package com.randyfish.metadata.models;

import com.randyfish.metadata.models.entities.CategoryEntity;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Categories API functionality
 */
public class Categories {

    private static final ObjectMapper json = new ObjectMapper();


    public Category findCategory(long categoryId, String name) {
        CategoryEntity entity = DB.ds.find(CategoryEntity.class).filter("categoryId", categoryId).filter("name", name).get();
        if (entity != null) {
            return new Category(entity);
        }
        return null;

    }

    public Category create(long categoryId, String name) {
        Category category = findCategory(categoryId, name);
        if (category == null)  {
            CategoryEntity entity = new CategoryEntity();
            entity.categoryId = categoryId;
            entity.name = name;
            DB.ds.save(entity);
            category = new Category(entity);
        }
        return category;
    }

    public Category createSubcategory(ObjectId parent, long categoryId, String name) {
        CategoryEntity entity = DB.ds.find(CategoryEntity.class).filter("parentId", parent)
                .filter("categoryId", categoryId).filter("name", name).get();
        if (entity == null) {
            entity = new CategoryEntity();
            entity.categoryId = categoryId;
            entity.name = name;
            DB.ds.save(entity);
        }
        return new Category(entity);
    }

}
