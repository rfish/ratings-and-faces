package com.randyfish.metadata.models;

import com.randyfish.metadata.models.entities.ImageEntity;
import com.randyfish.metadata.models.smugmug.SmugmugExtendedImage;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Date;
import java.util.List;

/**
 * Images API functionality
 */
public class Images {

    private static final ObjectMapper json = new ObjectMapper();


    public Image findImage(long imageId, String imageKey) {
        ImageEntity entity = DB.ds.find(ImageEntity.class).filter("imageId", imageId).filter("imageKey", imageKey).get();
        if (entity != null) {
            return new Image(entity);
        }
        return null;

    }

    public Image create(SmugmugExtendedImage extendedImage) {
        Image image = findImage(extendedImage.imageId, extendedImage.imageKey);
        if (image == null)  {
            ImageEntity entity = ImageEntity.createFromExtendedImage(extendedImage);
            DB.ds.save(entity);
            image = new Image(entity);
        }
        return image;
    }

}
