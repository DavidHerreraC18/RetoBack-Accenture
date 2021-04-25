package com.payment.api.model.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents the product.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product{

    /**
     * Represents the unique identification of the Product.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    /**
     * Represents the name of the product.
     */
    @Column(name = "name", unique=true)
    private String name;

    /**
     * Represents the price of the product.
     */
    @Column(name = "price")
    private Long price;
}