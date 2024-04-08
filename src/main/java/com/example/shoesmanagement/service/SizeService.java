package com.example.shoesmanagement.service;

import com.example.shoesmanagement.model.Size;

import java.util.List;
import java.util.UUID;

public interface SizeService {
//    @Autowired
//    SizeRepository sizeRepo;
//
//    public List<Size> findAll() {
//        return sizeRepo.findAll();
//    }
//
//    public void insert(Size s) {
//        Date ngayThem = new Date();
//        s.setTgThem(ngayThem);
//        sizeRepo.save(s);
//    }
//
//    public void update(Size s) {
//        Date ngaySua = new Date();
//        s.setTgSua(ngaySua);
//        sizeRepo.save(s);
//    }
//
//    public Size getOne(UUID idSize) {
//        return sizeRepo.findById(idSize).orElse(null);
//    }
//
//    public List<Size> loc(int trangThai) {
//        return sizeRepo.locTrangThai(trangThai);
//    }

    public List<Size> getAllSizeActiveOrderByName();

    public List<Size> getAllSize();

    public void save(Size size);

    public void deleteByIdSize(UUID id);

    public Size getByIdSize(UUID id);

    public List<Size> getByActive();

    public List<Size> filterSizes(Integer selectedSize, String maSize);

//    public void importDataFromExcel(InputStream excelFile);
}
