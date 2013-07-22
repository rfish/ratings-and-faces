package com.randyfish.metadata.models;

import com.randyfish.metadata.models.entities.AlbumEntity;
import com.randyfish.metadata.models.entities.CategoryEntity;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Albums API functionality
 */
public class Albums {

    private static final ObjectMapper json = new ObjectMapper();


    public Album findAlbum(long albumId, String albumKey) {
        AlbumEntity entity = DB.ds.find(AlbumEntity.class).filter("albumId", albumId).filter("albumKey", albumKey).get();
        if (entity != null) {
            return new Album(entity);
        }
        return null;

    }

    public Album create(long albumId, String albumKey, String title) {
        Album album = findAlbum(albumId, albumKey);
        if (album == null)  {
            AlbumEntity entity = new AlbumEntity();
            entity.albumId = albumId;
            entity.albumKey = albumKey;
            entity.title = title;
            DB.ds.save(entity);
            album = new Album(entity);
        }
        return album;
    }

}
