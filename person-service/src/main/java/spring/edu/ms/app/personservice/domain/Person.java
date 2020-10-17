package spring.edu.ms.app.personservice.domain;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PERSON")
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="first_name",nullable = false)
	private String firstName;
	
	@Column(name="last_name",nullable = false)
	private String lastName;
	
	@Column(name="UID",nullable = false, unique = true)
	private String uuid;
	
	@CreationTimestamp
	@Column(name="CREATION_TIME")
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    @Column(name="LAST_UPDATED_TIME")
    private LocalDateTime updateDateTime;
	
    @Column(name="dob",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    @Column(name="ssn",nullable = false,unique=true)
    private String ssn;
	

}
