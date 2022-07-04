package com.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Trip> trips = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Flight> flights = new HashSet<>();

}
