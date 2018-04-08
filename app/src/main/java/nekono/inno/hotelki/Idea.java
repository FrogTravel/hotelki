package nekono.inno.hotelki;

import java.util.List;

/**
 * Created by ekaterina on 4/8/18.
 */

public class Idea {
    private String name;
    private String description;
    private int likes;
    private int dislikes;
    private int id;
    private double lan;
    private double lon;
    private int approved;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public Idea(int id, String name, String description, int likes, int dislikes, double lat, double lng, int approved) {
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.id = id;
        this.lan = lat;
        this.lon = lng;
        this.approved = approved;
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
        return lan;
    }

    public void setLat(double lat) {
        this.lan = lat;
    }

    public double getLng() {
        return lon;
    }

    public void setLng(double lng) {
        this.lon = lng;
    }

}
