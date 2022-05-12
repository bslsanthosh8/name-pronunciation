package com.wellsfargo.name.pronunciation.service;

import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.model.request.NamePronunciationRequest;

public interface GcpTtsApiService {
    byte[] convertTextToSpeech(NamePronunciationRequest request) throws NamePronunciationException;
}
