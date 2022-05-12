package com.wellsfargo.name.pronunciation.service.impl;

import com.wellsfargo.name.pronunciation.entity.Employee;
import com.wellsfargo.name.pronunciation.entity.NamePronunciation;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.repository.NamePronunciationRepository;
import com.wellsfargo.name.pronunciation.service.EmployeeService;
import com.wellsfargo.name.pronunciation.service.NamePronunciationService;
import com.wellsfargo.name.pronunciation.utils.ErrorType;
import com.wellsfargo.name.pronunciation.utils.Messages;
import com.wellsfargo.name.pronunciation.utils.SoundSupportFormat;
import com.wellsfargo.name.pronunciation.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NamePronunciationServiceImpl implements NamePronunciationService {
    @Autowired
    NamePronunciationRepository namePronunciationRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    Messages messages;

    @Override
    public byte[] getNamePronunciation(String uid) {

        Optional<NamePronunciation> pronunciation = namePronunciationRepository.findById(uid);
        return pronunciation.isPresent() ? pronunciation.get().getPronunciationSound() : new byte[0];
    }

    @Override
    public void addEmployeeNamePronunciation(String uid, byte[] mp3Sound) throws NamePronunciationException {
        Employee employee = employeeService.getEmployee(uid);
        if (mp3Sound != null && mp3Sound.length < 1) {
            throw new NamePronunciationException(Status.FAILED.name(), ErrorType.SYSTEM.name(), messages.get("np-msg.NPA003"));
        }
        NamePronunciation namePronunciation = new NamePronunciation();
        namePronunciation.setPronunciationSound(mp3Sound);
        namePronunciation.setUid(uid);
        namePronunciation.setFormat(SoundSupportFormat.MP3.name());
        namePronunciationRepository.save(namePronunciation);
    }
}
