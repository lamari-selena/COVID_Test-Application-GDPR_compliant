package covidnews.models;

public class News {

    private String dateWritten;
    private String title;
    private String content;



    private String image;

    public String getDateWritten() {
        return dateWritten;
    }

    public void setDateWritten(String dateWritten) {
        this.dateWritten = dateWritten;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public News(String dateWritten, String title, String content, String image) {
        this.dateWritten = dateWritten;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public News() {
    }
}
