package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FeedApiController {
    private final FeedService feedService;

    @GetMapping("/test") //Feed 20개 저장
    public void testSave(HttpServletResponse response) throws IOException {
        feedService.saveTestFeeds();
        response.sendRedirect("/");
    }

    @GetMapping("/api/feeds")
    public Page<FeedResponseDto> testSave(@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.DESC) Pageable pageable) { //이부분 출력 테스트를 해봐야할 것같다!
        return feedService.findAll(pageable);
    }
}
