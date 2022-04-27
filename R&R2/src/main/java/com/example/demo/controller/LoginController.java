package com.example.demo.controller;

import com.example.demo.configuration.ResponseHandler;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/")
    private String register(Model m){
        try {
            User u=new User();
            m.addAttribute("user",u);
            ResponseHandler.generate("Successfully added data!", HttpStatus.CREATED, u,null);
        } catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.MULTI_STATUS, null,new Error("Error Adding Data"));
        }
        return "registration";
    }

    @RequestMapping("/save")
    private String save(@ModelAttribute("user") User u){
        try {
            userService.save(u);
            ResponseHandler.generate("Successfully added data!", HttpStatus.CREATED, u,null);
        } catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.MULTI_STATUS, null,new Error("Error Adding Data"));
        }

        return "login";
    }
    @RequestMapping("/login")
    public String login(Model m){
        return "login";
    }

    @RequestMapping("/bye")
    public String bye(){
        return "bye";
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("/admin")
    public String admin(){
        try {

            ResponseHandler.generate("Redirected Successfully!", HttpStatus.CREATED,null,null);
        } catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.BAD_REQUEST, null,new Error("Unable to access"));
        }
        return "admin_page";
    }

    @RequestMapping("/admin1")
    public String admin1( Model model){

        try {
            List<Review> reviews= reviewService.find();
            model.addAttribute("list",reviews);
            ResponseHandler.generate("Reviews Got SuccessFully!", HttpStatus.CREATED, reviews,null);
        } catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.BAD_REQUEST, null,new Error("Bad Request"));
        }

        return "admin_page";

    }
    @RequestMapping("/user")
    public String register2(Model m){

        try {
            Review r=new Review();
            m.addAttribute("review",r);
            ResponseHandler.generate("Successfully added data!", HttpStatus.CREATED,r,null);
        } catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.BAD_GATEWAY, null,new Error("Bad Request"));
        }

        return "user_page";
    }


    @RequestMapping("/save2")
    public String save2(@ModelAttribute("review") Review r){
        try {
            reviewService.insert(r);
            ResponseHandler.generate("Successfully added data!", HttpStatus.CREATED,r,null);
        } catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.BAD_REQUEST, null,new Error("Error Adding Data"));
        }

        return "user_response";
    }

    @RequestMapping("/view_average")
    public String admin_2(Model model){
        try {
            model.addAttribute("amb", reviewService.ambienceAvg());
            ResponseHandler.generate(" Ambience Average", HttpStatus.CREATED,reviewService.ambienceAvg(),null);
            model.addAttribute("foo", reviewService.foodAvg());
            ResponseHandler.generate("food Average", HttpStatus.CREATED,reviewService.foodAvg(),null);
            model.addAttribute("cle", reviewService.cleanAvg());
            ResponseHandler.generate("Cleanliness Average", HttpStatus.CREATED,reviewService.cleanAvg(),null);
            model.addAttribute("dri", reviewService.drinksAvg());
            ResponseHandler.generate("Drinks Average", HttpStatus.CREATED,reviewService.drinksAvg(),null);
            model.addAttribute("ser", reviewService.serviceAvg());
            ResponseHandler.generate("Service Average", HttpStatus.CREATED,reviewService.serviceAvg(),null);
            List l=reviewService.overAllAvg();
            model.addAttribute("overall", l);
            ResponseHandler.generate("Overall Average", HttpStatus.CREATED,l,null);

        }catch (Exception e) {
            ResponseHandler.generate(e.getMessage(), HttpStatus.BAD_GATEWAY, null,new Error("Error Computing"));
        }
        return "admin_view";
    }
    @RequestMapping("/logout")
    public String logout(){
        try{
            ResponseHandler.generate("Logged Out",HttpStatus.CREATED,null,null);
        }catch (Exception e){
            ResponseHandler.generate("Error in Logging Out",HttpStatus.BAD_REQUEST,null,new Error("Bad Request"));
        }
        return "registration";
    }

}
