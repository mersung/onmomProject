package org.zerock.onmomProject.repository.search;

import org.zerock.onmomProject.entity.ReviewBoard;

import java.util.List;

public interface SearchReviewBoardRepository {
    List<ReviewBoard> search1();

    List<ReviewBoard> search1(String area, String type, String keyword);
}
