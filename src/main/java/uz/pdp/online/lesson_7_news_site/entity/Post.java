package uz.pdp.online.lesson_7_news_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.online.lesson_7_news_site.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AbstractEntity {

    @Column(nullable = false,columnDefinition = "text")//default 255 ta edi buni ko'paytirdik
    private String title;

    @Column(nullable = false,columnDefinition = "text")//default 255 ta edi buni ko'paytirdik
    private String text;

    @Column(nullable = false,columnDefinition = "text")//default 255 ta edi buni ko'paytirdik
    private String url;
}
