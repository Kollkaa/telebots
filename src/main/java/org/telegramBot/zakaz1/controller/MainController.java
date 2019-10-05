package org.telegramBot.zakaz1.controller;


import org.telegramBot.zakaz1.domain.Message;
import org.telegramBot.zakaz1.domain.User;
import org.telegramBot.zakaz1.repos.MessageRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;





    @GetMapping("/")
    public String greeting(Map<String, Object> model) throws IOException {


        return "app";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) throws IOException {


        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }




    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model
    ) throws IOException, TelegramApiException {
        Message message = new Message(text, tag, user);

       /* for (User us:userRepo.findAll())
        {
            if (us.isAdmin())
            {
                bot.execute(new SendMessage().setChatId(us.getChat_id()).setText(text));
                System.out.println(us.getUsername());
            }
        }
*/

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "redirect:/main";
    }
}