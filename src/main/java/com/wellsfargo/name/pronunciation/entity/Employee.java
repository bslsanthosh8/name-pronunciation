package com.wellsfargo.name.pronunciation.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE")

public class Employee {
    @Column(name = "uid")
    @Id
    String uid;
    @Column(name = "emp_id")
    String empId;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "preferred_name")
    String preferredName;
    @Column(name = "is_preferred_name_chosen")
    Character isPreferredNameChosen;
    @Column(name = "email_id")
    String emailId;
    @Column(name = "entitlement")
    String entitlement;
    @Column(name = "created_by")
    String createdBy;
    @CreationTimestamp
    @Column(name = "created_timestamp")
    Date createdTimestamp;
    @UpdateTimestamp
    @Column(name = "modified_timestamp")
    Date modifiedTimestamp;
}
