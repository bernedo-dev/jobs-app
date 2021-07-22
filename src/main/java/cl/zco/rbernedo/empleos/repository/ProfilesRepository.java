package cl.zco.rbernedo.empleos.repository;


import cl.zco.rbernedo.empleos.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository  extends JpaRepository<Profile, Integer> {
}
