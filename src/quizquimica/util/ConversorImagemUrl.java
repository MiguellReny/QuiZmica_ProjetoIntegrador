package quizquimica.util;

public class ConversorImagemUrl {

    public static String converter(String url) {
        if (url == null || url.isBlank()) return null;

        if (url.contains("drive.google.com/file/d/")) {
            String id = url.split("/d/")[1].split("/")[0];
            return "https://drive.google.com/uc?export=view&id=" + id;
        }

        return url;
    }
}