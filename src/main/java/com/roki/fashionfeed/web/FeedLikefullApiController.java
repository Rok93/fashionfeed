package com.roki.fashionfeed.web;

import com.roki.fashionfeed.config.auth.LoginUser;
import com.roki.fashionfeed.config.auth.dto.SessionUser;
import com.roki.fashionfeed.service.LikefullService;
import com.roki.fashionfeed.service.UserService;
import com.roki.fashionfeed.web.dto.LikefullSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedLikefullApiController {
    private final LikefullService likefullService;
    private final UserService userService;

    @PutMapping("/api/{feedId}/likes")
    public Long save(@PathVariable Long feedId, @LoginUser SessionUser user) {
        if (user != null) {
            Long userId = userService.findUserIdByEmail(user.getEmail());
            return likefullService.save(feedId, new LikefullSaveRequestDto(userId));
        }
        return 0L;
    }

    @DeleteMapping("/api/{feedId}/likes") //todo: feeds 가 {feedId} 앞에 들어가야하지 않을까?
    public void delete(@PathVariable Long feedId, @LoginUser SessionUser user) {
        if (user != null) {
            Long userId = userService.findUserIdByEmail(user.getEmail());
            likefullService.delete(feedId, userId);
        }
    }
}
