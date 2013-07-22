package com.randyfish.metadata.models.smugmug;

import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Data holder for an album
 */
public class SmugmugAlbum {

    public long id = -1L;
    public String key;
    public String title;

    public static SmugmugAlbum parseFromJsonNode(JsonNode albumNode) {
        if (albumNode != null) {
            SmugmugAlbum album = new SmugmugAlbum();
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

    public static List<SmugmugImage> parseImages(JsonNode getImagesNode) {
        List<SmugmugImage> images = new ArrayList<SmugmugImage>();
        JsonNode albumNode = getImagesNode.get("Album");
        if (albumNode != null) {
            JsonNode imagesNode = albumNode.get("Images");
            if (imagesNode != null && imagesNode.isArray()) {
                for (int imageIndex = 0; imageIndex < imagesNode.size(); imageIndex++) {
                    JsonNode imageNode = imagesNode.get(imageIndex);
                    if (imageNode != null) {
                        SmugmugImage image = SmugmugImage.parseStubNode(imageNode);
                        if (image != null) {
                            images.add(image);
                        }
                    }
                }
            }
        }
        return images;
    }
}
