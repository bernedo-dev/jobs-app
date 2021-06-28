package cl.zco.rbernedo.empleos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.zco.rbernedo.empleos.model.Job;
import cl.zco.rbernedo.empleos.service.IJobsService;

@Controller
public class HomeController {
	
	@Autowired
	private IJobsService jobsService;
	
	@GetMapping("/table")
	public String showTable(Model model) {
		List<Job> list = jobsService.searchAll();
		model.addAttribute("jobs", list);
		return "table";
	}
	
	
	@GetMapping("/detail")
	public String showDetail(Model model) {
		Job job = new Job();
		job.setName("Ingeniero de comunicaciones");
		job.setDescription("Se solicita ingeniero para dar soporte a intranet");
		job.setPublicationDate(new Date());
		job.setSalary(9700.0);
		model.addAttribute("job", job);
		return "detail";
	}
	
	@GetMapping("/listing")
	public String showListing(Model model) {
		List<String> list = new LinkedList<String>();
		list.add("Ingeniero de Sistemas");
		list.add("Auxiliar de Contabilidad");
		list.add("Vendedor");
		list.add("Arquitecto");
		model.addAttribute("jobs", list);
		return "listing";
	}

	@GetMapping("/")
	public String showHome(Model model) {
		List<Job> list = jobsService.searchAll();
		model.addAttribute("jobs", list);		
		return "home";
	}
}
