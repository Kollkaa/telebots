package org.telegramBot.zakaz1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.TelUser;

public interface TeluUserRepo extends JpaRepository<TelUser,Long> {
    TelUser findByChatid(String chat);
}
