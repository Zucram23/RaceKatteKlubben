package application;

import domain.Cat;
import infrastructure.CatRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements ServiceInterface<Cat> {

    private final CatRepositoryImpl catRepository;

    public CatServiceImpl(CatRepositoryImpl catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public Cat save(Cat cat){
    return catRepository.save(cat);
    }

    @Override
    public List<Cat> findAll(){
        return catRepository.findAll();
    }

    @Override
    public void update(Cat cat){
        catRepository.update(cat);
    }

    @Override
    public void delete(int id){
        catRepository.delete(id);
    }

    public List<Cat> findCatsByOwner(int id){
        return catRepository.findCatsByOwner(id);
    }

    public Cat getCatById(int id){
    return catRepository.getCatById(id);
    }
}
