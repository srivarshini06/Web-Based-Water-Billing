//package com.water.backend.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "water_bills")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class WaterBill {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "consumer_id")
//    private Consumer consumer;
//
//    @Column(nullable = false)
//    private Double unitsConsumed;
//
//    @Column(nullable = false)
//    private Double amount;
//
//    @Column(nullable = false)
//    private LocalDate billingDate;
//
//    @Column(nullable = false)
//    private LocalDate dueDate;
//
//    @Column(nullable = false)
//    private String status;
//}