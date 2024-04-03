package com.example.shoesmanagement.service.impls;

import com.example.shoesmanagement.model.*;
import com.example.shoesmanagement.repository.*;
import com.example.shoesmanagement.service.GiayChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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



    @Override
    public List<ChiTietGiay> getAllChiTietGiay() {
        return null;
    }

    @Override
    public List<ChiTietGiay> getTop4ChiTietGiay() {
        return null;
    }

    @Override
    public void save(ChiTietGiay chiTietGiay) {

    }

    @Override
    public void update(ChiTietGiay chiTietGiay) {

    }

    @Override
    public void deleteByIdChiTietGiay(UUID id) {

    }

    @Override
    public ChiTietGiay getByIdChiTietGiay(UUID id) {
        return null;
    }

    @Override
    public List<ChiTietGiay> getCTGByGiay(Giay giay) {
        return null;
    }

    @Override
    public List<ChiTietGiay> getCTGByGiayActive(Giay giay) {
        return null;
    }

    @Override
    public List<ChiTietGiay> getCTGByGiaySoldOut(Giay giay) {
        return null;
    }

    @Override
    public HinhAnh hinhAnhByGiayAndMau(Giay giay, MauSac mauSac) {
        return null;
    }

    @Override
    public List<ChiTietGiay> fillterCTG(String searchTerm) {
        return null;
    }

    @Override
    public List<ChiTietGiay> fillterGCT(String searchTerm) {
        return null;
    }

    @Override
    public List<ChiTietGiay> findByGiay(Giay giay) {
        return null;
    }

    @Override
    public List<ChiTietGiay> findByMauSac(MauSac mauSac) {
        return null;
    }

    @Override
    public List<ChiTietGiay> findBySize(Size size) {
        return null;
    }

    @Override
    public List<Size> findDistinctSizeByGiayAndMauSac(Giay giay, MauSac mauSac) {
        return null;
    }

    @Override
    public List<ChiTietGiay> findByMauSacAndGiay(MauSac mauSac, Giay giay, int trangThai) {
        return null;
    }

    @Override
    public List<MauSac> findDistinctMauSacByGiay(Giay giay) {
        return null;
    }

    @Override
    public List<ChiTietGiay> findByGiayAndMau(Giay giay, MauSac mauSac) {
        return null;
    }

    @Override
    public ChiTietGiay findByMa(String ma) {
        return null;
    }

    @Override
    public void updatePriceCTGGHCT(ChiTietGiay chiTietGiay) {

    }

    @Override
    public List<ChiTietGiay> isDuplicateChiTietGiay(UUID giayId, UUID sizeId, UUID mauSacId, UUID hinhAnhId) {
        return null;
    }

    @Override
    public void importDataFromExcel(InputStream excelFile) {

    }
}
