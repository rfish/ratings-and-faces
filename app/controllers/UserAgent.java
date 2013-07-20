package controllers;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * A Play Framework action that reads the User Agent and determines the device. The action
 * sets an argument in the context based on that type.
 */
public class UserAgent extends Action.Simple {
    @Override
    public Result call(Http.Context ctx) throws Throwable {
        String agent = ctx.request().getHeader("User-Agent");
        String device = "unknown";
        if (agent != null) {
            if (agent.contains("iPhone")) {
                device = "iphone";
            } else if (agent.contains("iPad")) {
                device = "ipad";
            }
        }
        ctx.args.put("UserDevice", device);
        return delegate.call(ctx);
    }
}
