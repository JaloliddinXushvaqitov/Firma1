package com.example.firma.Service;

import com.example.firma.DTO.APIResponse;
import com.example.firma.DTO.IshchiDTo;
import com.example.firma.Entite.Ishchi;
import com.example.firma.Entite.Manzil;
import com.example.firma.Repozitary.IshchiRepozitary;
import com.example.firma.Repozitary.ManzilRepozitary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IshchiService {
    @Autowired
    IshchiRepozitary ishchiRepozitary;
    @Autowired
    ManzilRepozitary manzilRepozitary;
    public APIResponse joylash(IshchiDTo ishchiDTo) {
        Ishchi ishchi=new Ishchi();
        ishchi.setIsmi(ishchiDTo.getIsmi());
        ishchi.setTelRaqam(ishchiDTo.getTelRaqam());
        ishchi.setEmail(ishchiDTo.getEmail());

        Manzil manzil=new Manzil();
        manzil.setViloyat(ishchiDTo.getViloyat());
        manzil.setTuman(ishchiDTo.getTuman());
        manzil.setKocha(ishchiDTo.getKocha());
        Manzil save = manzilRepozitary.save(manzil);
        ishchi.setManzil(save);
        ishchiRepozitary.save(ishchi);
        return new APIResponse("Malumot saqlandi",true);
    }

    public APIResponse oqish(IshchiDTo ishchiDTo) {
        List<Ishchi> all=ishchiRepozitary.findAll();
        return new APIResponse(all.toString(),true);
    }

    public APIResponse oqishId(Integer id) {
        Optional<Ishchi> all=ishchiRepozitary.findById(id);
        if(all.isPresent()) return new APIResponse(all.toString(),true);
        return null;
    }

    public APIResponse deleteid(Integer id) {
        Optional<Ishchi>all=ishchiRepozitary.findById(id);
        if(all.isPresent()){
            ishchiRepozitary.deleteById(id);
            manzilRepozitary.deleteById(id);
            return new APIResponse("Malumot ochirildi",true);
        }
        return new APIResponse("Malumot mavjudmas",false);
    }
}
