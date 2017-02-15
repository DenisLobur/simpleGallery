package simple.gallery.den.simplegallery.screen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denis on 15-Feb-17.
 */

public class Photo {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("camera")
    @Expose
    private String camera;

    public String getName() {
        return name;
    }

    public String getCamera() {
        return camera;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", camera='" + camera + '\'' +
                '}';
    }
}
