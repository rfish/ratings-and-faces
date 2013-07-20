package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

import java.util.Map;

/**
 * Base class for avatar services controllers
 */
@With(Logging.class)
public class BasicController extends Controller {
    /**
     * Check if this is an html request. Generally this should be checked first, since json
     * clients will be explicit with their request, whereas browsers will accept anything.
     */
    public static boolean isHTMLRequest() {
        return request().accepts("text/html");
    }

    /**
     * Check if this is a json request.
     */
    public static boolean isJSONRequest() {
        return request().accepts("application/json") || hasAcceptJsonRequestParameter();
    }

    private static boolean hasAcceptJsonRequestParameter()
    {
        Map<String,String[]> params = request().queryString();
        String[] acceptValues = params.get("Accept");
        if (acceptValues != null) {
            for (String param : acceptValues){
                if (param.equals("application/json")) {
                    return true;
                }
            }
        }
        return false;
    }

}
