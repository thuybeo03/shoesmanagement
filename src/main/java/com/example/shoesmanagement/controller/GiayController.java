package com.example.shoesmanagement.controller;

import com.example.shoesmanagement.model.*;
import com.example.shoesmanagement.repository.ChatLieuRepository;
import com.example.shoesmanagement.repository.GiayRepository;
import com.example.shoesmanagement.repository.HangRepository;
import com.example.shoesmanagement.service.*;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/manage")
@Controller
public class GiayController {

    @Autowired
    private GiayService giayService;

    @Autowired
    private HangService hangService;

    @Autowired
    private ChatLieuService chatLieuService;

    @Autowired
    private HinhAnhService hinhAnhService;

    @Autowired
    private HttpSession session;

    @Autowired
    private GiayRepository giayRepository;

    @Autowired
    private HangRepository hangRepository;

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @ModelAttribute("dsTrangThai")
    public Map<Integer, String> getDsTrangThai() {
        Map<Integer, String> dsTrangThai = new HashMap<>();
        dsTrangThai.put(1, "Hoạt Động");
        dsTrangThai.put(0, "Không Hoạt Động");
        return dsTrangThai;
    }

    @GetMapping("/giay")
    public String getAllGiay(Model model, @ModelAttribute("message") String message) {
        List<Giay> listGiay = giayService.getAllGiay();
        List<Hang> listHang = hangService.getALlHang();
        List<ChatLieu> listChatLieu = chatLieuService.getAllChatLieu();
        model.addAttribute("giay", listGiay);
        model.addAttribute("hang", listHang);
        model.addAttribute("chatLieu", listChatLieu);
        if (message == null || "true".equals(message)) {
            model.addAttribute("message", false);
        }
        return "manage/giay";
    }

