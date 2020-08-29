package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.web.dto.FeedSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FileUploadController {
    private final FeedService feedService;

//    @PostMapping("/upload")
//    public Long save(@RequestBody FeedSaveRequestDto saveRequestDto) {
//        System.out.println("여기 여기 !?");
//        return feedService.save(saveRequestDto).getId();
//    }
}
