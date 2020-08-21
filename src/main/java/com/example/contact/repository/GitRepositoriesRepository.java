package com.example.contact.repository;

import com.example.contact.domain.GitRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitRepositoriesRepository extends JpaRepository<GitRepositories,Integer> {
}
