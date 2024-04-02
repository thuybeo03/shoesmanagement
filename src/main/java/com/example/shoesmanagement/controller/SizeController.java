package com.example.shoesmanagement.controller;

import com.example.shoesmanagement.model.Size;
import com.example.shoesmanagement.repository.SizeRepository;
import com.example.shoesmanagement.service.SizeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manage")
public class SizeController {
    @Autowired
    SizeRepository sRepo;

    @Autowired
    SizeGiayService sSer;


    @GetMapping("/size")
    public String hienThi(Model model) {
        model.addAttribute("size", sRepo.findAll());
        model.addAttribute("sizeAdd", new Size());
        return "manage/size-giay";
    }

    @PostMapping("/size/viewAdd/add")
    public String add(@ModelAttribute("sizeAdd") Size s) {
        sSer.insert(s);
        return "redirect:/manage/size";
    }

    @GetMapping("/size/viewUpdate/{idSize}")
    public String viewUpdate(@PathVariable("idSize") UUID s, Model model) {
        model.addAttribute("size", sSer.getOne(s));
        return "manage/update-size";
    }

    @PostMapping("/size/update/{idSize}")
    public String update(@PathVariable("idSize") UUID idSize, @ModelAttribute("size") Size size) {
        Size s = sSer.getOne(idSize);
        size.setTgThem(s.getTgThem());
        this.sSer.update(size);
        return "redirect:/manage/size";
    }

    @GetMapping("/size/filter")
    public String loc(@RequestParam("trangThai") int trangThai, Model model) {
        System.out.println(trangThai);

        List<Size> lSize = this.sSer.loc(trangThai);
        model.addAttribute("size", lSize);
        model.addAttribute("sizeAdd", new Size());
        return "manage/size-giay";
    }
}
