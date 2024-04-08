package com.example.shoesmanagement.service.impl;

import com.example.shoesmanagement.model.Size;
import com.example.shoesmanagement.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {

    @Override
    public List<Size> getAllSizeActiveOrderByName() {
        return null;
    }

    @Override
    public List<Size> getAllSize() {
        return null;
    }

    @Override
    public void save(Size size) {

    }

    @Override
    public void deleteByIdSize(UUID id) {

    }

    @Override
    public Size getByIdSize(UUID id) {
        return null;
    }

    @Override
    public List<Size> getByActive() {
        return null;
    }

    @Override
    public List<Size> filterSizes(Integer selectedSize, String maSize) {
        return null;
    }

//    @Override
//    public void importDataFromExcel(InputStream excelFile) {
//
//    }
}
