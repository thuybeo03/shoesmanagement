package com.example.shoesmanagement.service.impls;

import com.example.shoesmanagement.model.Size;
import com.example.shoesmanagement.repository.SizeRepository;
import com.example.shoesmanagement.service.SizeService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSizeActiveOrderByName() {
        return sizeRepository.findByTrangThaiOrderByMaSize(1);
    }

    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(Size size) {
        sizeRepository.save(size);
    }

    @Override
    public void deleteByIdSize(UUID id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public Size getByIdSize(UUID id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Size> getByActive() {
        return sizeRepository.findByTrangThai(1);
    }

    @Override
    public List<Size> filterSizes(Integer selectedSize, String maSize) {
        if ("Size".equals(selectedSize) && "Mã Size".equals(maSize)) {
            return sizeRepository.findAll();
        }
        return sizeRepository.findBySoSizeOrMaSize(selectedSize, maSize);
    }

    @Override
    public void importDataFromExcel(InputStream excelFile) {
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Size size = new Size();
                size.setMaSize(row.getCell(0).getStringCellValue());
                if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                    size.setSoSize((int) row.getCell(1).getNumericCellValue());
                }
                size.setTgThem(new Date());
                size.setTrangThai(1);
                sizeRepository.save(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
