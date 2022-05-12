package com.wellsfargo.name.pronunciation.model.request;

import com.wellsfargo.name.pronunciation.entity.NamePronunciation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NamePronunciationRequest {

    String uid;
    String format;
    String createdBy;

    String languageCode = "en-US";
    String voice = "FEMALE";
    String audioConfig = "MP3";
    @NotEmpty(message = "input name text cannot be empty")
    String inputText;

   public static NamePronunciation getNamePronunciation(NamePronunciationRequest namePronunciationRequest) {
        NamePronunciation namePronunciation = new NamePronunciation();
        namePronunciation.setFormat(namePronunciationRequest.getFormat());
        namePronunciation.setUid(namePronunciationRequest.getUid());
        namePronunciation.setCreatedTimestamp(new Date());
        namePronunciation.setModifiedTimestamp(new Date());
        namePronunciation.setCreatedBy(namePronunciationRequest.getCreatedBy());
        return namePronunciation;
    }

}
