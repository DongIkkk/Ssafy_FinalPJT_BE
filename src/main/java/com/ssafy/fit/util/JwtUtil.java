package com.ssafy.fit.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SALT = "SSAFIT";

    // 토큰 생성
    public String createToken(String claimId, String data) throws UnsupportedEncodingException {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .claim(claimId, data)
                .setExpiration(new Date(System.currentTimeMillis() + 1500000))
//                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes("UTF-8"))
                .compact();
    }

    // 유효성 검사
    public void valid(String token) throws Exception {
        Jwts.parser().setSigningKey("SSAFIT".getBytes("UTF-8")).parseClaimsJws(token);
    }

    //토큰에서 원하는 값 가져오기
    public String getUserIdAtToken(String token) throws Exception{
        // 디코더 객체 생성
        Base64.Decoder decoder = Base64.getDecoder();

        // 토큰에서 payload 부분 추출
        final String[] splitJwt = token.split("\\.");
        // payload decoding
        final String payloadStr = new String(decoder.decode(splitJwt[1].getBytes()));
        // "{"id":"dongthu","exp":"waefhi"}"
        // decoding 된 문자열부분 JSON으로 변환
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(payloadStr);

        return (String)jsonObject.get("id");

    }


}
