package rs.raf.demo.repositories.vesti;

import rs.raf.demo.entities.Vesti;

import java.util.List;

public interface VestiRepository {
    public Vesti addNews(Vesti vesti);
    public List<Vesti> allNews();
    public Vesti findNews(Integer id);
    public void deleteNews(Integer id);
}
