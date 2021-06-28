package cl.zco.rbernedo.empleos.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import cl.zco.rbernedo.empleos.model.Job;

@Service
public class JobsServiceImpl implements IJobsService {
	
 	private List<Job> list = null;
	
	public JobsServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		list = new LinkedList<Job>();
		try {
			list.add(new Job(1,"Ingeniero Civil Ind.","Se requiere para diseñar puente", sdf.parse("08-02-2021"), 10000000.0, 1).setImage("company1.png"));
			list.add(new Job(2,"Contador Publico","Empresa importante requiere profesional con 5 años de experiencia", sdf.parse("01-09-2016"), 250000.0, 0 ).setImage("company2.png"));
			list.add(new Job(3,"Ingeniero Eléctrico","Se requiere para poner una ampolleta", sdf.parse("01-01-2021"), 3000000.0, 0).setImage("company3.png"));
			list.add(new Job(4,"Diseñador Gráfico","Se requiere para crear etiqueta", sdf.parse("03-02-2022"), 4500000.0, 1));			
		} catch (Exception e) {
			System.out.println("Error: "+e.getMessage());
		}		
	}

	@Override
	public List<Job> searchAll() {
		return list;
	}

	@Override
	public Job findById(Integer jobId) {
		for(Job job : list) {
			if(job.getId() == jobId) {
				return job;
			}
		}
		return null;
	}

	@Override
	public void save(Job job) {
		this.list.add(job);// TODO 
	}

}
