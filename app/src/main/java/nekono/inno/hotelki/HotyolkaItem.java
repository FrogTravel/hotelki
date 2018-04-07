package nekono.inno.hotelki;

public class HotyolkaItem {
    private String name, description;
    private int likes, dizlikes;

    public HotyolkaItem(String name, String description, int likes, int dizlikes) {
        this.name = name;
        this.description = description;
        this.likes = likes;
        this.dizlikes = dizlikes;
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

    public int getDizlikes() {
        return dizlikes;
    }

    public void setDizlikes(int dizlikes) {
        this.dizlikes = dizlikes;
    }
}
