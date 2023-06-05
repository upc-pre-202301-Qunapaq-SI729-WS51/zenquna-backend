package com.qunapaq.zenquna.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="address", nullable = false)
    private String address;
    @Column(name="reference")
    private String reference;
    @Column(name="district", nullable = false)
    private String district;
    @Column(name="province", nullable = false)
    private String province;
    @Column(name="department", nullable = false)
    private String department;
}
