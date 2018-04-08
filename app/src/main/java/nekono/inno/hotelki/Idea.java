package nekono.inno.hotelki;

/**
 * Created by ekaterina on 4/8/18.
 */

public class Idea {
    private String name, description;
    private int likes, dislikes;
    private double lat;
    private double lng;

    public Idea(String name, String description, int likes, int dizlikes, double lat, double lng) {
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.dislikes = dizlikes;
        this.lat = lat;
        this.lng = lng;
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
}