    @GetMapping("/giay/viewAdd")
    public String viewAddGiay(Model model
            , @ModelAttribute("maGiayError") String maGiayError
            , @ModelAttribute("tenGiayError") String tenGiayError
            , @ModelAttribute("hangError") String hangError
            , @ModelAttribute("chatLieuError") String chatLieuError
            , @ModelAttribute("errorGiay") String errorGiay, @ModelAttribute("userInput") Giay userInputGiay
            , @ModelAttribute("messageHang") String messageHang
            , @ModelAttribute("maHangError") String maHangError
            , @ModelAttribute("tenHangError") String tenHangError
            , @ModelAttribute("errorHang") String errorHang, @ModelAttribute("userInput") Hang userInputHang
            , @ModelAttribute("messageChatLieu") String messageChatLieu
            , @ModelAttribute("maChatLieuError") String maChatLieuError
            , @ModelAttribute("tenChatLieuError") String tenChatLieuError
            , @ModelAttribute("errorChatLieu") String errorChatLieu, @ModelAttribute("userInput") ChatLieu userInputChatLieu
            , @ModelAttribute("Errormessage") String Errormessage
            , @ModelAttribute("ErrormessageHang") String ErrormessageHang
            , @ModelAttribute("ErrormessageChatLieu") String ErrormessageChatLieu) {
        List<Hang> hangList = hangService.getALlHang();
        Collections.sort(hangList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("hang", hangList);
        //
        List<ChatLieu> chatLieuList = chatLieuService.getAllChatLieu();
        Collections.sort(chatLieuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
        model.addAttribute("chatLieu", chatLieuList);
        //
        model.addAttribute("giay", new Giay());
        model.addAttribute("hangAdd", new Hang());
        model.addAttribute("chatLieuAdd", new ChatLieu());
        //
        if (maGiayError == null || !"maGiayError".equals(errorGiay)) {
            model.addAttribute("maGiayError", false);
        }
        if (tenGiayError == null || !"tenGiayError".equals(errorGiay)) {
            model.addAttribute("tenGiayError", false);
        }
        if (hangError == null || !"hangError".equals(errorGiay)) {
            model.addAttribute("hangError", false);
        }
        if (chatLieuError == null || !"chatLieuError".equals(errorGiay)) {
            model.addAttribute("chatLieuError", false);
        }
        if (userInputGiay != null) {
            model.addAttribute("giay", userInputGiay);
        }
        if (messageHang == null || !"true".equals(messageHang)) {
            model.addAttribute("messageHang", false);
        }
        if (maHangError == null || !"maHangError".equals(errorHang)) {
            model.addAttribute("maHangError", false);
        }
        if (tenHangError == null || !"tenHangError".equals(errorHang)) {
            model.addAttribute("tenHangError", false);
        }
        if (userInputHang != null) {
            model.addAttribute("hangAdd", userInputHang);
        }
        if (messageChatLieu == null || !"true".equals(messageChatLieu)) {
            model.addAttribute("messageChatLieu", false);
        }
        if (maChatLieuError == null || !"maChatLieuError".equals(errorChatLieu)) {
            model.addAttribute("maChatLieuError", false);
        }
        if (tenChatLieuError == null || !"tenChatLieuError".equals(errorChatLieu)) {
            model.addAttribute("tenChatLieuError", false);
        }
        if (userInputChatLieu != null) {
            model.addAttribute("chatLieuAdd", userInputChatLieu);
        }
        if (ErrormessageHang == null || !"true".equals(ErrormessageHang)) {
            model.addAttribute("ErrormessageHang", false);
        }
        if (ErrormessageChatLieu == null || !"true".equals(ErrormessageChatLieu)) {
            model.addAttribute("ErrormessageChatLieu", false);
        }
        if (Errormessage == null || !"true".equals(Errormessage)) {
            model.addAttribute("Errormessage", false);
        }
        return "manage/add-giay";
    }

    @PostMapping("/giay/viewAdd/add")
    public String addGiay(@Valid @ModelAttribute("giay") Giay giay, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Hang> hangList = hangService.getALlHang();
            Collections.sort(hangList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("hang", hangList);
            List<ChatLieu> chatLieuList = chatLieuService.getAllChatLieu();
            Collections.sort(chatLieuList, (a, b) -> b.getTgThem().compareTo(a.getTgThem()));
            model.addAttribute("chatLieu", chatLieuList);
            model.addAttribute("giay", new Giay());
            model.addAttribute("hangAdd", new Hang());
            model.addAttribute("chatLieuAdd", new ChatLieu());
            if (bindingResult.hasFieldErrors("maGiay")) {
                redirectAttributes.addFlashAttribute("userInput", giay);
                redirectAttributes.addFlashAttribute("errorGiay", "maGiayError");
            }
            if (bindingResult.hasFieldErrors("tenGiay")) {
                redirectAttributes.addFlashAttribute("userInput", giay);
                redirectAttributes.addFlashAttribute("errorGiay", "tenGiayError");
            }
            if (bindingResult.hasFieldErrors("hang")) {
                redirectAttributes.addFlashAttribute("userInput", giay);
                redirectAttributes.addFlashAttribute("errorGiay", "hangError");
            }
            if (bindingResult.hasFieldErrors("chatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", giay);
                redirectAttributes.addFlashAttribute("errorGiay", "chatLieuError");
            }
            return "redirect:/manage/giay/viewAdd";
        }
        Giay existingGiay = giayRepository.findByMaGiay(giay.getMaGiay());
        if (existingGiay != null) {
            redirectAttributes.addFlashAttribute("userInput", giay);
            redirectAttributes.addFlashAttribute("Errormessage", true);
            return "redirect:/manage/giay/viewAdd";
        }
        Giay giay1 = new Giay();
        giay1.setMaGiay(giay.getMaGiay());
        giay1.setMoTa(giay.getMoTa());
        giay1.setTenGiay(giay.getTenGiay());
        giay1.setTgThem(new Date());
        giay1.setHang(giay.getHang());
        giay1.setChatLieu(giay.getChatLieu());
        giay1.setTrangThai(1);
        giayService.save(giay1);
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/giay";
    }

    @PostMapping("/giay/hang/viewAdd/add")
    public String addHang(@RequestParam("maHang") String maHang,
                          @RequestParam("tenHang") String tenHang,
                          @RequestParam("logoHang") MultipartFile logoHang,
                          @Valid @ModelAttribute("hangAdd") Hang hang, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Hang existingHang = hangRepository.findByMaHang(hang.getMaHang());
        if (existingHang != null) {
            redirectAttributes.addFlashAttribute("userInput", hang);
            redirectAttributes.addFlashAttribute("ErrormessageHang", true);
            return "redirect:/manage/giay/viewAdd";
        }
        Path path = Paths.get("src/main/resources/static/images/logoBrands/");
        Hang hang1 = new Hang();
        hang1.setMaHang(maHang);
        hang1.setTenHang(tenHang);
        if (logoHang.isEmpty()) {
            return "redirect:/manage/hang";
        }
        try {
            InputStream inputStream = logoHang.getInputStream();
            Files.copy(inputStream, path.resolve(logoHang.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            hang1.setLogoHang(logoHang.getOriginalFilename().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hang1.setTgThem(new Date());
        hang1.setTrangThai(1);
        hangService.save(hang1);
        redirectAttributes.addFlashAttribute("messageHang", true);
        return "redirect:/manage/giay/viewAdd";
    }

    @PostMapping("/giay/chat-lieu/viewAdd/add")
    public String addChatLieu(@Valid @ModelAttribute("chatLieuAdd") ChatLieu chatLieu, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasFieldErrors("maChatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", chatLieu);
                redirectAttributes.addFlashAttribute("errorChatLieu", "maChatLieuError");
            }
            if (bindingResult.hasFieldErrors("tenChatLieu")) {
                redirectAttributes.addFlashAttribute("userInput", chatLieu);
                redirectAttributes.addFlashAttribute("errorChatLieu", "tenChatLieuError");
            }
            return "redirect:/manage/giay/viewAdd";
        }
        //
        ChatLieu existingChatLieu = chatLieuRepository.findByMaChatLieu(chatLieu.getMaChatLieu());
        if (existingChatLieu != null) {
            redirectAttributes.addFlashAttribute("userInput", chatLieu);
            redirectAttributes.addFlashAttribute("ErrormessageChatLieu", true);
            return "redirect:/manage/giay/viewAdd";
        }
        //
        ChatLieu chatLieu1 = new ChatLieu();
        chatLieu1.setMaChatLieu(chatLieu.getMaChatLieu());
        chatLieu1.setTenChatLieu(chatLieu.getTenChatLieu());
        chatLieu1.setTgThem(new Date());
        chatLieu1.setTrangThai(1);
        chatLieuService.save(chatLieu1);
        redirectAttributes.addFlashAttribute("messageChatLieu", true);
        return "redirect:/manage/giay/viewAdd";
    }


    @GetMapping("/giay/filter")
    public String searchGiay(Model model, @RequestParam(name = "searchTerm") String searchTerm) {
        List<Giay> filteredGiays;
        if ("Mã Giày".equals(searchTerm) && "Tên Giày".equals(searchTerm) && "Hãng".equals(searchTerm) && "Chất Liệu".equals(searchTerm)) {
            filteredGiays = giayService.getAllGiay();
        } else {
            filteredGiays = giayService.fillterGiay(searchTerm);
        }
        model.addAttribute("giay", filteredGiays);
        model.addAttribute("giayAll", giayService.getAllGiay());
        return "manage/giay";
    }

    @PostMapping("/giay/import")
    public String importData(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file != null && !file.isEmpty()) {
            try {
                InputStream excelFile = file.getInputStream();
                giayService.importDataFromExcel(excelFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:/manage/giay";
    }


}
