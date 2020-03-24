package com.roki.fashionfeed.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PopupController {

    @GetMapping("/share/popup")
    public String popup() {
        return "popup";
    }
}
