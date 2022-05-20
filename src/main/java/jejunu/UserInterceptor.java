package jejunu;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // handler 실행해야 할 컨트롤러의 메소드를 감싸서 만든것
        System.out.println("******************** preHandle ********************");
        return true; //true 여야 넘어간다.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 메소드 실행 후 실행
        System.out.println("******************** postHandle ********************");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 화면 렌더링 까지 하고 나서 실행
        System.out.println("******************** afterCompletion ********************");
    }
}
