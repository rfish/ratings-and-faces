package com.randyfish.metadata.models;

import com.randyfish.metadata.models.entities.AlbumEntity;

/**
 * A friendly wrapper for the db object
 */
public class Album {

    private String id;
    private AlbumEntity entity;

    public Album(String id) {
        this.id = id;
    }

    public Album(AlbumEntity entity) {
        this.entity = entity;
        this.id = entity.id.toString();
    }

    public String getId() {
        return id;
    }

    public long getAlbumId() {
        return entity.albumId;
    }

    public String getAlbumKey() {
        return entity.albumKey;
    }

    public String getTitle() {
        return entity.title;
    }


}
