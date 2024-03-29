
package acme.entities.jobs;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.applications.Application;
import acme.entities.duties.Duty;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Properties

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	private String				reference;

	@NotBlank
	@Pattern(regexp = "^(DRAFT|PUBLISHED)$")
	private String				status;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotNull
	@Valid
	private Money				salary;

	@URL
	private String				moreInfo;

	@NotBlank
	private String				description;


	@Transactional
	public boolean isActive() {
		return this.status.equals("PUBLISHED") && new Date().before(this.deadline);
	}


	//Relationships

	@OneToMany
	private Collection<Duty>		duties;

	@OneToMany(mappedBy = "job")
	private Collection<Application>	applications;

}
