package com.randyfish.metadata.models;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.randyfish.metadata.models.entities.AlbumEntity;
import com.randyfish.metadata.models.entities.ImageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Configuration;

import java.net.UnknownHostException;

/**
 * The main mongo database class
 */
public class DB {
    private static Logger logger = LoggerFactory.getLogger(DB.class);

    public static Datastore ds;
    static {
        try {
            Configuration config = Configuration.root();
            Morphia morphia = new Morphia();
            String dbHost = config.getString("metadata.db.host");
            logger.info("db.host: " + dbHost);
            if (dbHost == null || dbHost.length() == 0) {
                logger.error("DB HOST IS NOT CONFIGURED!!!!!");
            }
            Mongo mongo = new Mongo(
                    dbHost,
                    config.getInt("metadata.db.port")
            );
            if (config.getString("metadata.db.user") != null) {
                DB.ds = morphia.createDatastore(mongo,
                        config.getString("metadata.db.name"),
                        config.getString("metadata.db.user"),
                        config.getString("metadata.db.password").toCharArray());
            } else {
                DB.ds = morphia.createDatastore(mongo, config.getString("metadata.db.name"));
            }

            // these have indexes, so we want to ensure they are created
            morphia.map(AlbumEntity.class);
            morphia.map(ImageEntity.class);

        } catch (UnknownHostException e) {
            // ignored
        }

    }

    /**
     * Initialize the DB.
     */
    public static void init() {
        DB.ds.ensureIndexes();
    }
}
