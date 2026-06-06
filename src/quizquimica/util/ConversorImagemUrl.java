package quizquimica.util;

public class ConversorImagemUrl {

    public static String converter(String url) {
        if (url == null || url.isBlank()) return null;

        if (url.contains("drive.google.com/file/d/")) {
            String id = url.split("/d/")[1].split("/")[0];
            return "https://drive.google.com/uc?export=view&id=" + id;
        }

        if (url.contains("imgur.com/") && !url.contains("i.imgur.com")) {
            String id = url.substring(url.lastIndexOf("/") + 1);
            return "https://i.imgur.com/" + id + ".png";
        }
        
        if (url.contains("imgur.com/") && !url.contains("i.imgur.com")) {
            String id = url.substring(url.lastIndexOf("/") + 1);
            return "https://i.imgur.com/" + id + ".png";
        }

        return url; 
    }
}