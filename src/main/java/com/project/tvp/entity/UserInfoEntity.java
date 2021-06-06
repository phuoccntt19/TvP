package com.project.tvp.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getEmailShow() {
        String emailShow = email;
        for (int i = 1; i < emailShow.indexOf('@'); i++) {
            emailShow = emailShow.substring(0, i) + "*" + emailShow.substring(i + 1);
        }
        return emailShow;
    }

//    String emailShow = email;
//        for (int i = 1; i < emailShow.indexOf('@'); i++) {
//        emailShow = emailShow.substring(0, i) + "*" + emailShow.substring(i + 1);
//    }
//        return emailShow;

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }
}
