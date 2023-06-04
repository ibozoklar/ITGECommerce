package com.ihsan.itgecommerce.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.ihsan.itgecommerce.entity.enums.State;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonIgnoreType
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private State state;


    private String email;

    private String password;

    private String activationCode;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;

    @OneToMany
    private List<Product> products;

    @OneToOne
    private Basket basket;

}
