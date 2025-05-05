package com.ucatolica.easyevent.easyevent.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service
public class GoogleAuthService {

    private static final String CLIENT_ID = "451545397871-tnqk93ts6o7dng6iun10iugn4n0fm421.apps.googleusercontent.com";

    @SuppressWarnings("deprecation")
public GoogleIdToken.Payload verifyGoogleToken(String idTokenString) {
    try {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
            .Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
            .setAudience(List.of(CLIENT_ID))
            .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        return (idToken != null) ? idToken.getPayload() : null;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}
