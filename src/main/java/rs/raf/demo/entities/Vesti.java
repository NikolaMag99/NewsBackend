package rs.raf.demo.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.crypto.Data;


public class Vesti {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String title;

    @NotNull(message = "Content field is required")
    @NotEmpty(message = "Content field is required")
    private String content;

    private Data createdAt;

    private Integer visits;

    private User author;
    private Kategorija kategorija;


    public Vesti() {
    }


    public Vesti(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String title, @NotNull(message = "Content field is required") @NotEmpty(message = "Content field is required") String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Data getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Data createdAt) {
        this.createdAt = createdAt;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}


