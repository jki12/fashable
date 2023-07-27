package Hackerton.fashable.controller;

import Hackerton.fashable.repo.MemoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Conversion {
    @Autowired private MemoryRepo repo;

    @GetMapping("/conversion/{eng}") // rename.
    public String toKorean(@PathVariable("eng") String eng) throws Exception {
        repo.init(); // controller단에서 repo를 초기화 해주는 것이 맞을까?

        return repo.findById(eng);
    }
}
