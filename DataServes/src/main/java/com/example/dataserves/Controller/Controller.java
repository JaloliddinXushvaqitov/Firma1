package com.example.dataserves.Controller;

import com.example.dataserves.Dto.APIResponse;
import com.example.dataserves.Entite.Dasturchi;
import com.example.dataserves.Repozitary.DasturchiRepozitary;
import com.example.dataserves.Serves.DasturchiServes;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Dasturchi")
public class Controller {
    @Autowired
    DasturchiServes dasturchiServes;
    @PostMapping("/Joylash")
    // malumot tipini universal xolda berish va list tipini topish uchun ? qoysak avtomatik topadi ozi
    public HttpEntity<?> joylash(@RequestBody Dasturchi dasturchi){
        APIResponse apiResponse=dasturchiServes.addDasturchi(dasturchi);
        // bunda dasturchiServes clasidagi ApiResponse funksiysi ishlaydi va isxolat ga qarab javob qaytaradi
        //apiResponse.isXolat()? bu xolat shart berish if sharti
        //HttpStatus.OK:HttpStatus.ALREADY_REPORTED bu xol else aks xolada
        //apiResponse.getXabar() bolasa javobqa qaytaradi
        return ResponseEntity.status(apiResponse.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @PutMapping("/Taxrirlash/{id}")
    public HttpEntity<?>Taxrirlash( @PathVariable Integer id, @RequestBody Dasturchi dasturchi){
        APIResponse apiResponse=dasturchiServes.taxrirDasturchi(dasturchi,id);
        return ResponseEntity.status(apiResponse.isXolat()? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @GetMapping("/Oqish")
    public HttpEntity<?> oqish(){
        APIResponse apiResponse=dasturchiServes.oqish();
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @GetMapping("/Oqish/{id}")
    public HttpEntity<?> oqishid(@PathVariable Integer id){
        APIResponse apiResponse=dasturchiServes.oqishid(id);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());
    }
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        APIResponse apiResponse=dasturchiServes.delete(id);
        return ResponseEntity.status(apiResponse.isXolat()?200:208).body(apiResponse.getXabar());

    }
}
