package com.randyfish.metadata.models.smugmug;

import org.codehaus.jackson.JsonNode;

/**
 * Data holder for an album
 */
public class Album {

    public long id = -1L;
    public String key;
    public String title;

    public static Album parseFromJsonNode(JsonNode albumNode) {
        if (albumNode != null) {
            Album album = new Album();
            JsonNode idNode = albumNode.get("id");
            if (idNode != null) {
                album.id = idNode.getLongValue();
            }

            JsonNode keyNode = albumNode.get("Key");
            if (keyNode != null) {
                album.key = keyNode.asText();
            }

            JsonNode titleNode = albumNode.get("Title");
            if (titleNode != null) {
                album.title = titleNode.asText();
            }

            return album;
        } else {
            return null;
        }
    }
}
