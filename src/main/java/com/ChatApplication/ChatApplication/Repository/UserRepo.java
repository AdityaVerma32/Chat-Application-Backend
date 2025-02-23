package com.ChatApplication.ChatApplication.Repository;

import com.ChatApplication.ChatApplication.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Integer> {
    UserModel findByEmail(String email);

    List<UserModel> findByEmailNot(String email);
}
