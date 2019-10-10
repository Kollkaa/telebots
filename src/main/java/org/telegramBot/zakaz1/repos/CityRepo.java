package org.telegramBot.zakaz1.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.City;

public interface CityRepo extends JpaRepository<City,Long> {
    City findByName(String name);
}
