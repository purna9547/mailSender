package com.purna.mailsender.Controller;

import com.purna.mailsender.Model.Mail;
import com.purna.mailsender.Model.MailSender;
import com.purna.mailsender.Model.User;

import com.purna.mailsender.Services.MailSenderService;
import com.purna.mailsender.Services.MailService;
import com.purna.mailsender.Services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private MailSenderService mailSenderService;
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("newUser",new User());
        return "login";
    }

    @GetMapping("/")
    public String register(Model model){
        model.addAttribute("newUser",new User());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView getRegister(@ModelAttribute User user){
        Map<String, User> mp=new HashMap<>();
        mp.put("newUser", new User());
        userService.registerNewUser(user);
        Mail mail= new Mail(
            user.getEmail(),
            "Welcome to MAILSENDER",
                "Dear, "+user.getUserName()+"\nThank you for registration here" +
                        "\nYour username is: "+user.getUserName()+"\nLogin using this user name"
        );
        mailService.sendRegistrationMail(mail);
        return new ModelAndView(new RedirectView("/login"));

    }
    @GetMapping("/mail")
    public String sendMail(Model model){
        model.addAttribute("newUser", new MailSender());
        return "mail";
    }
    @GetMapping("/terms")
    public String termsAndCondition(Model model){
        model.addAttribute("nerUser", new User());
        return "mail";
    }

    @PostMapping("/mail")
    public String sendEmail(@ModelAttribute MailSender mailSender){
        MailSender mailSender1=new MailSender(
            mailSender.getRecipient(),
            mailSender.getSubject(),
            mailSender.getMessage()
        );

        try {
            mailSenderService.sendMail(mailSender1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/mail";
    }
}
