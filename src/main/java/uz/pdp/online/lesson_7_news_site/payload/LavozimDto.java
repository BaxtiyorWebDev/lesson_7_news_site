package uz.pdp.online.lesson_7_news_site.payload;

import lombok.Data;
import uz.pdp.online.lesson_7_news_site.entity.enums.Huquq;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class LavozimDto {

    @NotBlank// probelni hisobga olmaydi
    private String name;

    private String description;

    @NotEmpty// huquqlarni qo'shish majburiy bo'lsin
    private List<Huquq> huquqList;

}
