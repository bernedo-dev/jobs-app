package cl.zco.rbernedo.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cl.zco.rbernedo.empleos.model.Category;
import cl.zco.rbernedo.empleos.service.ICategoriesService;
import cl.zco.rbernedo.empleos.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.zco.rbernedo.empleos.model.Job;
import cl.zco.rbernedo.empleos.service.IJobsService;

@Controller
@RequestMapping("/jobs")
public class JobsController {

	//key in application.properties
	@Value("${jobsApp.path.images}")
	private String path;
	
	@Autowired
	private IJobsService jobsService;

	@Autowired
	private ICategoriesService categoriesService;
	
	@GetMapping("/create")
	public String create(Job job, Model model) {
		model.addAttribute("categories",  categoriesService.searchAll());
		return "jobs/jobForm";
	}
	
	@PostMapping("/save")
	public String save(Job job, BindingResult result, RedirectAttributes attributes, @RequestParam("fileImage")MultipartFile multiPart) {
		System.out.println("Job:"+job);
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Error: "+error.getDefaultMessage());
			}
			return "jobs/jobForm";
		}
		if(!multiPart.isEmpty()){
			//String ruta = "/jobs/img-jobs/ //Linux/MAC"
			String imageName = Util.saveFile(multiPart,path);
			if(imageName != null){
				job.setImage(imageName);
			}
		}
		jobsService.save(job);
		attributes.addFlashAttribute("msg","Registro Guardado");
		return "redirect:/jobs/index";
	}
	
	@GetMapping("/index")
	public String showIndex(Model model) {
		List<Job> jobsList = jobsService.searchAll();
		System.out.println(jobsList);
		model.addAttribute("jobs", jobsList);
		return "jobs/jobsList";
	} 
		
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int jobId, Model model) {
		System.out.println("Borrando trabajo con id: "+jobId);
		model.addAttribute("jobId", jobId);
		return "message";
	}

	@GetMapping("/view/{id}")
	public String showDetail(@PathVariable("id") int jobId, Model model) {
		Job job = jobsService.findById(jobId);
		System.out.println("Job:"+ job);
		model.addAttribute("job",job);
		return "detail";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
		
}
