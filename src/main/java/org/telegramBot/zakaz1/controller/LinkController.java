package org.telegramBot.zakaz1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.telegramBot.zakaz1.domain.City;
import org.telegramBot.zakaz1.domain.Link;
import org.telegramBot.zakaz1.repos.CityRepo;
import org.telegramBot.zakaz1.repos.LinkRepo;

@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkRepo linkRepo;
    @Autowired
    private CityRepo cityRepo;

    @GetMapping
    public String AllLink(Model model)
    {
        Iterable<Link> links=linkRepo.findAll();
        model.addAttribute("links",links);
        model.addAttribute("cities",cityRepo.findAll());
        return "link";
    }

    @GetMapping("{link}")
    public String getEditLink(@PathVariable Link link,Model model)
    {

        model.addAttribute("link",link);
        model.addAttribute("cities",cityRepo.findAll());
        return "linkEditor";
    }
    @PostMapping("/addLink")
    public String addLink(Link link,
                          @RequestParam String links,
                          @RequestParam String name,
                          @RequestParam String cities){
        link=new Link(name,links);
        link.setCity(cityRepo.findByName(cities));
        linkRepo.save(link);

        return "link";
    }

    @PostMapping("/editLink")
    public String editLink(@RequestParam String text,
                           @RequestParam String combo,
                           @RequestParam("linkId")Link link)
    {

        System.out.println(text);
        link.setTextLink(text);
        try {
            System.out.println(combo);
            link.setCity(cityRepo.findByName(combo.trim()));
        }catch (Exception e){e.printStackTrace();}
        linkRepo.save(link);
        return "redirect:/link";
    }



}
