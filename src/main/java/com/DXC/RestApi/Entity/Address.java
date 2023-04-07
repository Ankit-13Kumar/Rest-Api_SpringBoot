package com.DXC.RestApi.Entity;
import javax.persistence.*;
//for one to one relation

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;

    //bidirectional one to one
    //there is no column associated with that's why
    //non ow-ning side
    //assignment
    @OneToOne(mappedBy = "address")
    private Student student;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
