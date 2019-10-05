package org.telegramBot.zakaz1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.telegramBot.zakaz1.domain.Link;
import org.telegramBot.zakaz1.repos.LinkRepo;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkRepo linkRepo;
    @GetMapping
    public String AllLink(Model model)
    {
        Iterable<Link> links=linkRepo.findAll();
        model.addAttribute("links",links);

        return "link";
    }

    @GetMapping("{link}")
    public String getEditLink(@PathVariable Link link,Model model)
    {

        model.addAttribute("link",link);
        return "linkEditor";
    }


    @PostMapping
    public String editLink(@RequestParam String text,
                           @RequestParam("linkId")Link link)
    {
        link.setTextLink(text);
        linkRepo.save(link);
        return "redirect:/link";
    }



}
