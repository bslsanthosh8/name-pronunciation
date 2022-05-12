package com.wellsfargo.name.pronunciation.service;

import com.wellsfargo.name.pronunciation.entity.EmployeeAvatar;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;

public interface EmployeeAvatarService {
    EmployeeAvatar getEmployeeAvatar(String uid) throws NamePronunciationException;

    void addEmployeeEmployeeAvatar(String uid, byte[] mp3Sound) throws NamePronunciationException;

    void deleteEmployeeAvatar(String uid);
}
