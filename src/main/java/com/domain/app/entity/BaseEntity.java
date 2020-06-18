package com.domain.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

	@Id
	@Column(name="id")
	private String id;
	
	@PrePersist
	void onCreate() {
		this.setId(UUID.randomUUID().toString());
	}
}
