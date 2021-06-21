package uz.pdp.online.lesson_7_news_site.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import uz.pdp.online.lesson_7_news_site.entity.User;
import uz.pdp.online.lesson_7_news_site.exceptions.ForbiddenException;

@Component
@Aspect// bu o'zimizni annotatsiyaga natija
public class CheckPermissionExecutor { // Huquqnitekshirishni ishga tushiruvchi
   /*qaysi anotatsiya bilan va qachon ishlashini aytadi*/
    @Before(value = "@annotation(huquqniTekshirish)")
    public void checkUserPermissionMyMethod(HuquqniTekshirish huquqniTekshirish) {
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        /*list yoki collectionlarni aylanib chiqish imkonini beradi*/
//        if (user.getAuthorities().stream().filter())
//        huquqniTekshirish.huquq()
        boolean exist = false;
        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.getAuthority().equals(huquqniTekshirish.huquq())) {
                exist = true;
                break;
            }
        }
        if (!exist)
            throw new ForbiddenException(huquqniTekshirish.huquq(),"Ruxsat yo'q");
    }
}
