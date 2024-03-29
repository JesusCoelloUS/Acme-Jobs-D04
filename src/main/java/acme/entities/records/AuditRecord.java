
package acme.entities.records;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Properties

	@NotBlank
	private String				title;

	@NotBlank
	@Pattern(regexp = "^(DRAFT|PUBLISHED)$")
	private String				status;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotBlank
	private String				body;

	//Relationships

	@ManyToOne(optional = false)
	private Job					job;

}
