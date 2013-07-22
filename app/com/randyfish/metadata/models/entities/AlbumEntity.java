package com.randyfish.metadata.models.entities;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 * An album in the database
 */
@Entity(value = "albums", noClassnameStored = true)
public class AlbumEntity {

    @Id
    public ObjectId id;

    public long albumId;

    public String albumKey;

    public String title;

}
