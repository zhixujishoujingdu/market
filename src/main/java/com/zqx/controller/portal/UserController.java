package com.zqx.controller.portal;

import com.zqx.common.Const;
import com.zqx.common.ResponseCode;
import com.zqx.common.ServiceResponse;
import com.zqx.pojo.User;
import com.zqx.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method= RequestMethod.POST)
    //返回时，自动通过Spring MVC的Jackson插件，将返回值序列化成json
    @ResponseBody
    public ServiceResponse<User> login(String username, String password, HttpSession session){
       //controller --> service --> mybatis --> dao

        ServiceResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    //登出接口
    @RequestMapping(value = "logout.do", method= RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServiceResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> register(User user){
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }

    /**
     * 获取用户请求信息
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> getInfo(HttpSession session){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user != null){
            return ServiceResponse.createBySuccess(user);
        }
        return ServiceResponse.createByErrorMessage("用户未登录，无法获取信息");
    }

    /**
     * 忘记密码问题
     * @param username
     * @return
     */
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    //使用本地缓存的校验问题答案是否正确
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }

    /**
     *重置密码
     * 1.拿到Token，并与缓存中的token做对比
     */

    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }

    //登录状态的重置密码
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServiceResponse.createByErrorMessage("用户未登录，请登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    //更新个人信息
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> update_information(HttpSession session,User user){
        User current_user = (User)session.getAttribute(Const.CURRENT_USER);
        if (current_user == null){
            return ServiceResponse.createByErrorMessage("用户未登录，请登录");
        }
        user.setId(current_user.getId());
        user.setUsername(current_user.getUsername());
        ServiceResponse<User> response = iUserService.updateInformation(user);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    //获取用户详细信息
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> getInformation(HttpSession session){
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录");
        }
        return iUserService.getInformation(currentUser.getId());
    }

}
