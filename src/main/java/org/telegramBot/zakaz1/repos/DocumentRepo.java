package org.telegramBot.zakaz1.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.Document;


public interface DocumentRepo extends JpaRepository<Document,Long> {
    Document findByNameContains(String name);
    Document findByNumber(Integer number);
    Document findByFoto(String foto);
}
