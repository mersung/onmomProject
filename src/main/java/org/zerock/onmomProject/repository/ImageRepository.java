package org.zerock.onmomProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.onmomProject.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @Modifying
    @Query("DELETE FROM Image img WHERE img.review.review_id = :review_id")
    void delImgByReviewId(Long review_id);
}
