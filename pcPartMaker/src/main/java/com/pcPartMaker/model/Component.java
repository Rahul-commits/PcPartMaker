package com.pcPartMaker.model;


import javax.persistence.*;

@Entity
@Table(name = "component")
public class Component {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long component_id;
    private float rating;
}
