package spring.edu.ms.app.vehicleregistrationapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="STATE_REGISTARTION_METADATA")
public class StateRegistrationEntity {

	@Id
	@GeneratedValue
	public Long id;
	@Column(nullable=false)
	private String state;
	@Column(nullable=false,name="STATE_SHORT_CODE")
	private String stateShortCode;
	@Column(nullable=false,name="NEXT_REG_ID")
	private String nextRegistrationId;
}
