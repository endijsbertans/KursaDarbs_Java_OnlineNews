package lv.venta.service.impl;

import lv.venta.model.Advertisement;
import lv.venta.repo.IAdvertisementRepo;
import lv.venta.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class AdvertisementServiceImpl implements IAdvertisementService {
    @Autowired
    private IAdvertisementRepo advertisementRepo;
    @Override
    public ArrayList<Advertisement> selectAllAdv() {
        return (ArrayList<Advertisement>) advertisementRepo.findAll();
    }
    @Override
    public Advertisement selectAdvById(long id) throws Exception {
        if(id < 0) throw new Exception("Id should be positive");
        if(advertisementRepo.existsById(id))
        {
            return advertisementRepo.findById(id).get();
        }
        throw new Exception("Adv with " + id + " is not found");
    }
    @Override
    public Advertisement insertNewAdv(Advertisement adv) throws Exception {
        if(adv == null) throw new Exception("Advert is null");
       // personRepo.save(customer.getPerson()); this should be added when person class completes
        Advertisement advFromDB = advertisementRepo.findByTitleAndPriceAndDescription(adv.getTitle(), adv.getPrice(), adv.getDescription());
        if(advFromDB  != null) {
            throw new Exception("advert already exists");
        } else {
            adv.setDate(LocalDateTime.now());
            return advertisementRepo.save(adv);
        }
    }
    @Override
    public Advertisement deleteAdvById(long id) throws Exception {
        Advertisement deleteProduct = selectAdvById(id);
        advertisementRepo.delete(deleteProduct);
        return deleteProduct;
    }

    @Override
    public Advertisement updateAdvById(long id, Advertisement adv) throws Exception {
        Advertisement updateAdv = selectAdvById(id);
        updateAdv.setTitle(adv.getTitle());
        updateAdv.setPrice(adv.getPrice());
        updateAdv.setDescription(adv.getDescription());
        updateAdv.setDate(LocalDateTime.now());
        return advertisementRepo.save(updateAdv);
    }
}
