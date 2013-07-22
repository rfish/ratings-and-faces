package controllers;

import com.randyfish.metadata.client.SmugmugClient;
import com.randyfish.metadata.models.Album;
import com.randyfish.metadata.models.Category;
import com.randyfish.metadata.models.Image;
import com.randyfish.metadata.models.MetadataServices;
import com.randyfish.metadata.models.smugmug.*;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Result;

import java.util.List;

/**
 * Image source controller
 */
public class ImageSourceController extends BasicController {

    private static ObjectMapper mapper = new ObjectMapper();

    private static Logger logger = LoggerFactory.getLogger(ImageSourceController.class);


    /**
     * Trigger a refresh of the content from SmugMug
     * @return
     */
    public static Result refresh() {
        // trigger a refresh
        SmugmugClient client = new SmugmugClient();
        UserTreeData userTree = client.getUserTree();
        if (userTree != null) {
            List<SmugmugCategory> smugmugCategories = userTree.categories;
            for (SmugmugCategory smugmugCategory : smugmugCategories) {
                // check to see if it has albums or subcategories
                List<SmugmugAlbum> albums = smugmugCategory.albums;
                List<SmugmugCategory> subcategories = smugmugCategory.subcategories;
                if ((albums != null && albums.size() > 0) || (subcategories != null && subcategories.size() > 0)) {
                    // we have a category that matters
                    Category category = MetadataServices.instance.categories.findCategory(smugmugCategory.id, smugmugCategory.name);
                    if (category == null) {
                        category = MetadataServices.instance.categories.create(smugmugCategory.id, smugmugCategory.name);
                    }

                    if (subcategories != null && subcategories.size() > 0) {
                        for (SmugmugCategory smugmugSubCategory : subcategories) {
                            Category subcategory = MetadataServices.instance.categories.findCategory(smugmugSubCategory.id, smugmugSubCategory.name);
                            if (subcategory == null) {
                                subcategory = MetadataServices.instance.categories.createSubcategory(new ObjectId(category.getId()), smugmugSubCategory.id, smugmugSubCategory.name);
                            }
                        }
                    }

                    if (albums != null && albums.size() > 0) {
                        for (SmugmugAlbum smugmugAlbum : albums) {
                            Album album = MetadataServices.instance.albums.findAlbum(smugmugAlbum.id, smugmugAlbum.key);
                            if (album == null) {
                                album = MetadataServices.instance.albums.create(smugmugAlbum.id, smugmugAlbum.key, smugmugAlbum.title);
                            }

                            // need to get the list of all of the images from the smugmug client
                            List<SmugmugImage> images = client.getImagesForAlbum(smugmugAlbum);
                            if (images != null && images.size() > 0) {
                                for (SmugmugImage smugmugImage : images) {
                                    // need to fetch extended data for each image
                                    SmugmugExtendedImage imageExt = client.getExtendedImageData(smugmugImage);
                                    if (imageExt != null) {
                                        // see if we have an item in the db and whether it has changed
                                        Image image = MetadataServices.instance.images.findImage(imageExt.imageId, imageExt.imageKey);
                                        if (image == null) {
                                            image = MetadataServices.instance.images.create(imageExt);
                                        } else {
                                            // TODO - check to see if anything has changed
                                        }
                                    }
                                }
                            }
                        }
                    }

                } else {
                    logger.debug("Skipping category " + smugmugCategory.name);
                }

            }
        }
        response().setHeader("Access-Control-Allow-Origin", "*");
        return ok();
    }

}
