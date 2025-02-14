package com.rymcu.forest.web.api.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rymcu.forest.core.result.GlobalResult;
import com.rymcu.forest.core.result.GlobalResultGenerator;
import com.rymcu.forest.core.service.security.annotation.SecurityInterceptor;
import com.rymcu.forest.dto.ChangeEmailDTO;
import com.rymcu.forest.dto.UpdatePasswordDTO;
import com.rymcu.forest.dto.UserInfoDTO;
import com.rymcu.forest.entity.LoginRecord;
import com.rymcu.forest.entity.UserExtend;
import com.rymcu.forest.service.LoginRecordService;
import com.rymcu.forest.service.UserService;
import com.rymcu.forest.util.Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ronger
 */
@RestController
@RequestMapping("/api/v1/user-info")
public class UserInfoController {

    @Resource
    private UserService userService;
    @Resource
    private LoginRecordService loginRecordService;

    @GetMapping("/detail/{idUser}")
    @SecurityInterceptor
    public GlobalResult detail(@PathVariable Integer idUser) {
        Map map = userService.findUserInfo(idUser);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    @GetMapping("/check-nickname")
    @SecurityInterceptor
    public GlobalResult checkNickname(@RequestParam Integer idUser, @RequestParam String nickname) {
        Map map = userService.checkNickname(idUser, nickname);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    @PatchMapping("/update")
    @SecurityInterceptor
    public GlobalResult updateUserInfo(@RequestBody UserInfoDTO user) {
        Map map = userService.updateUserInfo(user);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    @PatchMapping("/update-extend")
    @SecurityInterceptor
    public GlobalResult updateUserExtend(@RequestBody UserExtend userExtend) {
        Map map = userService.updateUserExtend(userExtend);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    @PatchMapping("/update-email")
    @SecurityInterceptor
    public GlobalResult updateEmail(@RequestBody ChangeEmailDTO changeEmailDTO) {
        Map map = userService.updateEmail(changeEmailDTO);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    @PatchMapping("/update-password")
    @SecurityInterceptor
    public GlobalResult updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        Map map = userService.updatePassword(updatePasswordDTO);
        return GlobalResultGenerator.genSuccessResult(map);
    }

    @GetMapping("/login-records")
    @SecurityInterceptor
    public GlobalResult loginRecords(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer rows, @RequestParam Integer idUser) {
        PageHelper.startPage(page, rows);
        List<LoginRecord> list = loginRecordService.findLoginRecordByIdUser(idUser);
        PageInfo<LoginRecord> pageInfo = new PageInfo<>(list);
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("records", pageInfo.getList());
        Map pagination = Utils.getPagination(pageInfo);
        map.put("pagination", pagination);
        return GlobalResultGenerator.genSuccessResult(map);
    }

}
