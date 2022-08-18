package project.metadatatest.controller;

import io.kubernetes.client.openapi.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.metadatatest.service.MetaService;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MetaController {

    private final MetaService metaService;

    @PostMapping("/insert/hypervisor")
    public void insertHypervisorMetadata() {
        metaService.insertHypervisorMetadata();
    }

    @PostMapping("/insert/pod-list")
    public void insertPodListMetadata() throws IOException, ApiException {
        metaService.insertPodListMetadata();
    }

    @PostMapping("/insert/hpa-list")
    public void insertHpaListMetadata() throws IOException, ApiException {
        metaService.insertHPAListMetadata();
    }
}
