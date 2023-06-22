package com.qunapaq.zenquna.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="payments_data")
public class PaymentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="donor_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_DONOR_ID"))
    @JsonProperty("donor_id")
    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
    private Donor donor;
    @Column(name="card_number", nullable = false)
    private String cardNumber;
    @Column(name="expiration_date", nullable = false)
    private LocalDate expirationDate;
    @Column(name="security_code", nullable = false)
    private String securityCode;
    @Column(name="card_holder", nullable = false)
    private String cardHolder;
}
