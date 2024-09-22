package com.hermez.saferrot.escrow.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NHTransferResponse {
    private Header header;

    @Getter
    @Setter
    public static class Header {
        private String trtm;         // 거래 시간
        private String rsms;         // 응답 메시지 (예: 오류 메시지)
        private String apiNm;        // API 이름
        private String isTuno;       // 거래 고유 번호
        private String tsymd;        // 거래 일자
        private String fintechApsno; // 핀테크 앱 일련번호
        private String iscd;         // 기관 코드
        private String rpcd;         // 응답 코드
        private String apiSvcCd;     // API 서비스 코드
    }
}