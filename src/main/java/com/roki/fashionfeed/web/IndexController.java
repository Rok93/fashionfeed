package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final FeedService feedService;

    @GetMapping("/")
    public String index(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<FeedResponseDto> feedPage = feedService.findAll(pageable);
        model.addAttribute("feedPage", feedPage);
        return "index";
    }
}
