package sg.nus.iss.workshop26.fileUpload.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private String poster;
    private String comment;
    private String imageType;
    private String encodedString;
    private byte[] image;
    
    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    private Integer postId;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public static Post populate(ResultSet rs) throws SQLException {
        final Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setComment(rs.getString("comment"));
        post.setImageType(rs.getString("mediatype"));
        System.out.println(rs.getBytes("photo"));
        post.setImage(rs.getBytes("photo"));
        post.setPoster(rs.getString("poster"));
        return post;
    }
}
