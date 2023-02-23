package com.bbodeum.user.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.bbodeum.basetime.entity.BaseTimeEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
//public abstract class UserBase extends BaseTimeEntity {
public abstract class UserBase {

	@Id
	@GeneratedValue
	private Long userId;
	
	protected String id;
	
	protected String email;

	protected String password;
	
	protected String name;
	
    @Enumerated(EnumType.STRING)
    protected UserType userType;

    @Enumerated(EnumType.STRING)
    protected UserStatus userStatus;
}
