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
        String msg = "<div style='font-family: Arial, sans-serif; color: #333; max-width: 600px; margin: auto;'>"
                + "<div style='background-color: #f7f7f7; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);'>"
                + "<h2 style='color: #12A0DCFF; text-align: center;'>Welcome, " + user.getFullname().split(" ")[0] + "!</h2>"
                +"<hr style='border: 0; border-top: 3px solid #12A0DCFF; margin: 20px 0;'>"
                + "<p style='font-size: 16px; color: #333;line-height: 1.5;'>Thank you for registering with MailSender. We are excited to have you on board.</p>"
                + "<p style='font-size: 16px; line-height: 1.5;'>Your username is: <strong>" + user.getUserName() + "</strong></p>"
                + "<p style='font-size: 16px; color: #333;line-height: 1.5;'>Please use this username to log in.</p>"
                + "<p style='font-size: 16px; color: #333;line-height: 1.5; margin-top: 20px;'>Best regards,<br>The MailSender Team</p>"
                + "<hr style='border: 0; border-top: 1px solid #ccc; margin: 20px 0;'>"
                + "<p style='font-size: 12px; color: #333; text-align: center;'>If you did not register for this account, please ignore this email.</p>"
                + "</div>"
                + "</div>";

        Mail mail = new Mail(
                user.getEmail(),
                "Welcome to MailSender",
                msg
        );

        try {
            mailService.sendRegistrationMail(mail);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        return "terms";
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

    @GetMapping("/forget")
    public String forgetPassword(Model model){
        model.addAttribute("newUser",new User());
        return "forget";
    }
}
