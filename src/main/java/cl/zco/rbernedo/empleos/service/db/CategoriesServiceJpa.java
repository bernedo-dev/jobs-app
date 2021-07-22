package cl.zco.rbernedo.empleos.service.db;

import cl.zco.rbernedo.empleos.model.Category;
import cl.zco.rbernedo.empleos.repository.CategoriesRepository;
import cl.zco.rbernedo.empleos.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Primary
public class CategoriesServiceJpa implements ICategoriesService {

    @Autowired
    private CategoriesRepository catRepo;

    @Override
    public void save(Category category) {
        catRepo.save(category);
    }

    @Override
    public List<Category> searchAll() {
        return catRepo.findAll();
    }

    @Override
    public Category findById(Integer categoryId) {
        Optional<Category> optional = catRepo.findById(categoryId);
        return optional.orElse(null);
    }
}
