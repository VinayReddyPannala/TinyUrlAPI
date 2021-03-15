package com.neueda.url.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.url.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
