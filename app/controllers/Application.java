package controllers;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import play.Configuration;
import play.mvc.Result;
import play.mvc.With;

import views.html.index;

public class Application extends BasicController {

    private static ObjectMapper mapper = new ObjectMapper();

    @With(UserAgent.class)
    public static Result index() {
        return ok(index.render("testing"));
    }

    public static Result admin() {
        ObjectNode node = mapper.createObjectNode();
        node.put("adminAppId", Configuration.root().getString("facebook.admin.id"));
        return ok(node);
    }

    /**
     * Get JSON that includes general information about the application
     */
    public static Result appInfo() {
        ObjectNode node = mapper.createObjectNode();
        node.put("id", Configuration.root().getString("facebook.app.id"));

        ObjectNode parent = mapper.createObjectNode();
        parent.put("facebook", node);
        return ok(parent);
    }

}