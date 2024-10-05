package com.purna.mailsender.Reposities;

import com.purna.mailsender.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String userN);
}
