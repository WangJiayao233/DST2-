package cn.edu.zju.repository;

import cn.edu.zju.entity.DrugLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugLabelRepository extends JpaRepository<DrugLabel, String> {
}
