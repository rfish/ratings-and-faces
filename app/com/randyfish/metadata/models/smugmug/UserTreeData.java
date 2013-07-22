package com.randyfish.metadata.models.smugmug;

import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UserTreeData {

    public List<SmugmugCategory> categories = new ArrayList<SmugmugCategory>();

    public UserTreeData() {

    }

    public static UserTreeData parseFromJson(JsonNode json) {

        UserTreeData userTree = new UserTreeData();

        JsonNode categoriesNode = json.get("Categories");
        if (categoriesNode != null) {
            // categoriesNode should be an array
            if (categoriesNode.isArray()) {
                for (int categoryIndex = 0; categoryIndex < categoriesNode.size(); categoryIndex++) {
                    JsonNode categoryNode = categoriesNode.get(categoryIndex);
                    if (categoryNode != null) {
                        SmugmugCategory category = SmugmugCategory.parseFromJsonNode(categoryNode);
                        if (category != null) {
                            userTree.categories.add(category);
                        }
                    }
                }
            }
        }

        return userTree;

    }

}
