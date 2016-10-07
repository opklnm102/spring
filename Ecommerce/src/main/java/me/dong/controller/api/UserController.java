package me.dong.controller.api;

import me.dong.exception.UserNameExistException;
import me.dong.exception.UserNotFoundException;
import me.dong.exception.UserPasswordNotMatchedException;
import me.dong.security.Authorities;
import me.dong.model.domain.Authority;
import me.dong.model.domain.User;
import me.dong.model.service.UserService;
import me.dong.util.ParameterUtil;
import me.dong.model.vo.ResponseVO;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     *  1. 아이디 중복체크
     *  2. 패스워드 암호화 저장
     *  3. 유저 Authority 할당
     *  4. 저장
     * @param user 회원가입할 유저 정보를 담은 모델 객체
     * @return 가입완료 response
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public ResponseVO join(User user){
        ParameterUtil.checkParameterEmpty(user.getUserName(), user.getPassword(), user.getName());
        checkUserNameDuplicated(user.getUserName());  //중복검사
        user.setPassword(passwordEncoder.encode(user.getPassword()));  // password 암호화해서 저장

        Authority authority = userService.findByAuthority(Authorities.USER);
        user.getAuthorities().add(authority);
        userService.save(user);

        return ResponseVO.ok();
    }

    private void checkUserNameDuplicated(String userName){
        boolean existUserName = userService.existUserByUserName(userName);
        if(existUserName){
            throw new UserNameExistException();
        }
    }

    /**
     * 로그인
     * @param userName 폼에서 가져온 유저 이름
     * @param password 폼에서 가져온 패스워드
     * @return 로그인완료 response
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseVO login(@RequestParam String userName, @RequestParam String password){

        System.out.println("## UserController Login ##");
        User user = userService.findByUserName(userName);
        if(user == null){  // User존재 여부
            throw new UserNotFoundException();
        }

        if(!passwordEncoder.matches(password, user.getPassword())){  // Password 일치 여부
            throw new UserPasswordNotMatchedException();
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));  //로그인 처리

        return ResponseVO.ok();
    }

    /**
     * 로그아웃
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public ResponseVO logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();  //세션정보 삭제
        }
        SecurityContextHolder.clearContext();  //세션 clear
        return ResponseVO.ok();
    }
}
