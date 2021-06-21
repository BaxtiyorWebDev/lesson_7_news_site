package uz.pdp.online.lesson_7_news_site.aop;

import java.lang.annotation.*;

/*ANNOTATSIYA YOZISH UCHUN KERAK BO'LADIGAN ANNOTATSIYA*/
@Documented
@Target(ElementType.METHOD)// qayerda ishlatasan
@Retention(RetentionPolicy.RUNTIME)// qachon ishlashini aytyapmiz
public @interface HuquqniTekshirish {
    String huquq();
}
