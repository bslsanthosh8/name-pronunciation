package com.wellsfargo.name.pronunciation.controller;

import com.wellsfargo.name.pronunciation.entity.NamePronunciation;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.model.request.NamePronunciationRequest;
import com.wellsfargo.name.pronunciation.model.response.NamePronunciationResponse;
import com.wellsfargo.name.pronunciation.repository.NamePronunciationRepository;
import com.wellsfargo.name.pronunciation.service.GcpTtsApiService;
import com.wellsfargo.name.pronunciation.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class NamePronunciationController {

    @Autowired
    NamePronunciationRepository namePronunciationRepository;

    @Autowired
    private GcpTtsApiService gcpTtsApiService;

    @RequestMapping(value = "/NamePronunciation", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<NamePronunciationResponse<String>> addNamePronunciationSound(@RequestPart("file") MultipartFile mp3Data, @RequestPart("data") NamePronunciationRequest namePronunciationRequest) throws IOException {

        NamePronunciationResponse<String> response = new NamePronunciationResponse<>();
        NamePronunciation namePronunciation = NamePronunciationRequest.getNamePronunciation(namePronunciationRequest);
        if (mp3Data != null) {
            byte[] soundData = mp3Data.getBytes();
            namePronunciation.setPronunciationSound(soundData);
            namePronunciationRepository.save(namePronunciation);
            response.setStatus(Status.SUCCESS.name());
            response.setData("");
            response.setMessage("");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setStatus(Status.FAILED.name());
        response.setData("");
        response.setMessage("NPA001:Error occurred while persisting name sound request");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/getNameSound/{uid}")
    public ResponseEntity<byte[]> getNamePronunciation(@PathVariable("uid") String uid) {
        byte[] namePronunciation = namePronunciationRepository.findById(uid).get().getPronunciationSound();
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=voice.mp3").body(namePronunciation);
    }

    @GetMapping("/synthesize")
    public ResponseEntity<NamePronunciationResponse<byte[]>> convertTextToSpeech(@RequestHeader Map<String, String> headers, @Valid @RequestBody NamePronunciationRequest request) throws NamePronunciationException {
        log.info("Convert text to speech started for trace-id ::" + headers.get("trace-id"));
        NamePronunciationResponse<byte[]> response = new NamePronunciationResponse<>();
        byte[] audioContents = gcpTtsApiService.convertTextToSpeech(request);
        response.setMessage(Status.SUCCESS.name());
        response.setData(audioContents);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
