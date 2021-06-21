package uz.pdp.online.lesson_7_news_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.online.lesson_7_news_site.entity.enums.Huquq;
import uz.pdp.online.lesson_7_news_site.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lavozim extends AbstractEntity { // ROLE

    @Column(unique = true, nullable = false)//DUBLICATE VA BO'SH BO'LMASLIGI UCHUN
    private String name; //ADMIN, USER, BOSHQALAR

    @Enumerated(EnumType.STRING)
    @ElementCollection()// SHULARNI YIG'ISH UCHUN FOYDALANAMIZ
    private List<Huquq> huquqList; // lavozim_huquq_list degan table ochiladi

    @Column(length = 600)
    private String description;
}
