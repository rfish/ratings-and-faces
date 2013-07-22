package controllers;

import com.randyfish.metadata.client.SmugmugClient;
import com.randyfish.metadata.models.smugmug.Category;
import com.randyfish.metadata.models.smugmug.UserTreeData;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import play.mvc.Result;

import java.util.List;

/**
 * Image source controller
 */
public class ImageSourceController extends BasicController {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Trigger a refresh of the content from SmugMug
     * @return
     */
    public static Result refresh() {
        // trigger a refresh
        SmugmugClient client = new SmugmugClient();
        UserTreeData userTree = client.getUserTree();
        if (userTree != null) {
            List<Category> categories = userTree.categories;
            for (Category category : categories) {

            }
        }
        response().setHeader("Access-Control-Allow-Origin", "*");
        return ok();
    }

}
