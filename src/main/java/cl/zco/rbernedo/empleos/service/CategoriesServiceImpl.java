package cl.zco.rbernedo.empleos.service;

import cl.zco.rbernedo.empleos.model.Category;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriesServiceImpl implements ICategoriesService{

    private List<Category> categoriesList = null;

    public CategoriesServiceImpl(){
        categoriesList = new LinkedList<Category>();
        Category cat = new Category();
        cat.setId(1);
        cat.setName("Primera Categoría");
        cat.setDescription("La cat de rbernedo");
        Category cat2 = new Category();
        cat2.setId(2);
        cat2.setName("Segunda Categoría");
        cat2.setDescription("la 2da categoría");
        categoriesList.add(cat);
        categoriesList.add(cat2);
    }

    @Override
    public void save(Category category) {
        this.categoriesList.add(category);
    }

    @Override
    public List<Category> searchAll() {
        return this.categoriesList;
    }

    @Override
    public Category findById(Integer categoryId) {
       return this.categoriesList.stream().filter( cat -> cat.getId() == categoryId).toList().get(0);
    }
}
