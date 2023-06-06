package com.qunapaq.zenquna.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "donors")
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_DON_ID"))
    private User user;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name="age", nullable = false)
    private Integer age;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "dni", nullable = false, length = 8)
    private String DNI;
}
