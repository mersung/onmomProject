package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.onmomProject.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
