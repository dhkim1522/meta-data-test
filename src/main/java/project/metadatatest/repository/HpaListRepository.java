package project.metadatatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.metadatatest.entity.HpaList;
import project.metadatatest.entity.Hypervisor;

public interface HpaListRepository extends JpaRepository<HpaList, Long> {
}
