package org.duckdns.ibooku.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.response.library.LibraryResponseDTO;
import org.duckdns.ibooku.service.library.LibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;

    @GetMapping("/list")
    public List<LibraryResponseDTO> list(@RequestParam String isbn, @RequestParam double lat, @RequestParam double lon) {
        return libraryService.list(isbn, lat, lon);
    }

//    @GetMapping("/insert")
//    public String insert() {
//        return libraryService.insert();
//    }
}
