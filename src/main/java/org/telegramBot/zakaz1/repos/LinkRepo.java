package org.telegramBot.zakaz1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.Link;

public interface LinkRepo extends JpaRepository<Link,Long> {
    Link findByNameBut(String name_button);
}
