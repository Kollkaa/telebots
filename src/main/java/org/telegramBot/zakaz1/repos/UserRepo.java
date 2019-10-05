package org.telegramBot.zakaz1.repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
