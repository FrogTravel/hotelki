package nekono.inno.hotelki;

import java.util.List;

/**
 * Created by ekaterina on 4/8/18.
 */

public class Idea {
    private String name, description;
    private int likes, dislikes;
    private double lat;
    private double lng;
    private List<String> tags;


    public Idea(String name, String description, List<String> tags, int likes, int dislikes, double lat, double lng) {
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.lat = lat;
        this.lng = lng;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
