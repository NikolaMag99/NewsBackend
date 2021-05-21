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

    public Vesti addSubject(Vesti vesti) {
        return this.vestiRepository.addSubject(vesti);
    }

    public List<Vesti> allSubjects() {
        return this.vestiRepository.allSubjects();
    }

    public Vesti findSubject(Integer id) {
        return this.vestiRepository.findSubject(id);
    }

    public void deleteSubject(Integer id) {
        this.vestiRepository.deleteSubject(id);
    }
}
