package com.example.dataserves.Serves;

import com.example.dataserves.Dto.APIResponse;
import com.example.dataserves.Entite.Dasturchi;
import com.example.dataserves.Repozitary.DasturchiRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DasturchiServes {
    @Autowired
    DasturchiRepozitary dasturchiRepozitary;

    public APIResponse addDasturchi(Dasturchi dasturchi) {
        boolean b = dasturchiRepozitary.existsByEmail(dasturchi.getEmail());
        // APIResponse dagi parametrlarga qiymatni berdik xabar va xolatga;
        if(b) return new APIResponse("Bunday dasturchi royxatdan otgan",false);
        // mavjud bolagani uchun saqladik
        dasturchiRepozitary.save(dasturchi);
        // return orqali ApiResponsga malumot yuboriladi;
        return new APIResponse("Malumot saqlandi",true);
    }

    public APIResponse taxrirDasturchi( Dasturchi dasturchi,Integer id) {
        boolean b = dasturchiRepozitary.existsById(id);
        if(!b) return new APIResponse("Bunday id malumot mavjud emas",false);
        Optional<Dasturchi>taxrir=dasturchiRepozitary.findById(id);
      Dasturchi dasturchi1=taxrir.get();
      dasturchi1.setIsm(dasturchi.getIsm());
      dasturchi1.setFam(dasturchi.getFam());
      dasturchi1.setEmail(dasturchi.getEmail());
      dasturchi1.setMaosh(dasturchi.getMaosh());
      dasturchi1.setLavozim(dasturchi.getLavozim());
      dasturchiRepozitary.save(dasturchi1);
      return new APIResponse("Malumot taxrirlandi",true);
    }


    public APIResponse oqish() {
        List<Dasturchi> all = dasturchiRepozitary.findAll();
        // bunda api dan malumot olamiz
        return new APIResponse(all.toString(),true);
    }

    public APIResponse oqishid(Integer id) {
        Optional<Dasturchi> byId = dasturchiRepozitary.findById(id);
        if(!byId.isPresent()) return new APIResponse("Id malumot mavjudmas",false);
        return new APIResponse(byId.toString(),true);
    }

    public APIResponse delete(Integer id) {
     dasturchiRepozitary.deleteById(id);
     return new APIResponse("O'chirildi",true);

    }
}