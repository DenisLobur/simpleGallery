package simple.gallery.den.simplegallery.screen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("camera")
    @Expose
    private String camera;
    @SerializedName("image_url")
    @Expose
    private String photoUrl;

    public String getName() {
        return name;
    }

    public String getCamera() {
        return camera;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", camera='" + camera + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
