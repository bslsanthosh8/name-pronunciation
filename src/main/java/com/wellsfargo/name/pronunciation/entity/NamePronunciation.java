package com.wellsfargo.name.pronunciation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "NAME_PRONUNCIATION")
public class NamePronunciation {

    @Column(name = "uid")
    @Id
    String uid;
    @Column(name = "pronunciation_sound")
    @Lob
    byte[] pronunciationSound;
    @Column(name = "format")
    String format;
    @Column(name = "created_by")
    String createdBy;
    @Column(name = "created_timestamp")
    @CreationTimestamp
    Date createdTimestamp;
    @UpdateTimestamp
    @Column(name = "modified_timestamp")
    Date modifiedTimestamp;
}
