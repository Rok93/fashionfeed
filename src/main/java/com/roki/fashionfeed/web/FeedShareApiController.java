package com.roki.fashionfeed.web;

import com.roki.fashionfeed.config.auth.LoginUser;
import com.roki.fashionfeed.config.auth.dto.SessionUser;
import com.roki.fashionfeed.service.ShareService;
import com.roki.fashionfeed.service.UserService;
import com.roki.fashionfeed.web.dto.ShareSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedShareApiController {
    private final ShareService shareService;
    private final UserService userService;

    @PutMapping("/api/{feedId}/shares") //todo: 마찬가지로 feeds/{feedsId} 되야할 듯!
    public Long save(@PathVariable Long feedId, @LoginUser SessionUser user) {
        if (user != null) {
            Long userId = userService.findUserIdByEmail(user.getEmail());
            return shareService.save(feedId, new ShareSaveRequestDto(userId));
        }
        // 유저 아이디가 없는 경우 발생
        return 0L;
    }

    @DeleteMapping("/api/{feedId}/shares")
    public Long delete(@PathVariable Long feedId, @LoginUser SessionUser user) {
        if (user != null) {
            Long userId = userService.findUserIdByEmail(user.getEmail());
            shareService.delete(feedId, userId);
            return userId;
        }

        return -1L;
    }
}
