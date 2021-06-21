package uz.pdp.online.lesson_7_news_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_7_news_site.aop.HuquqniTekshirish;
import uz.pdp.online.lesson_7_news_site.entity.Lavozim;
import uz.pdp.online.lesson_7_news_site.entity.enums.Huquq;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.LavozimDto;
import uz.pdp.online.lesson_7_news_site.payload.UserDto;
import uz.pdp.online.lesson_7_news_site.service.LavozimService;
import uz.pdp.online.lesson_7_news_site.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {

    @Autowired
    private LavozimService lavozimService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_LAVOZIM')")// bitta bo'lsa hasAuthority()
    @PostMapping
    public HttpEntity<?> addLavozim(@Valid @RequestBody LavozimDto lavozimDto) {
        ApiResponse apiResponse = lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAnyAuthority('EDIT_LAVOZIM')")// bitta bo'lsa hanAuthority()
    @HuquqniTekshirish(huquq = "EDIT_LAVOZIM")
    @PutMapping("/{id}")
    public HttpEntity<?> editLavozim(@PathVariable Long id,
            @Valid @RequestBody LavozimDto lavozimDto) {
        ApiResponse apiResponse = lavozimService.editLavozim(id,lavozimDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('VIEW_LAVOZIMLAR')")
    @GetMapping
    public HttpEntity<?> viewLavozims() {
        List<Lavozim> lavozimsList = lavozimService.getLavozimsList();
        return ResponseEntity.status(lavozimsList!=null?200:409).body(lavozimsList);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_LAVOZIM')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteLavozimById(@PathVariable Long id) {
        ApiResponse apiResponse = lavozimService.deleteLavozimById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
