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

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user_client")
public class UserClient{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

    @Column(name = "name")
    private String name;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document")
    private String document;

    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    private ClientOrder actualClientOrder;

    @OneToMany(mappedBy = "userClient",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ClientOrder> clientOrders;

    @OneToMany(mappedBy = "userClient",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Bill> bills;
    
}