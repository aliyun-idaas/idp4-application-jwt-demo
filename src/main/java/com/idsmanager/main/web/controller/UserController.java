package com.idsmanager.main.web.controller;

import com.idsmanager.main.service.UserService;
import com.idsmanager.main.service.dto.user.UserFormDto;
import com.idsmanager.main.service.dto.user.UserPaginated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by IDsManager.Feng on 2016/2/23.
 */
@Controller
@RequestMapping(value = "/user/management/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "list")
    public String list(Model model, UserPaginated paginated) {
        paginated = userService.loadUserPaginated(paginated);
        model.addAttribute("paginated", paginated);
        return "user/user_list";
    }

    @RequestMapping(value = "form/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("formDto", userService.generateUserFormDto());
        return "user/user_form";
    }


    @RequestMapping(value = "form/create", method = RequestMethod.POST)
    public String createForm(Model model, @ModelAttribute("formDto") @Valid UserFormDto dto, BindingResult result) {
        if (result.hasErrors()) {
            UserFormDto formDto = (UserFormDto) result.getTarget();
            model.addAttribute("formDto", formDto);
            return "user/user_form";
        }
        userService.createUser(dto);
        return "redirect:../list";
    }
}
