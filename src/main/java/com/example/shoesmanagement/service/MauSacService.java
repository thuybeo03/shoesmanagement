package com.example.shoesmanagement.service;

import com.example.shoesmanagement.model.MauSac;
import com.example.shoesmanagement.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MauSacService {
    @Autowired
    MauSacRepository msRepo;

    public void save(MauSac ms) {
        Date ngayHienTai = new Date();
        //lấy ngày hiện tại
        ms.setTgThem(ngayHienTai);
        msRepo.save(ms);
    }

    public void update(MauSac ms) {
        Date ngaySua = new Date();
        ms.setTgSua(ngaySua);
        msRepo.save(ms);
    }

    public MauSac getOne(UUID idMS) {
        return msRepo.findById(idMS).orElse(null);
    }

    public List<MauSac> findAll() {
        return msRepo.findAll();
    }

    public List<MauSac> loc(int trangThai) {
        return this.msRepo.locTrangThai(trangThai);
    }
}
