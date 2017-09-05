package sun.geonoon.wh.androidtest.news;

/**
 * Created by marc on 17-9-5.
 */

public class News {

    private String title;

    private String content;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
