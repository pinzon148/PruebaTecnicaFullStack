package entity;

package com.example.orchestrator.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class CreditEvaluation extends PanacheEntity {

    public String cedula;
    public double monto;
    public String estado;
    public int score;
}