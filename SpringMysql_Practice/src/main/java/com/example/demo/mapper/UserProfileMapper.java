//package com.example.demo.mapper;
//
//import com.example.demo.model.UserProfile;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import org.apache.ibatis.annotations.*;
//
//@Mapper
//public interface UserProfileMapper {
//    //내가 api를 호출하면 @Param때문에 전달된 id가 {id}로 전달되어서 sql문을 실행함.
//    //이후에 db에서 정보를 가지고 와서 반환이 되는 것이다.
//    @Select("SELECT * FROM UserProfile WHERE id = #{id}")
//    UserProfile selectUserProfile(@Param("id") String id);
//
//    @Select("SELECT * FROM UserProfile")
//    List<UserProfile> getUserList();
//
//    @Insert("INSERT INTO UserProfile VALUES(#{id}, #{name}, #{phone}, #{address})")
//    int insertUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);
//
//    @Update("UPDATE UserProfile SET name=#{name}, phone=#{phone}, address=#{address} WHERE id=#{id}")
//    int updateUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);
//
//    @Delete("DELETE FROM UserProfile WHERE id=#{id}")
//    int deleteUserProfile(@Param("id") String id);
//
//}
