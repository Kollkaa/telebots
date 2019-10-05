package org.telegramBot.zakaz1.repos;




import org.springframework.data.jpa.repository.JpaRepository;
import org.telegramBot.zakaz1.domain.Message;


import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

List<Message> findByTag(String tag);
List<Message> findByText(String text);

}
