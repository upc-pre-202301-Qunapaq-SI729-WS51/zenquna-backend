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
@Table(name="donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="campaign_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_CAMPAIGN_HELP_ID"))
    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
    private Campaign campaign;
    @ManyToOne
    @JoinColumn(name="payment_data_id", nullable = false,
            foreignKey = @ForeignKey(name="FK_PAYMENT_DATA_ID"))
    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
    private PaymentData paymentData;
    @Column(name="amount", nullable = false)
    private Double amount;
    @Column(name="date", nullable = false)
    private LocalDate date;
}
