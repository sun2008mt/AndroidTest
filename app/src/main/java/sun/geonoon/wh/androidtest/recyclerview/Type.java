package sun.geonoon.wh.androidtest.recyclerview;

/**
 * Created by marc on 17-9-5.
 */

public class Type {
    private int imageId;
    private String name;

    public Type(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
