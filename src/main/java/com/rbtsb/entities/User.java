package com.rbtsb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USER_EMAIL"
        }),
        @UniqueConstraint(columnNames = {
                "USER_MOBILE"
        })
})
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @NaturalId
    @Column(name = "USER_EMAIL", length = 40)
    @Email
    private String email;

    @Column(name = "USER_MOBILE", length = 40)
    private String mobile;

    @Column(name = "USER_PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "IS_ACTIVE")
    private boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    public User(String userName, String email, String mobile, String password, boolean isActive) {
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.isActive = isActive;
    }
}