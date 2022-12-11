package com.example.hakaton.entity;

import javax.persistence.*;

@Entity
public class TrashCanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int paperFullness;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int glassFullness;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int plasticFullness;
    @Column(nullable = false)
    private String address;
    private boolean isDisabled;

    public TrashCanEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public int getPaperFullness() {
        return paperFullness;
    }

    public void setPaperFullness(int paperFullness) {
        this.paperFullness = paperFullness;
    }

    public int getGlassFullness() {
        return glassFullness;
    }

    public void setGlassFullness(int glassFullness) {
        this.glassFullness = glassFullness;
    }

    public int getPlasticFullness() {
        return plasticFullness;
    }

    public void setPlasticFullness(int plasticFullness) {
        this.plasticFullness = plasticFullness;
    }
}
