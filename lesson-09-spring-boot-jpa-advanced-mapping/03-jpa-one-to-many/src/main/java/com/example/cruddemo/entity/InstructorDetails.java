package com.example.cruddemo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetails {

    //the comments are the steps to create an entity

    //annotate the class as an entity and map to db table

    //define the fields

    //annotate the fiels with db coluln name

    //set up mapping for instructor entity

    //create constructors

    //generate getters/setters methods

    //generate toString() method

    
    @Id        //for the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //for auto incremantation
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtibeChannel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetails" ,cascade = CascadeType.ALL) //mappedBy = the joined field in instructor class
    private Instructor instructor;

    public InstructorDetails(){

    }

    public InstructorDetails(String youtibeChannel, String hobby) {
        this.youtibeChannel = youtibeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutibeChannel() {
        return youtibeChannel;
    }

    public void setYoutibeChannel(String youtibeChannel) {
        this.youtibeChannel = youtibeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    @Override
    public String toString() {
        return "InstructorDetails [id=" + id + 
                ", youtibeChannel=" + youtibeChannel + 
                ", hobby=" + hobby + "]";
    }
}
