package cl.zco.rbernedo.empleos.service;

import java.util.List;
import cl.zco.rbernedo.empleos.model.Job;

public interface IJobsService {
	List<Job> searchAll();
	
	Job findById(Integer jobId);
	
	void save(Job job);
}
