package com.jinnjartech.posnip.Controller;

import com.jinnjartech.posnip.Model.Code;
import com.jinnjartech.posnip.Service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code")
public class CodeApiController {
    CodeService service;

    @Autowired
    CodeApiController(CodeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{uuid}", produces = "application/json")
    public Object getJsonCode(@PathVariable String uuid) {
        Code code = service.getCode(uuid);

        if (code != null) {
            if (code.isRestrictedByViews()) {
                code.setViews(code.getViews() - 1);
                if (code.getViews() < 0) {
                    service.removeCode(uuid);
                    return new ResponseEntity<>("404 Not Found", HttpStatus.NOT_FOUND);
                } else
                    service.addCode(code);
            }
            if (code.isRestrictedByTime()) {
                long difference = service.timeElapsed(code);
                if (difference <= 0) {
                    service.removeCode(uuid);
                    return new ResponseEntity<>("404 Not Found", HttpStatus.NOT_FOUND);
                } else {
                    code.setTime(difference);
                    service.addCode(code);
                }
            }
        } else
            return new ResponseEntity<>("404 Not Found", HttpStatus.NOT_FOUND);

        return service.getCode(uuid);
    }

    @GetMapping(value = "/latest", produces = "application/json")
    public List<Code> latestJCodes() {
        return service.getLatest();
    }

    @PostMapping(value = "/new", produces = "application/json", consumes = "application/json")
    public String addJsonCode(@RequestBody Code code) {
        if (code.getViews() > 0)
            code.setRestrictedByViews(true);
        if (code.getTime() > 0)
            code.setRestrictedByTime(true);
        service.addCode(code);
        return "{ \"id\" : \"" + code.getUuid() + "\" }";
    }

    @PutMapping("/update/{uuid}")
    public String updateCode(@PathVariable String uuid, @RequestBody Code code) {
        Code codex = service.getCode(uuid);
        codex.setTime(code.getTime());
        service.addCode(codex);
        return "Updated!";
    }

    @DeleteMapping("/delete/{uuid}")
    public String deleteCode(@PathVariable String uuid) {
        service.removeCode(uuid);
        return "Deleted!";
    }
}
