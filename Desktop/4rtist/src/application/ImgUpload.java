package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.scene.image.Image;

public class ImgUpload {
	public String Upload(String path) {
		HttpURLConnection urlRequest = null;
		String token = null;
		String projectID = "auth-test-3aad8";
		String bucketName = "auth-test-3aad8.appspot.com";
		File f = new File(path);
        f.getName();
        String objectName = f.getName();
		try {
			URL url = new URL("https://storage.googleapis.com/upload/storage/v1/b/auth-test-3aad8.appspot.com/o?uploadType=media&name=" + objectName); ///
			urlRequest = (HttpURLConnection) url.openConnection();
			urlRequest.setRequestMethod("POST");
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\"data-binary\":"+path+"\",\"Content-Type\":image/png}");
            osw.flush();
            osw.close();  
            urlRequest.connect();
            System.out.println(
                "File " + path + " uploaded to bucket " + bucketName + " as " + objectName + " " + url);
            int responseCode = urlRequest.getResponseCode();
            JsonObject rootobj = JsonParser.parseReader(new InputStreamReader((InputStream) urlRequest.getContent())).getAsJsonObject();
            token = rootobj.get("name").getAsString();
            System.out.println(token);
            System.out.println(responseCode);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
            urlRequest.disconnect();
        }
		return token;
	}
}
