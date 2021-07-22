package cl.zco.rbernedo.empleos.repository;

import cl.zco.rbernedo.empleos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
