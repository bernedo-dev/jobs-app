package cl.zco.rbernedo.empleos.service.db;

import cl.zco.rbernedo.empleos.model.Job;
import cl.zco.rbernedo.empleos.repository.JobsRepository;
import cl.zco.rbernedo.empleos.service.IJobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class JobsServiceJpa implements IJobsService {

    @Autowired
    JobsRepository jobsRepo;

    @Override
    public List<Job> searchAll() {
        return jobsRepo.findAll();
    }

    @Override
    public Job findById(Integer jobId) {
        Optional<Job> optional = jobsRepo.findById(jobId);
        return optional.orElse(null);
    }

    @Override
    public void save(Job job) {
        jobsRepo.save(job);
    }
}
