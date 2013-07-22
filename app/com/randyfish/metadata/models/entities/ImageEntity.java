package com.randyfish.metadata.models.entities;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.PrePersist;
import org.bson.types.ObjectId;

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
    public String imageId;

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

    @Embedded
    public static class UrlEntity {
        public String urlType; // type of url
        public String url; // url on smugmug
    }


}
