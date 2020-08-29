package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import com.roki.fashionfeed.web.dto.FeedSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

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

    @GetMapping("/file-upload")
    public String fileUpload() {
        return "file-test";
    }

    @PostMapping("/upload")
    public String save(FeedSaveRequestDto saveRequestDto) {
        System.out.println("여기 여기 !?");
        System.out.println(saveRequestDto.getFeedTitle());
        System.out.println(saveRequestDto.getFeedContent());

        return "upload-ok";
    }


}
