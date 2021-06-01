package vn.compedia.website.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.compedia.website.util.FacesUtil;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;

public abstract class BaseController implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract String getMenuId();

    @Inject
    @Named
    LanguageController languageController;

    @Inject
    AuthorizationController authorizationController;



    public String getTextMessage(String key) {
        return languageController.getTextMap().get(key);
    }

    public String getErrorMessage(String key) {
        return languageController.getErrorMap().get(key);
    }

}
