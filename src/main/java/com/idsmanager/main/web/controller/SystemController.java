package com.idsmanager.main.web.controller;

import com.idsmanager.main.service.LoginPageConfigService;
import com.idsmanager.main.service.dto.loginpage.LoginPageConfigDto;
import com.idsmanager.main.service.dto.loginpage.LoginPageThemeDto;
import com.idsmanager.main.service.dto.loginpage.SPLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feng on 2016/4/5.
 */
@Controller
public class SystemController {


    @Autowired
    private LoginPageConfigService loginPageConfigService;

    @RequestMapping(value = "system/login", method = RequestMethod.GET)
    public String loginConfigDetails(Model model) {
        LoginPageConfigDto pageConfigDto = loginPageConfigService.findLoginPageConfig();
        if (null == pageConfigDto) {
            model.addAttribute("formDto", new LoginPageConfigDto());
        } else {
            model.addAttribute("formDto", pageConfigDto);
        }

        Map<String, String> themes = new HashMap<>();
        List<LoginPageThemeDto> themeList = loginPageConfigService.findAllLoginPageTheme();
        for (LoginPageThemeDto themeDto : themeList) {
            themes.put(themeDto.getThmemId(), themeDto.getName());
        }
        model.addAttribute("themes", themes);
        return "system/login_config";
    }

    @RequestMapping(value = "system/login", method = RequestMethod.POST)
    public String loginConfigForm(@ModelAttribute("formDto") LoginPageConfigDto pageConfigDto, Model model) {
        loginPageConfigService.saveLoginPageConfig(pageConfigDto);
        model.addAttribute("alert", "saveOK");
        return "redirect:/system/login";
    }

    @RequestMapping(value = "system/login/theme/create", method = RequestMethod.GET)
    public String themeForm(Model model) {
        LoginPageThemeDto themeDto = new LoginPageThemeDto();
        model.addAttribute("formDto", themeDto);
        return "system/login_theme_create";
    }

    @RequestMapping(value = "system/login/theme/create", method = RequestMethod.POST)
    public String themeSave(@ModelAttribute("formDto") LoginPageThemeDto pageThemeDto) {
        loginPageConfigService.saveLoginPageTheme(pageThemeDto);
        return "redirect:/system/login";
    }

    @RequestMapping(value = "system/splogin", method = RequestMethod.GET)
    public String sploginConfigDetails(Model model) {
        List<SPLoginDto> spLoginDtos = loginPageConfigService.findSPLoginDto();

        model.addAttribute("spLoginDtos", spLoginDtos);
        return "system/splogin_config";
    }

    @RequestMapping(value = "system/splogin/load", method = RequestMethod.GET)
    @ResponseBody
    public List<SPLoginDto> getSPloginConfig(Model model) {
        List<SPLoginDto> spLoginDtos = loginPageConfigService.findSPLoginDto();
        return spLoginDtos;
    }

    @RequestMapping(value = "system/splogin/creat", method = RequestMethod.GET)
    public String sploginConfigForm(@ModelAttribute("formDto") SPLoginDto formDto, Model model) {

        return "system/splogin_config_form";
    }

    @RequestMapping(value = "system/splogin/creat", method = RequestMethod.POST)
    public String sploginConfigSave(@ModelAttribute("formDto") SPLoginDto formDto, Model model) {
        loginPageConfigService.saveSPLoginConfig(formDto);
        model.addAttribute("alert", "saveOK");
        return "redirect:/system/splogin";
    }

    @RequestMapping(value = "system/splogin/delete_{uuid}", method = RequestMethod.GET)
    public String deleteSPLoginConfig(@PathVariable("uuid") String uuid, Model model) {
        loginPageConfigService.deleteSPLoginConfig(uuid);
        model.addAttribute("alert", "OK");
        return "redirect:/system/splogin";
    }

    @RequestMapping(value = "system/splogin/update_{uuid}", method = RequestMethod.GET)
    public String loadSPLoginConfig(@PathVariable("uuid") String uuid, Model model) {
        SPLoginDto formDto = loginPageConfigService.findSPLoginConfigByuuid(uuid);
        model.addAttribute("formDto", formDto);
        return "system/splogin_config_edit";
    }

    @RequestMapping(value = "system/splogin/update_{uuid}", method = RequestMethod.POST)
    public String updateSPLoginConfig(@ModelAttribute("formDto") SPLoginDto formDto, Model model) {
        loginPageConfigService.updateSPLoginConfig(formDto);
        model.addAttribute("alert", "OK");
        return "redirect:/system/splogin";
    }
}
