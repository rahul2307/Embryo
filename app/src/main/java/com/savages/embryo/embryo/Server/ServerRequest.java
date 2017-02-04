package com.savages.embryo.embryo.Server;

import com.savages.embryo.embryo.Bean.User;

/**
 * Created by Asus on 04-02-2017.
 */
public class ServerRequest {

    private String operation;
    private User user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
