//package com.group9.fitnesstracker.model;
//import jakarta. persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//@Entity
//@Table(name = "exercises")
//public class Exercise {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(length = 2000)
//    private String description;
//
//    @Column(nullable = false)
//    private String muscleGroup;
//
//    public Exercise() {
//    }
//    public Exercise(String name, String description, String muscleGroup) {
//        this.name = name;
//        this.description = description;
//        this.muscleGroup = muscleGroup;
//    }
//    public Long getId() {
//        return id;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public String getMuscleGroup() {
//        return muscleGroup;
//    }
//    public void setMuscleGroup(String muscleGroup) {
//        this.muscleGroup = muscleGroup;
//    }
//
//
//}
