package cn.edu.zju.repository;

import cn.edu.zju.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug, String> {
}
