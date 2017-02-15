package simple.gallery.den.simplegallery.screen.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Page {
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("total_pages")
    @Expose
    private Long totalPages;
    @SerializedName("total_items")
    @Expose
    private Long totalItems;
    @SerializedName("feature")
    @Expose
    private String feature;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos;


    public Integer getCurrentPage() {
        return currentPage;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public String getFeature() {
        return feature;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", feature='" + feature +
                ", photos.count=" + photos.size() +
                '}';
    }
}
