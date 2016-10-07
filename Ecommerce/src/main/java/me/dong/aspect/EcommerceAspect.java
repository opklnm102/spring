package me.dong.aspect;

import lombok.extern.apachecommons.CommonsLog;
import me.dong.security.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.ServletContext;

@Component
@Aspect
@CommonsLog
public class EcommerceAspect {

    @Autowired
    private ServletContext servletContext;

    /**
     * 적용할 조인포인트(어드바이스로 지정된 부가기능을 적용할 위치 -> 메소드) 선별
     */
    @Pointcut("within(@org.spring.stereotype.Controller *) ||"
    + "within(@org.spring.web.bind.annotation.RestController * )")
    public void controllerBean(){}

    @Pointcut("execution(* me.dong..*.*(..))")
    public void userBean(){}

    /**
     * 타겟 오브젝트의 메소드가 호출되는 전과정을 모두 담을 수 있는 가장 강력한 기능을 가진 어드바이스
     * 타겟오브젝트의 메소드를 여러번 호출하거나 호출 파라미터를 바꿔치기 하거나, 호출하지 않도록 하는 것이 가능
     * @param pjp 타겟
     * @return
     * @throws Throwable 예외
     */
    @Around("controllerBean() && userBean()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {

        //타겟 호출 전
        String userName = null;

        if(SecurityUtils.getCurrentUser() != null){
            userName = SecurityUtils.getCurrentUser().getUserName();
        }

        String methodName = pjp.getSignature().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = pjp.proceed();  //타켓 호출

        //타겟 호출 후
        stopWatch.stop();
        log.info(String.format("[사용자명:%s][%s method time:%s", userName, methodName, stopWatch.getTotalTimeSeconds()));
        return result;
    }
}
