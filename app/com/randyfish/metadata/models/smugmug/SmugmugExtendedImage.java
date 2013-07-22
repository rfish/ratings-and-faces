package com.randyfish.metadata.models.smugmug;

import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Contains much more metadata about an image
 */
public class SmugmugExtendedImage {

    /**
     * This is the id in the smugmug system
     */
    public long imageId;

    /**
     * This is the key in the smugmug system
     */
    public String imageKey;

    public String caption;

    public String filename;

    public String format;       // JPG or PNG, etc

    public int height;
    public int width;

    public List<String> keywords = new ArrayList<String>();

    public Date lastUpdated;

    public Date lastChecked;

    public long fileSize;

    public List<UrlHolder> urls = new ArrayList<UrlHolder>();

    public String type;

    public static class UrlHolder {
        public String urlType; // type of url
        public String url; // url on smugmug
    }

    public static SmugmugExtendedImage parseFromJson(JsonNode resultNode) {
        SmugmugExtendedImage extendedImage = null;
        JsonNode imageNode = resultNode.get("Image");
        if (imageNode != null) {
            extendedImage = new SmugmugExtendedImage();

            JsonNode captionNode = imageNode.get("Caption");
            if (captionNode != null) {
                extendedImage.caption = captionNode.asText();
            }

            JsonNode fileNameNode = imageNode.get("FileName");
            if (fileNameNode != null) {
                extendedImage.filename = fileNameNode.asText();
            }

            JsonNode formatNode = imageNode.get("Format");
            if (formatNode != null) {
                extendedImage.format = formatNode.asText();
            }

            JsonNode heightNode = imageNode.get("Height");
            if (heightNode != null) {
                extendedImage.height = heightNode.getIntValue();
            }

            JsonNode widthNode = imageNode.get("Width");
            if (widthNode != null) {
                extendedImage.width = widthNode.getIntValue();
            }

            JsonNode keywordsNode = imageNode.get("Keywords");
            if (keywordsNode != null) {
                String keywordsStr = keywordsNode.asText();
                String[] keywords = keywordsStr.split(",");
                if (keywords != null) {
                    for (String keyword : keywords) {
                        extendedImage.keywords.add(keyword);
                    }
                }
            }

           extendedImage.urls = extractUrlsFromNode(imageNode,
                   new String[] { "LargeURL", "LightboxURL", "MediumURL", "SmallURL", "ThumbURL",
                           "TinyURL", "URL", "X2LargeURL", "X3LargeURL", "XLargeURL"  });

            JsonNode sizeNode = imageNode.get("Size");
            if (sizeNode != null) {
                extendedImage.fileSize = sizeNode.getLongValue();
            }

            JsonNode typeNode = imageNode.get("Type");
            if (typeNode != null) {
                extendedImage.type = sizeNode.asText();
            }

        }

        return extendedImage;

    }

    public static List<UrlHolder> extractUrlsFromNode(JsonNode imageNode, String[] urlTypes) {
        List<UrlHolder> urls = new ArrayList<UrlHolder>();
        if (urlTypes != null) {
            for (String urlType : urlTypes) {
                UrlHolder holder = extractUrlFromNode(imageNode, urlType);
                if (holder != null) {
                    urls.add(holder);
                }
            }
        }
        return urls;
    }

    public static UrlHolder extractUrlFromNode(JsonNode imageNode, String urlType) {
        JsonNode urlNode = imageNode.get(urlType);
        if (urlNode != null) {
            String url = urlNode.asText();
            UrlHolder urlHolder = new UrlHolder();
            urlHolder.urlType = "LargeURL";
            urlHolder.url = url;
            return urlHolder;
        }
        return null;

    }
}
