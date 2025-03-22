package com.example.coindesk_api.vo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "currency_mapping")
public class CurrencyMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_code", unique = true, nullable = false)
    private String currencyCode;

    @Column(name = "currency_name", nullable = false)
    private String currencyName;
}