package com.randyfish.metadata.models.smugmug;

import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Data holder for a category
 */
public class SmugmugCategory {

    public long id = -1;
    public String name = null;
    public List<SmugmugAlbum> albums = new ArrayList<SmugmugAlbum>();
    public List<SmugmugCategory> subcategories = new ArrayList<SmugmugCategory>();

    public static SmugmugCategory parseFromJsonNode(JsonNode categoryNode) {
        SmugmugCategory category = new SmugmugCategory();
        JsonNode categoryIdNode = categoryNode.get("id");
        if (categoryIdNode != null) {
            category.id = categoryIdNode.getLongValue();
        }

        JsonNode nameNode = categoryNode.get("Name");
        if (nameNode != null)  {
            category.name = nameNode.asText();
        }

        JsonNode albumsNode = categoryNode.get("Albums");
        if (albumsNode != null && albumsNode.isArray()) {
            for (int albumIndex = 0; albumIndex < albumsNode.size(); albumIndex++) {
                JsonNode albumNode = albumsNode.get(albumIndex);
                if (albumNode != null) {
                    SmugmugAlbum album = SmugmugAlbum.parseFromJsonNode(albumNode);
                    if (album != null) {
                        category.albums.add(album);
                    }
                }
            }
        }

        JsonNode subCategoriesNode = categoryNode.get("SubCategories");
        if (subCategoriesNode != null && subCategoriesNode.isArray()) {
            for (int subCategoryIndex = 0; subCategoryIndex < subCategoriesNode.size(); subCategoryIndex++) {
                JsonNode subCategoryNode = subCategoriesNode.get(subCategoryIndex);
                if (subCategoryNode != null) {
                    SmugmugCategory subCategory = parseFromJsonNode(subCategoryNode);
                    if (subCategory != null) {
                        category.subcategories.add(subCategory);
                    }
                }
            }
        }

        return category;

    }


}
