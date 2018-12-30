package com.example.web.service;

import com.example.web.bean.api.account.RegisterModel;
import com.example.web.bean.db.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 */
// 127.0.0.1/api/account/...
@Path("/account")
public class AccountService {
    @POST
    @Path("/register")
    // 指定请求与返回的相应体为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User register(RegisterModel model) {
        User user = new User();
        user.setName(model.getName());
        user.setSex(2);
        return user;
    }

}
