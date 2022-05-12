package com.wellsfargo.name.pronunciation.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYEE_AVATAR")
public class EmployeeAvatar {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_avatar_sequence")
    @SequenceGenerator(sequenceName = "employee_avatar_sequence", allocationSize = 1, name = "employee_avatar_sequence")
    Integer id;
    @Column(name = "uid")
    String uid;
    @Column(name = "image")
    @Lob
    byte[] image;
    @Column(name = "created_by")
    String createdBy;
    @CreationTimestamp
    @Column(name = "created_timestamp")
    Date createdTimestamp;
    @UpdateTimestamp
    @Column(name = "modified_timestamp")
    Date modifiedTimestamp;
}
