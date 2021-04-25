package com.payment.api.model.userclient;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.payment.api.model.bill.Bill;
import com.payment.api.model.clientorder.ClientOrder;

/**
 * Represents the client of the application that can order prducts.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_client")
public class UserClient{

    /**
     * Represents the unique identification of the UserClient.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    /**
     * Represents the name of the UserClient.
     */
    @Column(name = "name")
    private String name;

    /**
     * Represents the email of the UserClient.
     */
    @Email
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Represents the documtent type of the UserClient.
     * Document Type can be CC, CE, Etc.
     */
    @Column(name = "document_type")
    private String documentType;

    /**
     * Represents the document of the UserClient.
     */
    @Column(name = "document")
    private String document;

    /**
     * Represents the address of the UserClient.
     */
    @Column(name = "address")
    private String address;

    /**
     * Represents the actual order of the UserClient.
     */
    @OneToOne(fetch = FetchType.EAGER)
    private ClientOrder actualClientOrder;

    /**
     * Represents the orders of the UserClient.
     */
    @OneToMany(mappedBy = "userClient",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ClientOrder> clientOrders;

    /**
     * Represents the created bills of the UserClient.
     */
    @OneToMany(mappedBy = "userClient",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Bill> bills;
    
}