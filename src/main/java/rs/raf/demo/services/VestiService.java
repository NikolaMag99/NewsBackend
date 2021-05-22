package rs.raf.demo.services;

import rs.raf.demo.entities.Vesti;
import rs.raf.demo.repositories.vesti.VestiRepository;

import javax.inject.Inject;
import java.util.List;

public class VestiService {

    public VestiService() {
        System.out.println(this);
    }

    @Inject
    private VestiRepository vestiRepository;

    public Vesti addNews(Vesti vesti) {
        return this.vestiRepository.addNews(vesti);
    }

    public List<Vesti> allNews() {
        return this.vestiRepository.allNews();
    }

    public Vesti findNews(Integer id) {
        return this.vestiRepository.findNews(id);
    }

    public void deleteNews(Integer id) {
        this.vestiRepository.deleteNews(id);
    }
}
