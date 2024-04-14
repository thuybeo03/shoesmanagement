package com.example.shoesmanagement.service;

import com.example.shoesmanagement.model.*;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface GiayChiTietService {
    public List<ChiTietGiay> getAllChiTietGiay();

    public List<ChiTietGiay> getTop4ChiTietGiay();

    List<ChiTietGiay> findByGiayAndMau(Giay giay, MauSac mauSac);

    List<ChiTietGiay> findByMauSacAndGiay(MauSac mauSac, Giay giay, int trangThai);


    HinhAnh hinhAnhByGiayAndMau(Giay giay, MauSac mauSac);

}
