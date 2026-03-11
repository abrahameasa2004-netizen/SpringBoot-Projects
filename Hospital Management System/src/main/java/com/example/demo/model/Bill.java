package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private double amount;
    private String billingStatus; // The variable name must match the setter

    public Bill() {}

    // --- MANUALLY ADD THESE IF THEY ARE MISSING ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    // This is the specific method your Controller is looking for
    public String getBillingStatus() { 
        return billingStatus; 
    }

    public void setBillingStatus(String billingStatus) { 
        this.billingStatus = billingStatus; 
    }
}