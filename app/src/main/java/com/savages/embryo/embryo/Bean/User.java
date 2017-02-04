package com.savages.embryo.embryo.Bean;

/**
 * Created by Asus on 04-02-2017.
 */
public class User {

        private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;
        private String unique_id;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;
        private String old_password;
        private String new_password;

        public String getName() {
            return name;
        }


        public String getUnique_id() {
            return unique_id;
        }

        public void setName(String name) {
            this.name = name;
        }



        public void setOld_password(String old_password) {
            this.old_password = old_password;
        }

        public void setNew_password(String new_password) {
            this.new_password = new_password;
        }


}

