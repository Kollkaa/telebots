package org.telegramBot.zakaz1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.City;
import org.telegramBot.zakaz1.domain.Link;

import java.util.List;

public interface LinkRepo extends JpaRepository<Link,Long> {
    List<Link> findAllByNameBut(String name_button);
    Link findByNameBut(String name_button);
    List<Link> findAllByCity(City city);
}
