package services;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CarPhotosProvider {
    public static List<String> getCarPhotos(ResultSet rsPhotos) throws Exception{
        List<String> imageBase64StringList = new ArrayList<String>();
        while(rsPhotos.next()){
            Blob blob = rsPhotos.getBlob("photo");

            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();

            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            imageBase64StringList.add(base64Image);

            System.out.println("Photo: "+ base64Image);

            inputStream.close();
            outputStream.close();
        }
        return imageBase64StringList;
    }
}
