package project.metadatatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.metadatatest.entity.Hypervisor;
import project.metadatatest.entity.PodList;

public interface PodListRepository extends JpaRepository<PodList, Long> {
}
