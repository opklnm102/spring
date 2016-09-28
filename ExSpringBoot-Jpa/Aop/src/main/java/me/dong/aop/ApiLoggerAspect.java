package me.dong.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ApiLoggerAspect {

    private static final Log log = LogFactory.getLog(ApiLoggerAspect.class);

    /**
     * 적용할 조인포인트(어드바이스로 지정된 부가기능을 적용할 위치 -> 메소드) 선별
     */
    @Pointcut("within(@org.springframework.stereotype.Controller *) " +
            "|| within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerBean(){
    }

    @Pointcut("execution(* me.dong..*.*(..))")
//    @Pointcut("execution(* me.dong..*(..))")
    public void apiMethod(){
    }

    /**
     * 타겟 오브젝트의 메소드가 실행되기 전에 사용되는 어드바이스
     * 메소드 호출방식 제어X,
     * 어떤 메소드가 호출되고 어떤 파라미터를 사용하는지를 참조해서 필요한 부가작업 수행
     * @param joinPoint 타겟
     */
    @Before("controllerBean() && apiMethod()")
    public void beforeMethod(JoinPoint joinPoint){
       String methodName = joinPoint.getSignature().getName();
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        Object[] argValues = joinPoint.getArgs();
        Object[] argNames = codeSignature.getParameterNames();
        StringBuilder param = new StringBuilder();
        for(int i=0; i<argNames.length; i++){
            param.append(String.format("%s=%s", argNames[i], argValues[i]));
            if(i <= argNames.length - 1){
                param.append(",");
            }
        }
        log.info(String.format("[API}[%s] Start. request: %s", methodName, param.toString()));
    }

    /**
     * 타켓 오브젝트의 메소드가 정상적으로 실행을 마친뒤 실행
     * @param staticPart 타겟 오브젝트의 메소드
     * @param ret 리턴 값
     */
    @AfterReturning(pointcut = "controllerBean() && apiMethod()", returning = "ret")
    public void afterMethod(JoinPoint.StaticPart staticPart, Object ret){
        String methodName = staticPart.getSignature().getName();
        log.info(String.format("[API][%s] END. response: %s", methodName, ret));
    }

    /**
     * 타겟 오브젝트의 메소드가 호출되는 전과정을 모두 담을 수 있는 가장 강력한 기능을 가진 어드바이스
     * 타겟오브젝트의 메소드를 여러번 호출하거나 호출 파라미터를 바꿔치기 하거나, 호출하지 않도록 하는 것이 가능
     * @param pjp 타겟
     * @return
     * @throws Throwable 예외
     */
    @Around(value = "controllerBean() && apiMethod()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable{

        //타겟 호출 전
        String methodName = pjp.getSignature().getName();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = pjp.proceed();  //타겟 호출

        //타겟 호출 후
        stopWatch.stop();
        log.info(String.format("[API][%s] elasped time: %s secs", methodName, stopWatch.getTotalTimeSeconds()));
        return result;
    }
}
