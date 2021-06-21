package uz.pdp.online.lesson_7_news_site.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.online.lesson_7_news_site.entity.enums.Huquq;
import uz.pdp.online.lesson_7_news_site.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {// securityda user sifatida ko'rishi uchun

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)// faqat userni o'zi kerak bo'lganda dbdan olib kelib o'tirmasin
    private Lavozim lavozim;

    private boolean enabled;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Huquq> huquqList = this.lavozim.getHuquqList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Huquq huquq : huquqList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(huquq.name()));
        }
//    for (Huquq huquq : huquqList) {
//        grantedAuthorities.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return huquq.name();// userga huquqlarni beryapmiz
//            }
//        });
//    }
        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, Lavozim lavozim, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.lavozim = lavozim;
        this.enabled = enabled;
    }
}
