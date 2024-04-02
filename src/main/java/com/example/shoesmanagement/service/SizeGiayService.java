package com.example.shoesmanagement.service;

import com.example.shoesmanagement.model.Size;
import com.example.shoesmanagement.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SizeGiayService {
    @Autowired
    SizeRepository sizeRepo;

    public List<Size> findAll() {
        return sizeRepo.findAll();
    }

    public void insert(Size s) {
        Date ngayThem = new Date();
        s.setTgThem(ngayThem);
        sizeRepo.save(s);
    }

    public void update(Size s) {
        Date ngaySua = new Date();
        s.setTgSua(ngaySua);
        sizeRepo.save(s);
    }

    public Size getOne(UUID idSize) {
        return sizeRepo.findById(idSize).orElse(null);
    }

    public List<Size> loc(int trangThai) {
        return sizeRepo.locTrangThai(trangThai);
    }
}
