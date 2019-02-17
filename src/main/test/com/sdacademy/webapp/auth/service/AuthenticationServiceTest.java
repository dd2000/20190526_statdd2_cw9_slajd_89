package com.sdacademy.webapp.auth.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sdacademy.webapp.auth.components.PasswordEncoder;
import com.sdacademy.webapp.auth.model.User;
import com.sdacademy.webapp.auth.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class) // równoznacze @ExtendWith(MockitoExtension)
public class AuthenticationServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;

  @InjectMocks
  private AuthenticationService authenticationService;

  @Test
  public void shouldSignInUserWithCorrectPassword() {
    final String email = "test@gmail.com";
    final String userPassword = "SecretP@assword_123";
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(
        User.builder()
            .email(email)
            .password(userPassword)
            .build()
    ));
    when(passwordEncoder.encode(userPassword)).thenReturn(userPassword);

    final boolean isUserSignedIn = authenticationService.signInUser(email, userPassword);

    assertTrue(isUserSignedIn);
    verify(userRepository).findByEmail(email);
    verify(passwordEncoder).encode(userPassword);
  }

  @Test
  public void shouldFailToSignInWhenPasswordIsIncorrect() {
    final String email = "test@gmail.com";
    final String userPassword = "SecretP@assword_123";
    when(userRepository.findByEmail(email)).thenReturn(Optional.of(
        User.builder()
            .email(email)
            .password(userPassword)
            .build()
    ));
    final String incorrectPassword = "SecretPassword_12";
    when(passwordEncoder.encode(incorrectPassword)).thenReturn(incorrectPassword);

    final boolean isUserSignedIn = authenticationService.signInUser(email, incorrectPassword);

    assertFalse(isUserSignedIn);
    verify(userRepository).findByEmail(email);
    verify(passwordEncoder).encode(incorrectPassword);
  }

  @Test
  public void shouldNotSignInUserWhenCannotFindByEmail() {
    final String email = "test@gmail.com";
    when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

    final boolean isUserSignedIn = authenticationService.signInUser(email, "test");

    assertFalse(isUserSignedIn);
    verify(userRepository).findByEmail(email);
    verifyZeroInteractions(passwordEncoder);
  }

}