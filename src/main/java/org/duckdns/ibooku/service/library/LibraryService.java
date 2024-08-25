package org.duckdns.ibooku.service.library;

import lombok.RequiredArgsConstructor;
import org.duckdns.ibooku.dto.response.library.LibraryResponseDTO;
import org.duckdns.ibooku.repository.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public List<LibraryResponseDTO> list(double lat, double lon) {
        return libraryRepository.findAll().stream()
                .map(library -> LibraryResponseDTO.builder()
                        .id(library.getId())
                        .name(library.getName())
                        .address(library.getAddress())
                        .content(library.getContent())
                        .telephone(library.getTelephone())
                        .website(library.getWebsite())
                        .lat(library.getLat())
                        .lon(library.getLon())
                        .build())
                .collect(Collectors.toList());
    }
}
