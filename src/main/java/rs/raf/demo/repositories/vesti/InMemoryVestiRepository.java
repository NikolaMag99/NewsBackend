package rs.raf.demo.repositories.vesti;

import rs.raf.demo.entities.Vesti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryVestiRepository implements VestiRepository {
    private static List<Vesti> vestis = new CopyOnWriteArrayList<>();

    public InMemoryVestiRepository() {
        System.out.println(this);
    }

    @Override
    public synchronized Vesti addNews(Vesti vesti) {
        Integer id = vestis.size();
        vesti.setId(id);
        vestis.add(Math.toIntExact(id), vesti);

        return vesti;
    }

    @Override
    public List<Vesti> allNews() {
        return new ArrayList<>(vestis);
    }

    @Override
    public Vesti findNews(Integer id) {
        return vestis.get(id);
    }

    @Override
    public void deleteNews(Integer id) {
        vestis.remove(id.intValue());
    }
}
