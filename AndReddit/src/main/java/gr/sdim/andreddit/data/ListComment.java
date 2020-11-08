package gr.sdim.andreddit.data;

import java.util.function.BinaryOperator;

public class ListComment {
    public String text;
    public Integer level;
    public Boolean  hasReplies;

    public ListComment(String text, Integer level,Boolean hasReplies){
        this.text = text;
        this.level = level;
        this.hasReplies = hasReplies;
    }
}
