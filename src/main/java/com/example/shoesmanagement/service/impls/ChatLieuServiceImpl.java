package com.example.shoesmanagement.service.impls;

import com.example.shoesmanagement.model.ChatLieu;
import com.example.shoesmanagement.repository.ChatLieuRepository;
import com.example.shoesmanagement.service.ChatLieuService;
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
public class ChatLieuServiceImpl implements ChatLieuService {

    @Autowired
    private ChatLieuRepository chatLieuRepository;


    @Override
    public List<ChatLieu> getAllChatLieu() {
        return chatLieuRepository.findAllByOrderByTgThemDesc();
    }

    @Override
    public void save(ChatLieu chatLieu) {
        chatLieuRepository.save(chatLieu);
    }

    @Override
    public void deleteByIdChatLieu(UUID id) {
        chatLieuRepository.deleteById(id);
    }

    @Override
    public ChatLieu getByIdChatLieu(UUID id) {
        return chatLieuRepository.findById(id).orElse(null);
    }

    @Override
    public List<ChatLieu> fillterChatLieu(String maCl, String tenCl) {
        if ("Mã Chất Liệu".equals(maCl) && "Tên Chất Liệu".equals(tenCl)) {
            return chatLieuRepository.findAll();
        }
        return chatLieuRepository.findByMaChatLieuOrTenChatLieu(maCl, tenCl);
    }

    @Override
    public void importDataFromExcel(InputStream excelFile) {
        try (Workbook workbook = new XSSFWorkbook(excelFile)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setMaChatLieu(row.getCell(0).getStringCellValue());
                chatLieu.setTenChatLieu(row.getCell(1).getStringCellValue());
                chatLieu.setTgThem(new Date());
                chatLieu.setTrangThai(1);
                chatLieuRepository.save(chatLieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
