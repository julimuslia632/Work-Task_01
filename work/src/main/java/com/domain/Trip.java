package com.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trip_id;
//    @OneToMany
//    private final List<String> reasons = new ArrayList<>(Arrays.asList("Meeting" , "Training" ,"Project" ,
//            "Workshop" , "Event" , "Other"));
    private String reason;
    private String description;
    private String from_place;
    private String to_place;
    private String departure_date;
    private String arrival_date;
    private String trip_status;


}
