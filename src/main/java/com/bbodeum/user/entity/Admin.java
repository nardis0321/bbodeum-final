package com.bbodeum.user.entity;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin extends UserBase {

	@Builder
    public Admin(String id, String password, UserType userType, UserStatus userStatus) {
        this.id = id;
        this.password = password;
        this.userType = userType;
        this.userStatus = userStatus;
    }

}