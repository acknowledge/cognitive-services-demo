package cognitiveservices.api;

import java.io.File;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Emotion {
	
	final static int REMOTE_IMAGE = 0;
	final static int LOCAL_IMAGE = 1;
	
	static public String runEmotion(String key, String image) {
		
		String JSONResponse = "...";
		
		int mode = Emotion.LOCAL_IMAGE;
		if (image.toLowerCase().contains("http")) {
			mode = Emotion.REMOTE_IMAGE;
		}
		
		HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");

            //builder.setParameter("faceRectangles", "{string}");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            
            if (mode == Emotion.REMOTE_IMAGE) {
            	request.setHeader("Content-Type", "application/json");
            } else {
            	request.setHeader("Content-Type", "application/octet-stream");
            }
            
            request.setHeader("Ocp-Apim-Subscription-Key", key);

            // Request body
            if (mode == Emotion.REMOTE_IMAGE) {
            	StringEntity reqEntity = new StringEntity("{ \"url\": \"" + image + "\" }");
            	request.setEntity(reqEntity);
            } else {
            	File file = new File(image);
            	@SuppressWarnings("deprecation")
            	FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");
            	request.setEntity(reqEntity);
            }
            

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) 
            {
                JSONResponse = EntityUtils.toString(entity);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return JSONResponse;
	}
}
