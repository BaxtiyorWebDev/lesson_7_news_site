package uz.pdp.online.lesson_7_news_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_7_news_site.entity.Lavozim;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.LavozimDto;
import uz.pdp.online.lesson_7_news_site.repository.LavozimRepos;

import java.util.List;
import java.util.Optional;

@Service
public class LavozimService {

    @Autowired
    private LavozimRepos lavozimRepos;

    public ApiResponse addLavozim(LavozimDto lavozimDto) {
        if (lavozimRepos.existsByName(lavozimDto.getName()))
            return new ApiResponse("Bunday lavozim mavjud",false);
        Lavozim lavozim = new Lavozim(
                lavozimDto.getName(),
                lavozimDto.getHuquqList(),
                lavozimDto.getDescription());
        lavozimRepos.save(lavozim);
        return new ApiResponse("Saqlandi",true);
    }

    public ApiResponse editLavozim(Long id, LavozimDto lavozimDto) {
        boolean existsByNameAndIdNot = lavozimRepos.existsByNameAndIdNot(lavozimDto.getName(), id);
        if (existsByNameAndIdNot)
            return new ApiResponse("Bunday lavozim mavjud",false);
        Optional<Lavozim> optionalLavozim = lavozimRepos.findById(id);
        if (!optionalLavozim.isPresent())
            return new ApiResponse("Bunday lavozim mavjud emas",false);

        Lavozim editingLavozim = optionalLavozim.get();
        editingLavozim.setName(lavozimDto.getName());
        editingLavozim.setDescription(lavozimDto.getDescription());
        editingLavozim.setHuquqList(lavozimDto.getHuquqList());
        lavozimRepos.save(editingLavozim);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Lavozim> getLavozimsList() {
        List<Lavozim> lavozimList = lavozimRepos.findAll();
        return lavozimList;
    }

    public ApiResponse deleteLavozimById(Long id) {
        try {
            lavozimRepos.deleteById(id);
            return new  ApiResponse("Lavozim o'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Lavozim topilmadi",false);
        }
    }
}
