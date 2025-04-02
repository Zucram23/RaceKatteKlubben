package application;

import domain.Cat;
import infrastructure.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat createCat(Cat cat){
    return catRepository.createCat(cat);
    }

    public List<Cat> getAllCats(){
        return catRepository.findAllCats();
    }

    public void updateCat(Cat cat){
        catRepository.updateCat(cat);
    }

    public void deleteCat(Cat cat){
        catRepository.deleteCat(cat.getId());
    }

    public List<Cat> findCatsByOwner(int id){
        return catRepository.findCatsByOwner(id);
    }

}
