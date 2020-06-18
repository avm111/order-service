package com.domain.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the registerd_users database table.
 * 
 */
@Entity
@Table(name="registerd_users")
@Getter
@Setter
public class RegisterdUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "customer_id",nullable=true)
	private Customer customer;

	@Column(name="login_email")
	private String loginEmail;

	private String password;

	@Column(name="user_role")
	private String userRole;

	public RegisterdUser() {
	}
}