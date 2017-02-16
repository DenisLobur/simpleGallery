package simple.gallery.den.simplegallery.screen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Photo model which has all necessary photo attributes
 */

public class Photo implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("camera")
    @Expose
    private String camera;
    @SerializedName("image_url")
    @Expose
    private String photoUrl;
    @SerializedName("user")
    @Expose
    private User user;

    public String getName() {
        return name;
    }

    public String getCamera() {
        return camera;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public User getUser() {
        return user;
    }

    public class User implements Serializable {
        @SerializedName("username")
        @Expose
        private String userName;

        public String getUserName() {
            return userName;
        }
    }

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", camera='" + camera + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", username='" + user.getUserName() + '\'' +
                '}';
    }
}
