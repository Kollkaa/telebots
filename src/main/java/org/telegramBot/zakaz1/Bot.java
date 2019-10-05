package org.telegramBot.zakaz1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegramBot.zakaz1.domain.Document;
import org.telegramBot.zakaz1.domain.Link;
import org.telegramBot.zakaz1.domain.TelUser;
import org.telegramBot.zakaz1.repos.DocumentRepo;
import org.telegramBot.zakaz1.repos.LinkRepo;
import org.telegramBot.zakaz1.repos.TeluUserRepo;

import javax.annotation.PostConstruct;
import java.io.*;

import java.util.*;
@Service
public class Bot extends TelegramLongPollingBot {
    boolean add=true;
    String chatadmin="-361379869";
    String chatadmin2="-365050746";
   boolean shutdown=false;
   boolean shut=false;
   boolean flag=false;
    Map<String, TelUser> users=new HashMap<>();
    //List<String >rassilka=new ArrayList<>();
    Set<String> rassilka = new HashSet<String>();
    List<String>listNickname=new ArrayList<>();
    TelUser telUser;
    String support_id="516538254";//"314254027";
    int count=0;
    @Autowired
    private DocumentRepo documentRepo;
    @Autowired
    private LinkRepo linkRepo;
    @Autowired
    private TeluUserRepo teluUserRepo;
        @PostConstruct
        public void construct() throws IOException {

          TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

          try {
                telegramBotsApi.registerBot(this);
          } catch (TelegramApiRequestException e) {
             e.printStackTrace();
         }
            List<Document>documents=new ArrayList<>();
            Document document1=new Document("Приглашение Воеводское ( Виза )","Приглашение воеводское и полугодовое\n" +
                    "\n" +
                    "\uD83D\uDCDDПриглашение воеводское на год\n" +
                    "⏳Срок изготовления 30-45 дней","Cписок необходимых документов: \n" +
                    "- все заполненные страницы паспорта","doc1.jpg",1);
            documents.add(document1);

            Document document2=new Document("Полугодовое приглашение","\uD83D\uDCDDПриглашение полугодовые\n" +
                    "⏳Срок изготовления 8-10 дней\n" +
                    "\uD83D\uDCB5Цена: 350 zł (Украина, Росиия, Белларусь )","Cписок необходимых документов: \n" +
                    "- все заполненные страницы паспорта","doc2.jpg",2);
            documents.add(document2);
            Document document3=new Document("Комплект документов на карту побыту","Комплект Документов на карту побыту\n" +
                    " \uD83D\uDDC2(залончник, умова, КРС)\n" +
                    "\n" +
                    "⏳Время ожидания 1 час\n" +
                    "\uD83D\uDCB5Цена: 500 zl","Cписок необходимых документов: По указанию менеджера","doc3.jpg",3);
            documents.add(document3);
            Document document4=new Document("Карта побыту рабочая(с внеском)","Карта побыту рабочая / студенческая\n" +
                    "любая страна + полный пакет документов (оплата внеска входит)\n" +
                    "\n" +
                    "⏳Время ожидания 8-12 месяцев\n" +
                    "\uD83D\uDCB5Цена: 2800 zl","Cписок необходимых документов: По индивидуальной записи","doc4.jpg",4);
            documents.add(document4);
            Document document5=new Document("Карта побытку рабочая(без внеска)","Карта побыту рабочая / студенческая\n" +
                    "любая страна + полный пакет документов (оплата внеска входит)\n" +
                    "\n" +
                    "⏳Время ожидания 30-45 дней\n" +
                    "\uD83D\uDCB5Цена: 1900 zl","первая страница паспорта \n" +
                    "-  либо виза, либо карта","doc5.jpg",5);
            documents.add(document5);
            Document document6=new Document("Мельдунок","Мельдунок (1 мес) + ПЕСЕЛЬ\n" +
                    "\n" +
                    "⏳Время ожидания 1 час\n" +
                    "\uD83D\uDCB5Цена: 200 zl  \n" +
                    "каждый след. месяц + 100 zl","первая страница паспорта \n" +
                    "-  либо виза, либо карта","doc6.jpg",6);
            documents.add(document6);
            Document document7=new Document("Умовы найму","Умовы найму\n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: 200 zl","Cписок необходимых документов: \n" +
                    "- фото первой страницы паспорта \n" +
                    "- с какого числа","doc7.jpg",7);
            documents.add(document7);
            Document document8=new Document("Wstepne","Wstępne (powyżej 3 m) \n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: 100 zl","Cписок необходимых документов: \n" +
                    "- фото паспорта \n" +
                    "- место жительства в Варшаве\n" +
                    "При необходиомости: \n" +
                    "- название фирмы \n" +
                    "- адрес фирмы -\n" +
                    "- должность\n" +
                    "- с какого числа","doc8.jpg",8);
            documents.add(document8);
            Document document9=new Document("Сан-эпид","медкнижка SANEPID \n" +
                    "+ к ней orzeczenie lekarskie \n" +
                    "+ анализы \n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: 120 zl","Cписок необходимых документов: \n" +
                    "- фото паспорта \n" +
                    "- место жительства в Варшаве\n" +
                    "При необходиомости: \n" +
                    "- название фирмы \n" +
                    "- адрес фирмы -\n" +
                    "- должность\n" +
                    "- с какого числа","doc9.jpg",9);
            documents.add(document9);
            Document document10=new Document("Психотесты для водитилей","Психотесты для водителей\n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: 120 zl","Cписок необходимых документов: \n" +
                    "- фото паспорта \n" +
                    "- место жительства в Варшаве.","doc10.jpg",10);
            documents.add(document10);
            Document document11=new Document("Orzeczenie для водителей","Orzeczenie для водителей\n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: 120 zl","Cписок необходимых документов: \n" +
                    "- фото паспорта \n" +
                    "- место жительства в Варшаве","doc11.jpg",11);
            documents.add(document11);
            Document document12=new Document("Код 95","Код95\n" +
                    "\n" +
                    "⏳Время ожидания до 10 дней\n" +
                    "\uD83D\uDCB5Цена: 1300 zl","Cписок необходимых документов:\n" +
                    "- фото паспорта\n" +
                    "- место жительства в Варшаве\n" +
                    "- фото прав(две стороны)","doc12.jpg",12);
            documents.add(document12);
            Document document13=new Document("Получение банковского кредита","Помощь в получение банковского кредита\n" +
                    "\n" +
                    "⏳Время ожидания (по ситуации)\n" +
                    "\uD83D\uDCB5Цена: индивидуально","Список необходимых документов:\n" +
                    "-  номер паспорта","doc13.jpg",13);
            documents.add(document13);
            Document document14=new Document("Выписка из банка","Выписка из банка\n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: 150 zl","Cписок необходимых документов: по указанию менеджера.","doc14.jpg",14);
            documents.add(document14);
            Document document15=new Document("Страховка авто/человек","Страховка авто/человек\n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: ндивидуально","Cписок необходимых документов:  \n" +
                    "- фото паспорта \n" +
                    "- место жительства В Варшаве \n" +
                    "- дата с какого по какое \n" +
                    "- фото техпаспорта (если машина)","doc15.jpg",15);
            documents.add(document15);
            Document document16=new Document("Справка о несудимости","Страховка авто/человек\n" +
                    "\n" +
                    "⏳Время ожидания 1 день\n" +
                    "\uD83D\uDCB5Цена: ндивидуально","Cписок необходимых документов:  По указанию менеджера","doc16.jpg",16);
            documents.add(document16);
            Document document17=new Document("BNP","BNP\n" +
                    "\n" +
                    "⏳Время ожидания индивидуально\n" +
                    "\uD83D\uDCB5Цена: ндивидуально","Cписок необходимых документов:  По указанию менеджера","doc17.jpg",17);
            documents.add(document17);
            Document document18=new Document("NIP/PIT","NIP/PIT\n" +
                    "\n" +
                    "⏳Время ожидания Индивидуально\n" +
                    "\uD83D\uDCB5Цена: Индивидуально","Cписок необходимых документов:  По указанию менеджера","doc18.jpg",18);
            documents.add(document18);
            Document document19=new Document(" Виза в USA \uD83C\uDDFA\uD83C\uDDF8 | England \uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F | Australia \uD83C\uDDE6\uD83C\uDDFA",
                    "Виза в USA \uD83C\uDDFA\uD83C\uDDF8 | England \uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F | Australia \uD83C\uDDE6\uD83C\uDDFA",
                    "Cписок необходимых документов:  По указанию менеджера",
                    "doc19.jpg",19);
            documents.add(document19);
            for (Document doc:documents) {
                if (documentRepo.findByFoto(doc.getFoto()) == null) {
                    documentRepo.save(doc);
                    System.out.println("true");
                }

            }

            List<Link> links=new ArrayList<>();
            Link link1=new Link("Варшава_Обьявление","⁃ Объявления https://t.me/warsaw_chats");
            links.add(link1);
            Link link2=new Link("Варшава_Работа","⁃ Работа https://t.me/Warsawwork");
            links.add(link2);
            Link link3=new Link("Варшава_Ринок","⁃ Рынок  https://t.me/warsaw_shop");
            links.add(link3);
            Link link4=new Link("Варшава_Жилье","⁃ Жилье https://t.me/warsaw_1");
            links.add(link4);
            Link link5=new Link("Варшава_Каталог","⁃ Каталог услуг | Варшава https://t.me/warsaw_poland");
            links.add(link5);
            Link link6=new Link("Варшава_Афиша","https://t.me/warszawweekend");
            links.add(link6);
            Link link7=new Link("Варшава_Знакомства","https://t.me/warsawchat");
            links.add(link7);
            Link link8=new Link("Вроцлав","https://t.me/Wroclaw_poland");
            links.add(link8);
            Link link9=new Link("Познань","https://t.me/Poznan_poland");
            links.add(link9);
            Link link10=new Link("Краков","https://t.me/Krakow_poland");
            links.add(link10);
            for (Link link:links) {
                if (linkRepo.findByNameBut(link.getNameBut()) == null) {
                    linkRepo.save(link);
                    System.out.println("true");
                }

            }


        }
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(documentRepo.findAll().size()+"  ______"+documentRepo.findAll().toArray().length);
        String documents="";
        int count=1;
        for (Document doc:documentRepo.findAll())
        {String r="⃣ ";
            System.out.println(doc.getFoto());
            if (count>9)
            {
                documents+=String.valueOf(count).split("")[0]+r;
                documents+=String.valueOf(count).split("")[1]+r;
            }
            else documents+=count+r;
            documents+=doc.getName()+"\n";
            count++;
        }

