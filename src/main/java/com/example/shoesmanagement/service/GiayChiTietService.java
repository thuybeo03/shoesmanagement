package com.example.shoesmanagement.service;

import com.example.shoesmanagement.model.*;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface GiayChiTietService {
    public List<ChiTietGiay> getAllChiTietGiay();

    public List<ChiTietGiay> getTop4ChiTietGiay();


}
