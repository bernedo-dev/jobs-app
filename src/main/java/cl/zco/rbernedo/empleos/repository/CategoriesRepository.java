package cl.zco.rbernedo.empleos.repository;

import cl.zco.rbernedo.empleos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Integer> {

}
