package gr.sdim.redditapiclient;

public class Reddit {
    public String prefix;
    public String title;
    public String details;
    public Post   current_post;
    public Post   nextPost;
    public String reddit_url;
    public String name;

    public Reddit(String prefix, String title, String details, Post current_post, Post nextPost, String reddit_url, String name){
        this.prefix = prefix;
        this.title = title;
        this.details = details;
        this.current_post = current_post;
        this.nextPost = nextPost;
        this.reddit_url = reddit_url;
        this.name = name;
    }

}
