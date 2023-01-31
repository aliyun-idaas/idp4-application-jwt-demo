/*
 * Copyright (c) 2016 BeiJing JZYT Technology Co. Ltd
 * www.idsmanager.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BeiJing JZYT Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with BeiJing JZYT Technology Co. Ltd.
 */
package com.idsmanager.demo.jwt.web.controller;


import com.idsmanager.demo.jwt.service.SSOConfigService;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigDto;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigPaginated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/sso/config/")
public class SSOConfigController {

    @Autowired
    private SSOConfigService ssoConfigService;

    @RequestMapping(value = "list")
    public String list(Model model, SSOConfigPaginated paginated) {
        paginated = ssoConfigService.loadSSOConfigPaginated(paginated);
        model.addAttribute("paginated", paginated);
        return "sso/sso_config_list";
    }

    @RequestMapping(value = "form/create", method = RequestMethod.GET)
    public String addSSOConfig(Model model) {
        model.addAttribute("formDto", ssoConfigService.loadSSOConfigDto());
        return "sso/sso_config_form";
    }

    @RequestMapping(value = "form/create", method = RequestMethod.POST)
    public String submitSSOConfig(Model model, @ModelAttribute("formDto") @Valid SSOConfigDto formDto, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("formDto", formDto);
            return "sso/sso_config_form";
        }
        ssoConfigService.addSSOConfig(formDto);
        return "redirect:../list";
    }

    @RequestMapping(value = "update_{uuid}", method = RequestMethod.GET)
    public String uodateSSOConfig(Model model, @PathVariable("uuid") String uuid) {
        SSOConfigDto formDto = ssoConfigService.loadSSOConfigDtoByUuid(uuid);
        model.addAttribute("formDto", formDto);
        return "sso/sso_config_edit";
    }

    @RequestMapping(value = "update_{uuid}", method = RequestMethod.POST)
    public String updateSSOConfig(@ModelAttribute("formDto") @Valid SSOConfigDto formDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("formDto", formDto);
            return "sso/sso_config_form";
        }
        ssoConfigService.updateSSOConfig(formDto);
        model.addAttribute("alert", "OK");
        return "redirect:list";
    }

    @RequestMapping(value = "delete_{uuid}", method = RequestMethod.GET)
    public String deleteSSOConfig(@PathVariable("uuid") String uuid, Model model) {
        ssoConfigService.deleteSSOConfig(uuid);
        model.addAttribute("alert", "OK");
        return "redirect:list";
    }
}
