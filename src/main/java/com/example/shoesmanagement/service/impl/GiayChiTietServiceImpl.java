package com.example.shoesmanagement.service.impl;

import com.example.shoesmanagement.model.*;
import com.example.shoesmanagement.repository.*;
import com.example.shoesmanagement.service.GHCTService;
import com.example.shoesmanagement.service.GiayChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiayChiTietServiceImpl implements GiayChiTietService {
    @Autowired
    private GiayChiTietRepository giayChiTietRepository;

    @Autowired
    private GiayRepository giayRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Autowired
    private HangRepository hangRepository;

    @Autowired
    private GHCTService ghctService;


    @Override
    public List<ChiTietGiay> getAllChiTietGiay() {
        return null;
    }

    @Override
    public List<ChiTietGiay> getTop4ChiTietGiay() {
        return null;
    }

    @Override
    public HinhAnh hinhAnhByGiayAndMau(Giay giay, MauSac mauSac) {
        return giayChiTietRepository.findDistinctByGiay(giay, mauSac);
    }

    @Override
    public List<ChiTietGiay> findByGiayAndMau(Giay giay, MauSac mauSac) {
        return giayChiTietRepository.findByGiayAndMauSac(giay, mauSac);
    }
    @Override
    public List<ChiTietGiay> findByMauSacAndGiay(MauSac mauSac, Giay giay, int trangThai) {
        return giayChiTietRepository.findByMauSacAndGiayAndTrangThai(mauSac, giay, trangThai);
    }


}
