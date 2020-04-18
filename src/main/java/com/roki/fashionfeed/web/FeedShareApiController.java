package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.ShareService;
import com.roki.fashionfeed.web.dto.LikefullSaveRequestDto;
import com.roki.fashionfeed.web.dto.ShareSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class FeedShareApiController {
    private final ShareService shareService;

    @PutMapping("/api/{feedId}/shares")
    public Long save(@PathVariable Long feedId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object objUserId = session.getAttribute("sessionUser");
        if (objUserId != null) {
            Long userId = (long) (int) objUserId;
            return shareService.save(feedId, new ShareSaveRequestDto(userId));
        }
        // 유저 아이디가 없는 경우 발생
        return 0L;
    }

    @DeleteMapping("/api/{feedId}/shares")
    public Long delete(@PathVariable Long feedId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (long) (int) session.getAttribute("sessionUser");
        shareService.delete(feedId, userId);
        return userId;
    }
}
