package uz.pdp.online.lesson_7_news_site.entity.template;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.online.lesson_7_news_site.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)// hech kim o'zgartira olmasin
    @CreationTimestamp
    private Timestamp createdAt;


    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;

    @JoinColumn(updatable = false)
    @CreatedBy//LAZY yozilishini sababi maqolani olib kelganda unga tegishli commentlarni chaqirmasak ham olib kelmasligi uchun
    @ManyToOne(fetch = FetchType.LAZY)// bitta maqolani 2-3 kichi yozishi mumkin, kim yozganligi takrorlanishi mumkin
    private User createdBy;

    @LastModifiedBy//LAZY yozilishini sababi maqolani olib kelganda unga tegishli commentlarni chaqirmasak ham olib kelmasligi uchun
    @ManyToOne(fetch = FetchType.LAZY)// bitta maqolani 2-3 kichi yozishi mumkin, kim yozganligi takrorlanishi mumkin
    private User updatedBy;

}
