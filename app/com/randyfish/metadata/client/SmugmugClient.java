package com.randyfish.metadata.client;

import com.randyfish.metadata.models.entities.AlbumEntity;
import com.randyfish.metadata.models.smugmug.UserTreeData;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonNode;
import play.Configuration;
import play.libs.Json;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This is a wrapper for the Smugmug client
 */
public class SmugmugClient {

    private static final String baseUrl = "http://api.smugmug.com/services/api/json/1.3.0/?";

    private String apiKey;
    private String nickName;

    public SmugmugClient() {
        Configuration config = Configuration.root();
        apiKey = config.getString("smugmug.key");
        nickName = config.getString("smugmug.nickname");

    }

    public UserTreeData getUserTree() {

        try {
            HttpClient httpClient = new DefaultHttpClient();

            StringBuilder builder = new StringBuilder();
            builder.append(baseUrl);

            //http://api.smugmug.com/services/api/json/1.3.0/?method=smugmug.users.getTree&APIKey=tc8KHTn4S93sUYozXwoba2J0cUggWbxx&NickName=randyfish
            builder.append("method=smugmug.users.getTree&");
            builder.append("APIKey="+apiKey+ "&");
            builder.append("NickName="+nickName+ "&");

            HttpGet get = new HttpGet(builder.toString());
            HttpResponse response = httpClient.execute(get);
            checkErrorResponse(response);

            String responseBody = EntityUtils.toString(response.getEntity());
            JsonNode resultNode = Json.parse(responseBody);
            UserTreeData userTreeData = UserTreeData.parseFromJson(resultNode);
            return userTreeData;
        } catch (IOException e) {
            throw new SmugmugException(e);
        }
    }

    private void checkErrorResponse(HttpResponse response) {
        if (response.getStatusLine().getStatusCode() >= 400) {
            try {
                EntityUtils.consume(response.getEntity());
            } catch (IOException e) {
                // ignored
            }
            throw new SmugmugException(response.getStatusLine().toString(),
                    response.getStatusLine().getStatusCode());
        }
    }

}
