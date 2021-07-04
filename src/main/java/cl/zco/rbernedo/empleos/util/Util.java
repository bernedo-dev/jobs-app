package cl.zco.rbernedo.empleos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Util {

    public static String saveFile(MultipartFile multiPart, String path){
        String originalName = multiPart.getOriginalFilename();
        originalName = originalName.replace(" ", "-");
        String finalName = randomAlphaNumeric(8)+originalName;
        System.out.println(finalName+" / "+ path );
        try {
            File imageFile = new File(path+finalName);
            System.out.println("Archivo: "+ imageFile.getAbsolutePath());
            multiPart.transferTo(imageFile);
            return finalName;
        }catch (IOException e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public static String randomAlphaNumeric(int count){
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        while(count-- != 0){
            int character = (int) (Math.random() * CHARACTERS.length());
            builder.append(CHARACTERS.charAt(character));
        }
        return builder.toString();
    }
}
