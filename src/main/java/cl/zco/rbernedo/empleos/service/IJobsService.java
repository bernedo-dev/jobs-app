package cl.zco.rbernedo.empleos.service;

import cl.zco.rbernedo.empleos.model.Job;

import java.util.List;

public interface IJobsService {
	List<Job> searchAll();
	
	Job findById(Integer jobId);
	
	void save(Job job);
}
