package com.wellsfargo.name.pronunciation.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "AUDIT_LOG")
public class AuditLog {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_log_sequence")
    @SequenceGenerator(sequenceName = "audit_log_sequence", allocationSize = 1, name = "audit_log_sequence")
    Integer id;
    @Column(name = "app_id")
    Integer appId;
    @Column(name = "uid")
    String uid;
    @Column(name = "created_timestamp")
    @CreationTimestamp
    Date createdTimestamp;
    @Column(name = "requested_name")
    String requestedName;
    @Column(name = "service_name")
    String serviceName;

}
