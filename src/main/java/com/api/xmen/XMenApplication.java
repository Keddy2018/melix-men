package com.api.xmen;

import com.api.xmen.services.MutantService;
import com.api.xmen.services.impl.MutantImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XMenApplication {



    public static void main(String[] args) {

//        SpringApplication.run(XMenApplication.class, args);
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};

//        String[] dna = {
//                "ATGCGA",
//                "CAGTGC",
//                "TTATTT",
//                "TGACGG",
//                "TCGTCA",
//                "TCACTG"};
        MutantService mutante = new MutantImpl();
        if (mutante.isMutant(dna)){
        System.out.println("DNA: OK <-- MUTANTE ENCONTRADO --> Vente pácá MUTANTE");

        }
    }
}
