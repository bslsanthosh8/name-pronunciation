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
@Table(name = "ENTITLEMENT")
public class Entitlement {

    @Column(name = "entitlement")
    @Id
    String entitlement;
    @Column(name = "entitlement_desc")
    String entitlement_desc;
    @Column(name = "created_by")
    String createdBy;
    @CreationTimestamp
    @Column(name = "created_timestamp")
    Date createdTimestamp;
    @UpdateTimestamp
    @Column(name = "modified_timestamp")
    Date modifiedTimestamp;
}
