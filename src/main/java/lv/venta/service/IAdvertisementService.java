package lv.venta.service;

import lv.venta.model.Advertisement;

import java.util.ArrayList;

public interface IAdvertisementService {
   Advertisement insertNewAdv(Advertisement adv) throws Exception;
    ArrayList<Advertisement> selectAllAdv();
    Advertisement selectAdvById(long id) throws Exception;
    Advertisement deleteAdvById(long id) throws Exception;
    Advertisement updateAdvById(long id, Advertisement adv) throws Exception;
}
