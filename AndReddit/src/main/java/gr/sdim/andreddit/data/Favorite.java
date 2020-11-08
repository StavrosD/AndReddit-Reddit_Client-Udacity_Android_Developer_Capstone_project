package gr.sdim.andreddit.data;

public class Favorite {
    private String title;
    private String url;

    public Favorite(){ }

    public Favorite(String title, String url){
        this.title = title;
        this.url = url;
    }

    public void setTitle(String title) {this.title = title;}

    public String getTitle() {return title;}

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}

}
