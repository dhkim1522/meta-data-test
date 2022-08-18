package project.metadatatest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import project.metadatatest.service.OpenstackService;
import project.metadatatest.vo.HypervisorVO;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/openstack")
public class OpenstackController {

    private final OpenstackService openstackService;

    @GetMapping("/token")
    public String getAccessToken() {
        return openstackService.getAccessToken();
    }

    @GetMapping("/hypervisor")
    public List<HypervisorVO> getHypervisor() {
        return openstackService.getHypervisor();
    }
}
