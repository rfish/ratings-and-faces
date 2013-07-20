package controllers;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class Logging extends Action.Simple {
    private static Logger.ALogger apiLogger = Logger.of("randyfish.ratingsandfaces.api");
    private static Logger.ALogger htmlLogger = Logger.of("randyfish.ratingsandfaces.html");

    @Override
    public Result call(Http.Context ctx) throws Throwable {
        Logger.ALogger logger = null;
        if (ctx.request().accepts("text/html")) {
            logger = htmlLogger;
        } else if (ctx.request().accepts("application/json")) {
            logger = apiLogger;
        }
        if (logger != null) {
            logger.info(ctx.request().method() + " " + ctx.request().uri());
        }
        return delegate.call(ctx);
    }
}
