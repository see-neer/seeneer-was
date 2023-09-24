package com.repill.was.review.repository.jpa;

import com.repill.was.review.entity.Review;
import com.repill.was.review.entity.ReviewId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<Review, ReviewId> {
}
