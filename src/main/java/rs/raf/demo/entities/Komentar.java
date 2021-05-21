package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class Komentar {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String author;

    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;


    private Date createdAt;

    public Komentar() {
    }

    public Komentar(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String author, @NotNull(message = "Content field is required") @NotEmpty(message = "Content field is required") String content) {
        this.author = author;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
