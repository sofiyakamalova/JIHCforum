package kz.cource.jihcforum.ui.home;

public class Item {
    private String name;
    private String email;
    private int image;
    private int postImage;
    private String date;

    public Item(String name, String email, int image, int postImage, String date) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.postImage = postImage;
        this.date = date;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getAvatarUrl() {
        // Вернуть URL аватара пользователя
        return null;
    }


}
