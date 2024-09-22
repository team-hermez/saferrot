package com.hermez.saferrot.escrow.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NHTransferRequest {

    @JsonProperty("Header")
    private Header header;

    @JsonProperty("Bncd")
    private String bncd;

    @JsonProperty("Acno")
    private String acno;

    @JsonProperty("Tram")
    private String tram;

    @JsonProperty("DractOtlt")
    private String dractOtlt;

    @JsonProperty("MractOtlt")
    private String mractOtlt;

    @Getter
    @Setter
    @ToString
    public static class Header {

        @JsonProperty("ApiNm")
        private String apiNm;

        @JsonProperty("Tsymd")
        private String tsymd;

        @JsonProperty("Trtm")
        private String trtm;

        @JsonProperty("Iscd")
        private String iscd;

        @JsonProperty("FintechApsno")
        private String fintechApsno;

        @JsonProperty("ApiSvcCd")
        private String apiSvcCd;

        @JsonProperty("IsTuno")
        private String isTuno;

        @JsonProperty("AccessToken")
        private String accessToken;
    }
}
