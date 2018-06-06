package com.zqx.service.serviceImpl;

import com.zqx.common.Const;
import com.zqx.common.ServiceResponse;
import com.zqx.common.TokenCache;
import com.zqx.dao.UserMapper;
import com.zqx.pojo.User;
import com.zqx.service.IUserService;
import com.zqx.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResponse<User> login(String username, String password) {

        int resultCount = userMapper.checkUserName(username);
        if (resultCount == 0){
            return ServiceResponse.createByErrorMessage("用户名不存在！");
        }
        System.out.println(password);
        password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,password);
        if (user == null){
            return ServiceResponse.createByErrorMessage("密码错误！");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySuccess("登陆成功",user);
    }

    //注册
    public ServiceResponse<String> register(User user){
        //校验用户名是否存在
        ServiceResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);

        if (!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0){
            return ServiceResponse.createByErrorMessage("注册失败！");
        }
        return ServiceResponse.createBySuccessMessage("注册成功！");
    }
    public ServiceResponse<String> checkValid(String str,String type){
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUserName(str);
                if (resultCount > 0){
                    return ServiceResponse.createByErrorMessage("用户名已存在！");
                }
            }
            if (Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0){
                    return ServiceResponse.createByErrorMessage("邮箱已被注册！");
                }
            }
        }else {
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        return ServiceResponse.createBySuccessMessage("校验成功");
    }

    public ServiceResponse<String> selectQuestion(String username){
        ServiceResponse validResponse = this.checkValid(username,Const.USERNAME);
        if (validResponse.isSuccess()){
            return ServiceResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isBlank(question)){
            return ServiceResponse.createBySuccess(question);
        }
        return ServiceResponse.createByErrorMessage("找回密码问题为空");
    }

    public ServiceResponse<String> checkAnswer(String username,String question,String answer){
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if (resultCount > 0){
            //问题答案正确
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServiceResponse.createBySuccess(forgetToken);
        }
        return ServiceResponse.createByErrorMessage("问题答案错误");
    }

    public ServiceResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServiceResponse.createByErrorMessage("参数错误，需要token");
        }
        ServiceResponse validResponse = this.checkValid(username, Const.USERNAME);
        if (validResponse.isSuccess()) {
            return ServiceResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + username);
        if (StringUtils.isBlank(token)) {
            return ServiceResponse.createByErrorMessage("token无效或者过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            //更新密码
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (rowCount > 0) {
                return ServiceResponse.createBySuccessMessage("修改密码成功");
            }
        } else {
            return ServiceResponse.createByErrorMessage("token错误，请重新获取token");
        }
        return ServiceResponse.createByErrorMessage("修改密码失败");
    }

    public ServiceResponse<String> resetPassword(String passwordOld, String passwordNew,User user){
        //user 为了防止横向越权，校验用户及其旧密码，故查询一个count(1)
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if (resultCount == 0){
            return ServiceResponse.createByErrorMessage("原密码错误");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0){
            return ServiceResponse.createBySuccessMessage("密码更新成功");
        }
        return ServiceResponse.createByErrorMessage("密码重置失败");
    }

    public ServiceResponse<User> updateInformation(User user){
        /**
         *  username是不能被更新的
         *  Email需要校验是否存在，若Email相同，则不能是当前用户的
         */
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (resultCount > 0){
            return ServiceResponse.createByErrorMessage("邮箱已存在，请更换邮箱");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0){
            return ServiceResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServiceResponse.createByErrorMessage("更新个人信息失败");
    }

    public ServiceResponse<User> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ServiceResponse.createByErrorMessage("找不到用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySuccess(user);
    }
}
