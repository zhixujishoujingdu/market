package com.zqx.dao;


import com.zqx.pojo.User;
import org.apache.ibatis.annotations.Param;



public interface UserMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(User recode);
    int insertSelective(User recode);
    User selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(User recode);
    int updateByPrimaryKey(User recode);
    int checkUserName(String username);
    int checkEmail(String email);
    User selectLogin(@Param("username") String username, @Param("password") String password);
    String selectQuestionByUsername(String username);
    int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);
    int updatePasswordByUsername(@Param("username")String username,@Param("passwordNew")String passwordNew);
    int checkPassword(@Param("password")String password, @Param("userId")Integer userId);
    int checkEmailByUserId(@Param("email")String email,@Param("userId")Integer userId);


}
