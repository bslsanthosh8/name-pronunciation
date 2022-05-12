package com.wellsfargo.name.pronunciation.service;

import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;

public interface NamePronunciationService {

    byte[] getNamePronunciation(String uid);
    void addEmployeeNamePronunciation(String uid,byte[] mp3Sound) throws NamePronunciationException;

}
