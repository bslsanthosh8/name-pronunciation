package com.wellsfargo.name.pronunciation.service.impl;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechSettings;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import com.wellsfargo.name.pronunciation.exception.NamePronunciationException;
import com.wellsfargo.name.pronunciation.model.request.NamePronunciationRequest;
import com.wellsfargo.name.pronunciation.model.response.NamePronunciationResponse;
import com.wellsfargo.name.pronunciation.service.GcpTtsApiService;
import com.wellsfargo.name.pronunciation.utils.ErrorType;
import com.wellsfargo.name.pronunciation.utils.Messages;
import com.wellsfargo.name.pronunciation.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class GcpTtsApiServiceImpl implements GcpTtsApiService {

    @Autowired
    private CredentialsProvider credentialsProvider;

    @Autowired
    private Messages messages;

    @Override
    public byte[] convertTextToSpeech(NamePronunciationRequest request) throws NamePronunciationException {
        TextToSpeechSettings textToSpeechSettings = null;
        TextToSpeechClient textToSpeechClient = null;
        ByteString audioContents = null;
        try {
            textToSpeechSettings = TextToSpeechSettings.newBuilder()
                    .setCredentialsProvider(credentialsProvider).build();

            textToSpeechClient = TextToSpeechClient.create(textToSpeechSettings);

            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(request.getInputText()).build();

            // Build the voice request
            VoiceSelectionParams voice =
                    VoiceSelectionParams.newBuilder()
                            .setLanguageCode(request.getLanguageCode()) // languageCode = "en_us"
                            .setSsmlGender(SsmlVoiceGender.valueOf(request.getVoice())) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
                            .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig =
                    AudioConfig.newBuilder()
                            .setAudioEncoding(AudioEncoding.valueOf(request.getAudioConfig())) // MP3 audio.
                            .build();

            // Perform the text-to-speech request
            SynthesizeSpeechResponse ttsApiresponse =
                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // Get the audio contents from the response
            audioContents = ttsApiresponse.getAudioContent();
            return audioContents.toByteArray();

        } catch (IOException e) {
            log.error("Exception while initiating SpeechClient :" + e.getMessage());
            e.printStackTrace();
            throw new NamePronunciationException(Status.FAILED.name(), ErrorType.SYSTEM.name(), messages.get("np-msg.NPA010"));
        } catch (Exception e) {
            log.error("Exception while calling GCP TTS Api :" + e.getMessage());
            e.printStackTrace();
            throw new NamePronunciationException(Status.FAILED.name(), ErrorType.SYSTEM.name(), messages.get("np-msg.NPA011"));
        }
    }

    private ResponseEntity<NamePronunciationResponse<byte[]>> prepareAndReturnFailureResponse(Exception ex) {
        NamePronunciationResponse<byte[]> response = new NamePronunciationResponse<>();
        response.setStatus(Status.FAILED.name());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
