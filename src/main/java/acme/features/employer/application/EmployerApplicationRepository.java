
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id=?1")
	Application findOneApplicationById(int id);

	@Query("select e.jobs from Employer e where e.id=?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select e from Employer e where e.id=?1")
	Employer findOneEmployerById(int id);

}
