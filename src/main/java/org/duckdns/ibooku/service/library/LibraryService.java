package org.duckdns.ibooku.service.library;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.duckdns.ibooku.dto.response.library.LibraryResponseDTO;
import org.duckdns.ibooku.entity.library.Library;
import org.duckdns.ibooku.repository.LibraryRepository;
import org.duckdns.ibooku.util.HTTPUtils;
import org.duckdns.ibooku.util.JSONUtils;
import org.duckdns.ibooku.util.network.Get;
import org.duckdns.ibooku.util.network.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LibraryService {
    @Value("${api-key.library-bigdata}")
    private String KEY_LIBRARY_BIGDATA;

    private final LibraryRepository libraryRepository;

    public List<LibraryResponseDTO> list(String isbn, double lat, double lon) {


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
                        .isBookExist(false)
                        .distance(1.23)
                        .build())
                .collect(Collectors.toList());
    }

    public String insert() {
        int num = 1;

        for (int page = 1; page <= 146; page++) {
            String url = String.format("https://data4library.kr/api/libSrch?authKey=%s&pageNo=%d&pageSize=10&format=json", KEY_LIBRARY_BIGDATA, page);

            Header header = new Header();
//                .append("User-Agent", HTTPUtils.USER_AGENT)
//                .append("Accept-Language", HTTPUtils.ACCEPT_LANGUAGE)
//                .append("Accept-Encoding", HTTPUtils.ACCEPT_ENCODING)
//                .append("Connection", HTTPUtils.CONNECTION);

            try {
                Get get = new Get(url)
                        .setHeader(header)
                        .execute();

                int responseCode = get.getResponseCode();
                if (responseCode != org.apache.http.HttpStatus.SC_OK) {
                    log.debug("responseCode: {}", responseCode);
                    throw new RuntimeException("통신 오류: " + get.getUrl());
                }

                JsonObject jsonObject = JSONUtils.parse(get.getResult());
                JsonObject response = jsonObject.getAsJsonObject("response");
                JsonArray libs = response.getAsJsonArray("libs");


                for (Object o: libs) {
                    JsonObject obj = (JsonObject) o;
                    JsonObject lib = obj.getAsJsonObject("lib");

                    String name = lib.get("libName").getAsString();
                    String libCode = lib.get("libCode").getAsString();
                    String address = lib.get("address").getAsString();
                    String content = lib.get("operatingTime") == null ? null : lib.get("operatingTime").getAsString();
                    String telephone = lib.get("tel").getAsString();
                    String website = lib.get("homepage").getAsString();
                    Double lat = Double.parseDouble(lib.get("latitude").getAsString());
                    Double lon = Double.parseDouble(lib.get("longitude").getAsString());

                    Library library = Library.builder()
                            .name(name)
                            .libCode(libCode)
                            .address(address)
                            .content(content)
                            .telephone(telephone)
                            .website(website)
                            .lat(lat)
                            .lon(lon)
                            .build();

                    libraryRepository.save(library);

                    System.out.println(num + "/1458 -> 남은개수 : " + (1458-num));
                    num++;
                }
            } catch(Exception e) {
                e.printStackTrace();
                log.error("날씨 조회 중 통신 오류가 발생했습니다.");
                throw new RuntimeException();
            }
        }

        return "success";
    }
}
