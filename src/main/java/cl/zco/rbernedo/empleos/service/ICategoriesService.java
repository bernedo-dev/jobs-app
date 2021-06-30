package cl.zco.rbernedo.empleos.service;

import cl.zco.rbernedo.empleos.model.Category;

import java.util.List;

public interface ICategoriesService {

    void save(Category category);
    List<Category> searchAll();
    Category findById(Integer categoryId);
}
