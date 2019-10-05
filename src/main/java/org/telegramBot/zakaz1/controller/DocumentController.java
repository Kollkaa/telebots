package org.telegramBot.zakaz1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.telegramBot.zakaz1.domain.Document;
import org.telegramBot.zakaz1.repos.DocumentRepo;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Controller
@RequestMapping("/document")
public class DocumentController {


    @Autowired
    private DocumentRepo documentRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("documents", documentRepo.findAll());

        return "document";
    }

    @GetMapping("{document}")
    public String userEditForm(@PathVariable Document document, Model model) {
        model.addAttribute("document", document);


        return "documentEditor";
    }

    public String write(MultipartFile file) throws IOException {

        String fileName =file.getOriginalFilename();
        String filePath = "src/main/resources/photos" + "/" + fileName;
System.out.println(fileName);
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @PostMapping("/documentAdd")
    public String addUser(Document document,
                          @RequestParam String name,
                          @RequestParam String manual,
                          @RequestParam String list_need_document,
                          @RequestParam("file") MultipartFile file) {
        if (file != null) {

            try { System.out.println("truuuuuuue");
                document = new Document(name, manual, list_need_document, write(file), documentRepo.findAll().toArray().length + 1);
            } catch (IOException e) { System.out.println("falseeeeeeeee");
                e.printStackTrace();
            }

        } else
        {
            System.out.println("nullllll");
        document = new Document(name, manual, list_need_document, "", documentRepo.findAll().toArray().length + 1);
        }

        documentRepo.save(document);

        return "redirect:/document";
    }

    @PostMapping("/documentEditor")
    public String userSave(
            @RequestParam String name,
            @RequestParam String manual,
            @RequestParam String list_need_document,
            @RequestParam("file") MultipartFile file,
            @RequestParam("documentId") Document document
    )  {
        document.setName(name);
        document.setManual(manual);
        document.setList_need_document(list_need_document);
        if (file != null) {

            try { System.out.println("truuuuuuue");
                document.setFoto(write(file));
            } catch (IOException e) { System.out.println("falseeeeeeeee");
                e.printStackTrace();
            }

        } else
        {
            System.out.println("nullllll");
        }

        documentRepo.save(document);

        return "redirect:/document";
    }

}
