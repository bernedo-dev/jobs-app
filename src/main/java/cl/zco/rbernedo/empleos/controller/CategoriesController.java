package cl.zco.rbernedo.empleos.controller;

import cl.zco.rbernedo.empleos.model.Category;
import cl.zco.rbernedo.empleos.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/categories")
public class CategoriesController {

	@Autowired
	private ICategoriesService categoriesService;
	
	// @GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String showIndex(Model model) {
		List categories = this.categoriesService.searchAll();
		model.addAttribute("categories", categories);
		return "categories/categoriesList";
	}
	// @GetMapping("/create")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Category category) {
		return "categories/categorieForm";
	}
	// @PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Category category, BindingResult result, RedirectAttributes attributes) {
		System.out.println("Categoria: "+category.getName());
		System.out.println("Descripción: "+category.getDescription());
		if(result.hasErrors()){
			return "categories/categorieForm";
		}
		categoriesService.save(category);
		attributes.addFlashAttribute("msg","Categoria Creada");
		return "redirect:/categories/index";
	}
}
