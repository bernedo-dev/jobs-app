package cl.zco.rbernedo.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.zco.rbernedo.empleos.model.Job;
import cl.zco.rbernedo.empleos.service.IJobsService;

@Controller
@RequestMapping("/jobs")
public class JobsController {
	
	@Autowired
	private IJobsService jobsService;
	
	@GetMapping("/create")
	public String create() {
		return "jobs/jobForm";
	}
	
	@PostMapping("/save")
	public String save(Job job) {
		System.out.println("Job:"+job);
		jobsService.save(job);
		return "jobs/jobsList";
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
