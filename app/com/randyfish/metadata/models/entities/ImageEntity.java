package com.randyfish.metadata.models.entities;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PrePersist;
import com.randyfish.metadata.models.smugmug.SmugmugExtendedImage;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity(value = "images", noClassnameStored = true)
public class ImageEntity {

    @Id
    public ObjectId id;

    /**
     * This is the id in the smugmug system
     */
    public Long imageId;

    /**
     * This is the key in the smugmug system
     */
    public String imageKey;

    public String caption;

    public String filename;

    public String format;       // JPG or PNG, etc

    public int height;
    public int width;

    public List<String> keywords;

    public Date lastUpdated;

    public Date lastChecked;

    public long fileSize;

    public List<UrlEntity> urls;

    public String type;

    @PrePersist
    public void prePersist() {
        this.lastChecked = new Date();
    }

    public static ImageEntity createFromExtendedImage(SmugmugExtendedImage extendedImage) {
        ImageEntity entity = new ImageEntity();
        entity.imageId = extendedImage.imageId;
        entity.imageKey = extendedImage.imageKey;

        if (extendedImage.caption != null) {
            entity.caption = extendedImage.caption;
        }

        if (extendedImage.filename != null) {
            entity.filename = extendedImage.filename;
        }

        if (extendedImage.format != null) {
            entity.format = extendedImage.format;
        }

        entity.width = extendedImage.width;
        entity.height = extendedImage.height;
        if (extendedImage.keywords != null) {
            entity.keywords = extendedImage.keywords;
        }

        entity.fileSize = extendedImage.fileSize;

        if (extendedImage.urls != null) {
            entity.urls = new ArrayList<UrlEntity>();
            for (SmugmugExtendedImage.UrlHolder holder : extendedImage.urls) {
                UrlEntity url = new UrlEntity();
                url.urlType = holder.urlType;
                url.url = holder.url;
                entity.urls.add(url);
            }
        }

        if (extendedImage.type != null) {
            entity.type = extendedImage.type;
        }

        return entity;
    }

    @Embedded
    public static class UrlEntity {
        public String urlType; // type of url
        public String url; // url on smugmug
    }


}
