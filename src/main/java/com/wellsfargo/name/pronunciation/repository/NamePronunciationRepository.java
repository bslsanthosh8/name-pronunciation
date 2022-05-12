package com.wellsfargo.name.pronunciation.repository;

import com.wellsfargo.name.pronunciation.entity.NamePronunciation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamePronunciationRepository extends JpaRepository<NamePronunciation, String> {
}
