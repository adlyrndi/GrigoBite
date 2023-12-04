package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.RequestEdit;
import com.grigoBiteUI.dto.ResponseProfile;
import com.grigoBiteUI.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<ResponseProfile> getProfileById (
            HttpServletRequest request, @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(profileService.getProfileByUserId(request, userId));
    }


    @PutMapping("/edit")
    public ResponseEntity<ResponseProfile> editProfile (
            HttpServletRequest request, @RequestBody RequestEdit editProfileRequest
    ) {
        return ResponseEntity.ok(profileService.editProfile(request, editProfileRequest));
    }
}