        flag=false;
//
        if(update.getMessage().hasText() && update.getMessage().getText().equals("/on")){
            shutdown=false;
        }

        if(shutdown!=true){

        if (update.hasMessage()) {
            System.out.println(update.getMessage().getChatId().toString());
            if (teluUserRepo.findByChatid(update.getMessage().getChatId().toString())!=null)
                telUser=teluUserRepo.findByChatid(update.getMessage().getChatId().toString());
            else
            {
                telUser=new TelUser(update.getMessage().getChatId().toString(),false);
                teluUserRepo.save(telUser);
            }


            if (update.getMessage().getText() != null) {
                String pass=update.getMessage().getText();

                switch (update.getMessage().getText())
                {
//
                    case "/on":
                        shutdown=false;
                        break;
                    case "/off" :
                        shutdown=true;
                        break;
                    case "Заказать":

                        try {

                            sendApiMethod(new SendMessage().setText("Спасибо за форомление, ваш запрос отправлен администратору.\n" + "В скором времени мы свяжемся с вами\uD83D\uDE0A\n").setChatId(telUser.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        try {
                            sendApiMethod(new SendMessage().setChatId(chatadmin2).setText("Заказ от пользователя:" + "@" + update.getMessage().getChat().getUserName() + "\n" + telUser.getType_doc()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;


                    case "/show":
                        String res = "";
                        for (String s : listNickname) {
                            res += "\n" + "@" + s;
                            count += 1;

                        }
                        try {
                            sendApiMethod(new SendMessage().setText("Список пользователей, которые посящали бота: " + res + "\n" + "Общее количество посещений:" + listNickname.size()).setChatId(telUser.getChat_id()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Отправить":
                        for (String path : telUser.getDocument_path()) {
                            try {
                                execute(new SendDocument().setChatId(support_id).setDocument(new File(path)));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case "Назад\uD83D\uDD19":
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете вашу страну"
                                    , 5, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    case "Назад <--":
                        try {
                            sendApiMethod(send_Message_With_Remake("..."
                                    , 6, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }


                    case "/start":
                        if(update.getMessage().getChatId().toString().equals("427806944")) {
                            try {
                                execute(new SendMessage().setText("Привет, Администратор. Все заказы в группе.").setChatId(update.getMessage().getChatId()));
                                System.out.println("Not Size");
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Do:"+ rassilka.size());

                        }
                        else {
                            rassilka.add(update.getMessage().getChatId().toString());
                            rassilka.size();
                            System.out.println(":"+ rassilka.size());

                        }

                        flag=false;
                        listNickname.add(update.getMessage().getChat().getUserName());
                        count += 1;
                        telUser.setAdmin_support(false);
                        try {

                            sendApiMethod(send_Message_With_Remake("Здравствуйте \uD83D\uDC4B\uD83C\uDFFC\n" +
                                            "Poland_inc - это современная автоматизированая платформа, которая облегчит вам жизнь в Польше \uD83C\uDDF5\uD83C\uDDF1\n" +
                                            "Здесь вы сможете ⤵️\n" +
                                            " ⁃ Найти любой документ\n" +
                                            " ⁃ Найти работу или учёбу \n" +
                                            " ⁃ Каталог групп по Польше\n" +
                                            " ⁃ Заказать рекламу или бота для бизнеса !"+'\n'+
                                    "\uD83C\uDD98ВНИМАНИЕ случаи с мошенничеством увеличились ‼️ просим пользоваться услугами только проверенных лиц \uD83D\uDC6E\uD83C\uDFFC\u200D♂️", 1, update.getMessage().getChatId().toString()));

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Помощь❗️":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(new SendMessage().setChatId(telUser.getChat_id()).setText(" ⁃ Как пользоваться ботом ?\n" +
                                    "Здравствуйте, все очень просто !\n" +
                                    " 1. выберите и нажмите на пункт Меню\n" +
                                    " 2. нажмите на документ который вам надо \n" +
                                    " 3. ознакомтесь и нажмите кнопку ЗАКАЗАТЬ\n" +
                                    "4. отправьте нужные данные для оформления документа\n" +
                                    "5.После прохождения этой процедуры, с вами свяжеться наш администратор"));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "\uD83D\uDCD1Документы":
                        telUser.setAdmin_support(false);

                        try {
                            sendApiMethod(send_Message_With_Remake("\uD83D\uDCC2СПИСОК ДОКУМЕНТОВ\n" +
                                    "\n" +
                                    documents,666, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Вернуться  в главное меню↩️":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт ⬇️ ", 1, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Назад↩️":
                        telUser.setAdmin_support(false);

                        try {
                            sendApiMethod(send_Message_With_Remake("\uD83D\uDCC2СПИСОК ДОКУМЕНТОВ\n" +
                                    "\n" +
                                    documents, 666, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;



                    case "1.Украина, Росиия, Белларусь":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("\uD83D\uDCB5Цена: 1000 zł (Украина, Росиия, Белларусь )", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "2.Грузия и все зак. на -АН (страны СНГ бывшего)":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Цена 1100 зл.\n Срок до 40дн", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "3.Любые другие страны":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Цена 1400 зл.\n Срок до 40дн", 33, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "1.Короткий":
                        telUser.setType_doc(TypeDoc.type_1_9_12);
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Любая страна.\n Цена 1300 зл.\n Срок до 10 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "2.Длинный":
                        telUser.setType_doc(TypeDoc.type_1_9_12_1);
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Любая страна.\n Цена 2800 зл.\n Срок до 20 дн.", 777, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;


                    case "К выбору докуметов" :

                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Доступные документы для просмотра информации:"+"\n"+"1.Приглашение воеводское" + "\n" + "2.Полугодовое приглашение" + "\n" + "3.Комплект документов на карту побыту" + "\n" + "4.Карта побыту рабочая(с внеском)" + "\n" + "5.Карта побыту рабочая(без внеска)" + "\n" + "6.Мельдунок" + "\n" + "7.Умовы найму" + "\n" + "8.Wstepne" + "\n" + "9.Cан-эпид" + "\n" + "10.Психотесты для водителей" + "\n" + "11.Orzeczenie для водителей" + "\n" + "12.Код 95" + "\n" + "13.Получение банковского кредита" + "\n" + "14.Выписка из банка" + "\n" + "15.Страховка авто/человек" + "\n" +
                            "Выберете необходимый пункт ⬇️ ", 666, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Сотрудничество\uD83D\uDC64":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете пункт для сотрудничиства\uD83D\uDD3D", 111, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "🚸Навигатор Польша":
                        telUser.setAdmin_support(false);
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете город\uD83D\uDD3D", 222, update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Варшава":
                        try {
                            sendApiMethod(send_Message_With_Remake("\uD83D\uDD3DВыберете необходимый пункт",12345,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Работа":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Работа").getTextLink()
                                    ,333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Обьявления":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Обьявление").getTextLink()
                                    ,333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;

                    case "Рынок":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Ринок").getTextLink()
                                    ,333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Жилье":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Жилье").getTextLink()
                                    ,333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Каталог услуг | Варшава":

                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Каталог").getTextLink()
                                    ,333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Вроцлав":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Вроцлав").getTextLink(),333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Краков":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Краков").getTextLink(),333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;
                    case "Познань":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Познань").getTextLink(),333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();

                        }
                        break;

                    case "\uD83C\uDF10Хочу в Европу | Работа | Учёба":
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт",1710,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Учеба в Польше":
                        try {
                            sendApiMethod(send_Message_With_Remake("Контакт консультатнта по учебе: @job_polandd ",333,update.getMessage().getChatId().toString()));
                            sendApiMethod(new SendMessage().setChatId(chatadmin).setText("Запрос по Учебе"+ "@"+update.getMessage().getChat().getUserName()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Работа в Польше":
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете нужный пункт",23112000,update.getMessage().getChatId().toString()));
                            sendApiMethod(new SendMessage().setChatId(chatadmin2).setText("Запрос по Работе"+ "@"+update.getMessage().getChat().getUserName()+update.getMessage().getContact().getPhoneNumber()+update.getMessage().getChat().getInviteLink()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "test":
                        try {
                            sendApiMethod(send_Message_With_Remake("Test",333,chatadmin));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ℹ️ Сотрудничество | помощь":
                        try {
                            sendApiMethod(send_Message_With_Remake("Выберете необходимый пункт",2311,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Реклама в Польше":
                        try {
                            sendApiMethod(send_Message_With_Remake("Контакт для сотрудничества: @frikok"+"\n"+"https://teletype.in/@lassis/HkDmvNhCN",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Нужен Бот":
                        try {
                            sendApiMethod(send_Message_With_Remake("Контакт для сотрудничества: @frikok",333,update.getMessage().getChatId().toString()));

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Вакансии":
                        try {
                            sendApiMethod(send_Message_With_Remake("https://t.me/praca_polsha",333,update.getMessage().getChatId().toString()));

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Связаться с Менеджером":
                        try {
                            sendApiMethod(send_Message_With_Remake("Менеджер по работе: @job_polandd",333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Афиша Мероприятий":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Афиша").getTextLink(),333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Знакомства":
                        try {
                            sendApiMethod(send_Message_With_Remake(linkRepo.findByNameBut("Варшава_Знакомства").getTextLink(),333,update.getMessage().getChatId().toString()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "/showstat":
                        Random random=new Random();
                        int resqw= random.nextInt(55);
                        try {
                            sendApiMethod(new SendMessage().setText("Количество пользователей: "+resqw).setChatId(update.getMessage().getChatId()));
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        break;




                    default:
                        try {
                            Integer rf=Integer.valueOf(update.getMessage().getText());
                            Document doc=documentRepo.findByNumber(rf);

                            try {
                                try {
                                    execute(new SendPhoto()
                                            .setPhoto(
                                                    new File("src/main/resources/photos/"+doc.getFoto()))
                                            .setChatId(update.getMessage().getChatId()));
                                }catch (Exception r)
                                {}
                                sendApiMethod(send_Message_With_Remake(doc.getManual(),0000,update.getMessage().getChatId().toString()));
                                execute(
                                        new SendMessage().setChatId(update.getMessage().getChatId().toString()).setText(doc.getList_need_document() ));
                                execute(
                                        new SendMessage().setChatId(chatadmin2).setText("Заказ по"+ doc.getName()+"@"+update.getMessage().getChat().getUserName()));
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        }catch (Exception e) { }
                        if(update.getMessage().getChatId().toString().equals("427806944")){
                            for (String r:rassilka){
                                try {
                                    execute(new SendMessage().setChatId(r).setText(update.getMessage().getText()));
                                } catch (TelegramApiException e) {
                                    e.printStackTrace();
                                }
                            }
                        }


                        break;
                }
            }
        }

       }

    }




    public SendMessage send_Message_With_Remake(String text, int type, String chat_id){
        System.out.println("in");
        ReplyKeyboardMarkup keyboard =new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        List<KeyboardRow> rows=new ArrayList<>();
        if (type==1)
        {
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();


            row1.add(new KeyboardButton("\uD83D\uDCD1Документы"));
            row2.add(new KeyboardButton("\uD83C\uDF10Хочу в Европу | Работа | Учёба"));
            row3.add(new KeyboardButton("🚸Навигатор Польша"));
            row4.add(new KeyboardButton("ℹ️ Сотрудничество | помощь"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            //
        }


        if (type==5)
        {
            System.out.println("in block");
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            row1.add(new KeyboardButton("1.Украина, Росиия, Белларусь"));
            row2.add(new KeyboardButton("2.Грузия и все зак. на -АН (страны СНГ бывшего)"));
            row3.add(new KeyboardButton("3.Любые другие страны"));
            row4.add(new KeyboardButton("Назад↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            System.out.println("Complete Reply");

        }

        if (type==7)
        {
            KeyboardRow row1 = new KeyboardRow();
            KeyboardRow row2 = new KeyboardRow();
            KeyboardRow row3 = new KeyboardRow();
            row1.add(new KeyboardButton("1.Короткий"));
            row2.add(new KeyboardButton("2.Длинный"));
            row3.add(new KeyboardButton("Назад↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
        }

        if(type==33)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row2.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            row1.add(new KeyboardButton("Заказать"));
            row3.add(new KeyboardButton("Назад↩️"));

            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
        }
        if(type==333)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Вернуться  в главное меню↩️"));


            rows.add(row1);

        }



        //553
        if(type==777)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Заказать"));
            row3.add(new KeyboardButton("Назад <--"));
            row2.add(new KeyboardButton("Вернуться  в главное меню↩️"));


            rows.add(row1);
            rows.add(row2);

        }
        if(type==666)
        {   int count=1;
            KeyboardRow row1=new KeyboardRow();
            for (Document document:documentRepo.findAll())
            {  if (count%3!=0)
                {
                    KeyboardButton but=new KeyboardButton().setText(String.valueOf(count));
                    row1.add(but);
                }else if (count%3==0)
                {   row1.add(new KeyboardButton().setText(String.valueOf(count)));
                    rows.add(row1);
                    row1=new KeyboardRow();
                }if (count==documentRepo.findAll().size())
                 {rows.add(row1);

                }
            count++;
            System.out.println(count);
            }
            KeyboardRow row8=new KeyboardRow();
            row8.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row8);

        }
        if(type==0000)
        {   KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Заказать"));
            row3.add(new KeyboardButton("К выбору докуметов"));
            row2.add(new KeyboardButton("Вернуться  в главное меню↩️"));


            rows.add(row1);
            rows.add(row2);

        }
        if (type == 111){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            row1.add(new KeyboardButton("Реклама"));
            row2.add(new KeyboardButton("Документы"));
            row3.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);

        }
        if(type == 222){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            row1.add(new KeyboardButton("Варшава"));
            row2.add(new KeyboardButton("Вроцлав"));
            row3.add(new KeyboardButton("Познань"));
            row4.add(new KeyboardButton("Краков"));
            row5.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);


        }
        if(type == 12345){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();
            KeyboardRow row5=new KeyboardRow();
            KeyboardRow row6=new KeyboardRow();
            KeyboardRow row7=new KeyboardRow();
            KeyboardRow row8=new KeyboardRow();
            row1.add(new KeyboardButton("Обьявления"));
            row2.add(new KeyboardButton("Работа"));
            row3.add(new KeyboardButton("Рынок"));
            row4.add(new KeyboardButton("Жилье"));

            row5.add(new KeyboardButton(" Каталог услуг | Варшава "));
            row6.add(new KeyboardButton("Афиша Мероприятий"));
            row7.add(new KeyboardButton("Знакомства"));
            row8.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);
            rows.add(row5);
            rows.add(row6);
            rows.add(row7);
            rows.add(row8);


        }
        if(type == 1710){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();

            row1.add(new KeyboardButton("Учеба в Польше"));
            row2.add(new KeyboardButton("Работа в Польше"));
            row3.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);



        }
        if(type == 2311){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();

            row1.add(new KeyboardButton("Реклама в Польше"));
            row2.add(new KeyboardButton("Нужен Бот"));


            row4.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);



        }
        if(type == 23112000){
            KeyboardRow row1=new KeyboardRow();
            KeyboardRow row2=new KeyboardRow();
            KeyboardRow row3=new KeyboardRow();
            KeyboardRow row4=new KeyboardRow();

            row1.add(new KeyboardButton("Вакансии"));
            row2.add(new KeyboardButton("Связаться с Менеджером"));


            row4.add(new KeyboardButton("Вернуться  в главное меню↩️"));
            rows.add(row1);
            rows.add(row2);
            rows.add(row3);
            rows.add(row4);



        }







        keyboard.setKeyboard(rows);

        return new SendMessage().setChatId(chat_id).setText(text).setReplyMarkup(keyboard);
    }
    public void SendMes(String text,String chat)
    {
        try {
            sendApiMethod(new SendMessage().setText(text).setChatId(chat));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return "@warsaww_bot";///""@Documents_in_Poland_bot;
    }

    @Override
    public String getBotToken() {
        return "827804459:AAEhCYbx6DhbZDsoUroynFmqf2f57yDqzaw";// 808617170:AAF58eibRG7whQZkJAI3ounVnN__2TRbFEo
    }
}
