package com.randyfish.metadata.models;

import com.randyfish.metadata.models.entities.ImageEntity;

import java.util.Date;
import java.util.List;

/**
 * A friendly wrapper for the db object
 */
public class Image {

    private String id;
    private ImageEntity entity;

    public Image(String id) {
        this.id = id;
    }

    public Image(ImageEntity entity) {
        this.entity = entity;
        this.id = entity.id.toString();
    }

    public long getImageId() {
        return entity.imageId;
    }

    public String getImageKey() {
        return entity.imageKey;
    }

    public String getCaption() {
        return entity.caption;
    }

    public String getFilename() {
        return entity.filename;
    }

    public String getFormat() {
        return entity.format;
    }

    public int getHeight() {
        return entity.height;
    }
    public int getWidth() {
        return entity.width;
    }

    public List<String> getKywords() {
        return entity.keywords;
    }

    public Date getLastUpdated() {
        return entity.lastUpdated;
    }

    public Date getLastChecked() {
        return entity.lastChecked;
    }

    public long getFileSize() {
        return entity.fileSize;
    }

    public List<ImageEntity.UrlEntity> getUrls() {
        return entity.urls;
    }

    public String getType() {
        return entity.type;
    }


}
