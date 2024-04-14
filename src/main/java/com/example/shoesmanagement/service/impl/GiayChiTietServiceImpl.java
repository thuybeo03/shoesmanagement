package com.example.shoesmanagement.service.impl;

import com.example.shoesmanagement.model.*;
import com.example.shoesmanagement.repository.*;
import com.example.shoesmanagement.service.GHCTService;
import com.example.shoesmanagement.service.GiayChiTietService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GiayChiTietServiceImpl implements GiayChiTietService {
    @Autowired
    private GiayChiTietRepository giayChiTietRepository;

    @Autowired
    private GHCTService ghctService;


    @Override
    public List<ChiTietGiay> getAllChiTietGiay() {
        return giayChiTietRepository.findAllByOrderByTgThemDesc();    }

    @Override
    public List<ChiTietGiay> getTop4ChiTietGiay() {
        List<ChiTietGiay> allChiTietGiay = giayChiTietRepository.findAllByOrderByTgThemDesc();
        int limit = 4;
        if (allChiTietGiay.size() <= limit) {
            return allChiTietGiay;
        } else {
            return allChiTietGiay.subList(0, limit);
        }
    }

    @Override
    public void save(ChiTietGiay chiTietGiay) {
        giayChiTietRepository.save(chiTietGiay);
    }

    @Override
    public void update(ChiTietGiay chiTietGiay) {
        giayChiTietRepository.save(chiTietGiay);
    }

    @Override
    public void deleteByIdChiTietGiay(UUID id) {
        giayChiTietRepository.deleteById(id);
    }

    @Override
    public ChiTietGiay getByIdChiTietGiay(UUID id) {
        return giayChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChiTietGiay> findByGiay(Giay giay) {
        return giayChiTietRepository.findByGiay(giay);
    }

    @Override
    public List<ChiTietGiay> findByMauSac(MauSac mauSac) {
        return giayChiTietRepository.findByMauSac(mauSac);
    }

    @Override
    public List<ChiTietGiay> findBySize(Size size) {
        return giayChiTietRepository.findBySize(size);
    }

    @Override
    public List<ChiTietGiay> getCTGByGiay(Giay giay) {
        return giayChiTietRepository.findByGiay(giay);
    }

    @Override
    public List<ChiTietGiay> isDuplicateChiTietGiay(UUID giayId, UUID sizeId, UUID mauSacId, UUID hinhAnhId) {
        return giayChiTietRepository.findByGiayAndSizeAndMauSacAndHinhAnh(giayId, sizeId, mauSacId, hinhAnhId);
    }


}
