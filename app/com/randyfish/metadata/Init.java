package com.randyfish.metadata;


import com.randyfish.metadata.models.DB;
import play.Application;
import play.GlobalSettings;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Global initialization
 */
public class Init extends GlobalSettings {
    @Override
    public void onStart(Application application) {
        super.onStart(application);
        DB.init();
    }

    @Override
    public Result onError(Http.RequestHeader request, Throwable t) {
        Throwable src = getRootCause(t);
        if (src instanceof IllegalAccessError) {
            return Controller.unauthorized();
        } else {
            return Controller.internalServerError();
        }
    }

    private Throwable getRootCause(Throwable t) {
        while (t.getCause() != null && t.getCause() != t) {
            t = t.getCause();
        }
        return t;
    }
}
