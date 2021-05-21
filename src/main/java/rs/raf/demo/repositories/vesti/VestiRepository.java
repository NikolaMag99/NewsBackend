package rs.raf.demo.repositories.vesti;

import rs.raf.demo.entities.Vesti;

import java.util.List;

public interface VestiRepository {
    public Vesti addSubject(Vesti vesti);
    public List<Vesti> allSubjects();
    public Vesti findSubject(Integer id);
    public void deleteSubject(Integer id);
}
