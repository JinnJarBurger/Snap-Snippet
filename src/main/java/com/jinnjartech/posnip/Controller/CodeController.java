package com.jinnjartech.posnip.Controller;

import com.jinnjartech.posnip.Model.Code;
import com.jinnjartech.posnip.Service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController()
public class CodeController {
    CodeService service;

    @Autowired
    CodeController(CodeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public  ModelAndView getHtmlHome() {
        return new ModelAndView("home");
    }

    @GetMapping("/code/{uuid}")
    public Object getWebCode(@PathVariable String uuid) {
        Code code = service.getCode(uuid);

        if (code == null)
            return new ModelAndView("restricted_error");
        if (code.isRestrictedByViews()) {
            code.setViews(code.getViews() - 1);
            if (code.getViews() < 0) {
                service.removeCode(uuid);
                return new ModelAndView("restricted_error");
            } else
                service.addCode(code);
        }
        if (code.isRestrictedByTime()) {
            if (code.getTime() > 0) {
                code.setTime(code.getTime() - 1);
                if (code.getTime() <= 0) {
                    service.removeCode(uuid);
                    return new ResponseEntity<>("404 Not Found", HttpStatus.NOT_FOUND);
                } else
                    service.addCode(code);
            }
        }

        ModelAndView modelAndView = new ModelAndView("view_code");
        modelAndView.addObject("codeId", code.getUuid());
        modelAndView.addObject("codeCode", code.getCode());
        modelAndView.addObject("codeTime", code.getTime());
        modelAndView.addObject("codeViews", code.getViews());
        modelAndView.addObject("Code", code);
        return modelAndView;
    }

    @GetMapping("/code/new")
    public ModelAndView getHtmlTemplate() {
        return new ModelAndView("new_code");
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
    public ModelAndView getLatestCode() {
        ModelAndView mav = new ModelAndView("list_of_codes");
        mav.addObject("codes", service.getLatest());
        return mav;
    }
}
