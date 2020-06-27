package com.project.wallet.dto;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


    @Entity(name = "user_details")
    public class UserDetailsDto {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
        @GenericGenerator(name = "native",strategy = "native")
        private Integer id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email_id")
        private String emailId;

        @Column(name = "password")
        private String password;

        @Column(name = "phone_number")
        private Long phoneNumber;

        @OneToOne(cascade=CascadeType.ALL)
        @JoinColumn(name="wallet_id")
        private WalletDto wallet;

        private String city;
        private String country;

        public WalletDto getWallet() {
            return wallet;
        }

        public void setWallet(WalletDto wallet) {
            this.wallet = wallet;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }


