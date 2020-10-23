package spring.edu.ms.app.vehicleregistrationapp.domain;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="VEHICLE_REGISTARTION")
public class VehicleRegistrationEntity {

	@Id
	@GeneratedValue
	public Long id;
	@Column(nullable = false)
	public String ssn;
	@Column
	public String ownerUUID;
	@Column(nullable = false,unique=true)
	public String registrationId;
	@Column(nullable = false)
	public String vehicleType;
	@Column(nullable = false)
	public String vehicleWheelType;
	@Column
	public String modelDetails;
	@Column(nullable = false,unique=true)
	public Long chassisNumber;
	@Column(nullable = false)
	public String registartionState;
	@CreationTimestamp
	public LocalDateTime creationTime;
	@UpdateTimestamp
	public LocalDateTime updateTime;
}
