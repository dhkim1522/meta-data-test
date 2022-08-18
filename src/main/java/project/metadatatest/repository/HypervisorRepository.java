package project.metadatatest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.metadatatest.entity.Hypervisor;

public interface HypervisorRepository extends JpaRepository<Hypervisor, Long> {
}
