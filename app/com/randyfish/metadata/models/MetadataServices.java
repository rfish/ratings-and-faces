package com.randyfish.metadata.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main metadata service conduit
 */
public class MetadataServices {

    private Logger logger = LoggerFactory.getLogger(MetadataServices.class);
    public static MetadataServices instance = new MetadataServices();

    /**
     * Categories API
     */
    public Categories categories = new Categories();

    /**
     * Albums API
     */
    public Albums albums = new Albums();

    /**
     * Images API
     */
    public Images images = new Images();
}
