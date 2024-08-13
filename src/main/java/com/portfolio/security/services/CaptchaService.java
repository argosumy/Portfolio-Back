package com.portfolio.security.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CaptchaService {
    @Value("${captcha.path}")
    private String path;
    private final Map<String, UserAnswer> answers = new HashMap<>();

    public byte[] generateCaptcha(String sessionId) throws NoSuchAlgorithmException, IOException {
        final File captchaFile = generateCaptchaFile(sessionId);
        try(FileInputStream fileInputStream = new FileInputStream(captchaFile)) {
            return fileInputStream.readAllBytes();
        }
    }

    private File generateCaptchaFile(String sessionId) throws NoSuchAlgorithmException {
        final File srsCaptcha = new File(path);
        File result = null;
        if(srsCaptcha.isDirectory()) {
            File [] packages = srsCaptcha.listFiles();
            Objects.requireNonNull(packages);
            Random random = SecureRandom.getInstanceStrong();
            int index = random.nextInt(packages.length);
            if(packages[index].isDirectory()) {
                File [] captchaArray = packages[index].listFiles();
                Objects.requireNonNull(captchaArray);
                if(captchaArray.length > 0) {
                    int answer = Integer.parseInt(packages[index].getName());
                    answers.put(sessionId, new UserAnswer(answer));
                    result = captchaArray[0];
                }
            }
        }
        if(result == null) {
            throw new NullPointerException("Captcha not exist");
        }
        return result;
    }

    public boolean validateCaptcha(String sessionId, String userAnswer) {
        UserAnswer correctAnswer = answers.get(sessionId);
        if(sessionId == null || userAnswer == null || correctAnswer == null) {
            return false;
        }
        return correctAnswer.getAnswer() == Integer.parseInt(userAnswer);
    }

    public void removeAnswer(String sessionId) {
        answers.remove(sessionId);
    }

    public void cleanAnswers() {
        List<String> expiredKeys = answers.entrySet()
                .stream()
                .filter(x -> x.getValue().isExpired())
                .map(Map.Entry::getKey).collect(Collectors.toList());
        for(String key : expiredKeys) {
            answers.remove(key);
        }
    }

    private static final class UserAnswer {
        private final int answer;
        private final LocalDateTime time;

        public UserAnswer(int answer) {
            this.answer = answer;
            this.time = LocalDateTime.now().plusMinutes(4);
        }

        private int getAnswer() {
            return answer;
        }

        private boolean isExpired() {
            return LocalDateTime.now().isAfter(time);
        }
    }

}
