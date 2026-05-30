package com.alphacore.service;

import com.alphacore.model.OtpVerification;
import com.alphacore.model.TbUser;
import com.alphacore.model.UserCredentials;
import com.alphacore.repository.OtpRepository;
import com.alphacore.repository.TbUserRepository;
import com.alphacore.repository.UserCredentialsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthenticationService {

    private final TbUserRepository userRepository;
    private final UserCredentialsRepository credentialsRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;

    public AuthenticationService(
            TbUserRepository userRepository,
            UserCredentialsRepository credentialsRepository,
            OtpRepository otpRepository,
            PasswordEncoder passwordEncoder,
            EmailService emailService,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.credentialsRepository = credentialsRepository;
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    // =========================
    // LOGIN
    // =========================
    public String login(String email, String password) {

        System.out.println("LOGIN EMAIL: " + email);

        if (email == null || email.isBlank()) {
            throw new RuntimeException("Email is required");
        }

        TbUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        UserCredentials cred = credentialsRepository.findByUserId(user.getId());

        if (cred == null) {
            throw new RuntimeException("User credentials not found");
        }

        if (!passwordEncoder.matches(password, cred.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String otp = generateOtp();

        OtpVerification otpEntity = new OtpVerification();
        otpEntity.setUserId(user.getId());
        otpEntity.setOtpHash(passwordEncoder.encode(otp));
        otpEntity.setPurpose("LOGIN");
        otpEntity.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpEntity.setIsUsed(false);
        otpEntity.setAttemptCount(0);
        otpEntity.setCreatedAt(LocalDateTime.now());

        otpRepository.save(otpEntity);

        emailService.sendOtp(email, otp);

        return "OTP_SENT";
    }

    // =========================
    // VERIFY OTP
    // =========================
    public String verifyOtp(String email, String otpInput) {

        System.out.println("VERIFY EMAIL: " + email);

        if (email == null || email.isBlank()) {
            throw new RuntimeException("Email is missing in request");
        }

        TbUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        OtpVerification otp = otpRepository
                .findTopByUserIdOrderByCreatedAtDesc(user.getId());

        if (otp == null) {
            throw new RuntimeException("OTP not found");
        }

        LocalDateTime now = LocalDateTime.now();

        if (otp.getExpiresAt() == null || otp.getExpiresAt().isBefore(now)) {
            throw new RuntimeException("OTP expired");
        }

        if (Boolean.TRUE.equals(otp.getIsUsed())) {
            throw new RuntimeException("OTP already used");
        }

        if (otp.getAttemptCount() >= 3) {
            throw new RuntimeException("Maximum OTP attempts reached");
        }

        if (!passwordEncoder.matches(otpInput, otp.getOtpHash())) {
            otp.setAttemptCount(otp.getAttemptCount() + 1);
            otpRepository.save(otp);
            throw new RuntimeException("Invalid OTP");
        }

        otp.setIsUsed(true);
        otpRepository.save(otp);

        return jwtService.generateToken(
                user.getEmail(),
                user.getAuthority().name()
        );
    }
    
 // =========================
 // FORGOT PASSWORD
 // =========================
 public void forgotPassword(String email) {

     TbUser user = userRepository.findByEmail(email)
             .orElseThrow(() -> new RuntimeException("User not found"));

     String token = jwtService.generateResetToken(user.getEmail());

     String resetLink =
             "http://localhost:3000/reset-password?token=" + token;

     emailService.sendEmail(
             email,
             "Reset Password",
             "Click the link below to reset your password:\n\n" + resetLink
     );
 }

 // =========================
 // RESET PASSWORD
 // =========================
 public void resetPassword(String token, String newPassword) {

     String email = jwtService.extractEmail(token);

     TbUser user = userRepository.findByEmail(email)
             .orElseThrow(() -> new RuntimeException("User not found"));

     UserCredentials credentials =
             credentialsRepository.findByUserId(user.getId());

     if (credentials == null) {
         throw new RuntimeException("User credentials not found");
     }

     credentials.setPassword(
             passwordEncoder.encode(newPassword)
     );

     credentialsRepository.save(credentials);
 }

    // =========================
    // OTP GENERATOR
    // =========================
    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }
}