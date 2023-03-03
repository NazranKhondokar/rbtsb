package com.rbtsb.entities;

import com.rbtsb.enums.RoleName;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "ROLE_NAME", length = 60)
    private RoleName name;

    @Column(name = "STATUS_NAME", length = 60, nullable = false)
    private String statusName;
}