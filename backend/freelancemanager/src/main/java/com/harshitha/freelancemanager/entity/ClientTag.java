package com.harshitha.freelancemanager.entity;

import jakarta.persistence.*;

@Entity
@Table(name="client_tags")
public class ClientTag {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public ClientTag(){}

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public Client getClient(){
        return client;
    }

    public void setClient(Client client){
        this.client=client;
    }

}