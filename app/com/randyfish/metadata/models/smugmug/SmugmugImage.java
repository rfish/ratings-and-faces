package com.randyfish.metadata.models.smugmug;

import org.codehaus.jackson.JsonNode;

/**
 * Data holder for an image
 */
public class SmugmugImage {

    public long id;
    public String key;


    public static SmugmugImage parseStubNode(JsonNode imageNode) {
        SmugmugImage image = new SmugmugImage();
        JsonNode idNode = imageNode.get("id");
        if (idNode != null) {
            image.id = idNode.getLongValue();
        }
        JsonNode keyNode = imageNode.get("Key");
        if (keyNode != null) {
            image.key = keyNode.asText();
        }

        if (image.id >= 0 && image.key != null) {
            return image;
        } else {
            return null;
        }
    }
}
