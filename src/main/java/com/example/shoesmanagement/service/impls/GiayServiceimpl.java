package com.example.shoesmanagement.service.impls;

import com.example.shoesmanagement.model.ChatLieu;
import com.example.shoesmanagement.model.Giay;
import com.example.shoesmanagement.model.Hang;
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
public class GiayServiceimpl implements GiayService {

    @Autowired
    private GiayRepository giayRepository;

    @Autowired
    private HangRepository hangRepository;

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public List<Giay> getAllGiay() {
        return giayRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(Giay giay) {
        giayRepository.save(giay);
    }

    @Override
    public void deleteByIdGiay(UUID id) {
        giayRepository.deleteById(id);
    }

    @Override
    public Giay getByIdGiay(UUID id) {
        return giayRepository.findById(id).orElse(null);
    }

    @Override
    public Giay getByName(String name) {
        return giayRepository.findByTenGiay(name);
    }

    @Override
    public List<Giay> fillterGiay(String searchTerm) {
        if ("Mã Giày".equals(searchTerm) && "Tên Giày".equals(searchTerm) && "Hãng".equals(searchTerm) && "Chất Liệu".equals(searchTerm)) {
            return giayRepository.findAll();
        }
        return giayRepository.customSearch(searchTerm);
    }

    @Override
    public void importDataFromExcel(InputStream excelFile) {
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                Giay giay = new Giay();
                giay.setMaGiay(row.getCell(0).getStringCellValue());
                giay.setTenGiay(row.getCell(1).getStringCellValue());

                String hangName = row.getCell(2).getStringCellValue();
                Hang hang = hangRepository.findByTenHang(hangName);
                giay.setHang(hang);

                String chatLieuName = row.getCell(3).getStringCellValue();
                ChatLieu chatLieu = chatLieuRepository.findByTenChatLieu(chatLieuName);
                giay.setChatLieu(chatLieu);

                giay.setMoTa(row.getCell(4).getStringCellValue());
                giay.setTrangThai(1);
                giay.setTgThem(new Date());
                giayRepository.save(giay);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public List<Giay> findByHang(Hang hang) {
        return giayRepository.findByHang(hang);
    }

    @Override
    public List<Giay> findByChatLieu(ChatLieu chatLieu) {
        return giayRepository.findByChatLieu(chatLieu);
    }

    @Override
    public List<Giay> findByTrangThai(int trangThai) {
        return giayRepository.findByTrangThai(trangThai);
    }
}
