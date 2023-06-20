package com.wsw.summercloud.user.repository;

import com.wsw.summercloud.user.entities.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/20 23:48
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long>, JpaSpecificationExecutor<UserInfoEntity> {
    /**
     * 根据用户名和密码获取用户信息
     *
     * @param username
     * @param password
     * @return UserInfoEntity
     */
    //jpql
    @Query("from UserInfoEntity where username = :username and password = :password and isDelete = 0")
    //sql
    //@Query(value = "select * from user_info where username = :username and password = :password and is_delete = 0", nativeQuery = true)
    UserInfoEntity getUserInfoEntity(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return UserInfoEntity
     */
    @Query("from UserInfoEntity where username = :username and isDelete = 0")
    UserInfoEntity getUserInfoEntity(@Param("username") String username);

    /**
     * 根据id逻辑删除用户信息
     *
     * @param id
     * @return void
     */
    @Modifying
    @Transactional
    @Query(value = "update UserInfoEntity set isDelete = 1 where id = :id")
    void deleteUserInfoEntity(Long id);
}