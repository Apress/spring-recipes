// Final 
package com.apress.springrecipes.court.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

public class Member {

    // JSR-303 validation annotations
    @NotNull
    @Size(min=2)
    private String name;
    @NotNull
    @Size(min = 9, max = 14)
    private String phone;
    @Pattern(regexp=".+@.+\\.[a-z]+")
    private String email;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
