package com.zqx.service;

import com.zqx.common.ServiceResponse;
import com.zqx.pojo.User;

import javax.servlet.http.HttpSession;

public interface IUserService {
    //使用泛型做一个通用的数据端相应对象
    ServiceResponse<User> login(String username, String password);
    ServiceResponse<String> register(User user);
    ServiceResponse<String> checkValid(String str,String type);
    ServiceResponse<String> selectQuestion(String username);
    ServiceResponse<String> checkAnswer(String username,String question,String answer);
    ServiceResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
    ServiceResponse<String> resetPassword(String passwordOld, String passwordNew,User user);
    ServiceResponse<User> updateInformation(User user);
    ServiceResponse<User> getInformation(Integer userId);
}
