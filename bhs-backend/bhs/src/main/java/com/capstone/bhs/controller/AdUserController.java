package com.capstone.bhs.controller;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.common.CommonConstant;
import com.capstone.bhs.common.HeaderUtil;
import com.capstone.bhs.common.JwtUtil;
import com.capstone.bhs.common.SpringSecurityUtils;
import com.capstone.bhs.model.dto.AuthenticationResponseDTO;
import com.capstone.bhs.model.entity.AdUser;
import com.capstone.bhs.model.entity.UserProfile;
import com.capstone.bhs.model.vm.AdUserVM;
import com.capstone.bhs.model.vm.ChangePasswordVM;
import com.capstone.bhs.model.vm.EmailVM;
import com.capstone.bhs.model.vm.KeyAndPasswordVM;
import com.capstone.bhs.model.vm.RegisterVM;
import com.capstone.bhs.model.vm.UsernameAndPasswordVM;
import com.capstone.bhs.repository.AdUserRepository;
import com.capstone.bhs.service.AdUserService;
import com.capstone.bhs.service.MailService;
import com.capstone.bhs.service.UserProfileService;
import com.capstone.bhs.service.impl.UserDetailService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdUserController {

	@Autowired
	private AdUserService service;

	@Autowired
	private MailService mailService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserDetailService userDetailsService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private JwtUtil JwtUtil;

	@Autowired
	private AdUserRepository adUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/admin/get-all-list-account")
	public ResponseEntity<List<AdUser>> getAllListAccount() {
		return ResponseEntity.ok().body(service.getAllListAccount());
	}

	@PutMapping("/admin/lock-account/{id}")
	public ResponseEntity<?> lockAccount(AdUserVM adUserVM, @PathVariable("id") Long id) {
		try {
			Optional<AdUser> name = adUserRepository.findOneById(id);
			if (name.get().getRole().equalsIgnoreCase(CommonConstant.Authorities.ROLE_ADMIN)) {
				return ResponseEntity.badRequest()
						.headers(HeaderUtil.createFailureAlert("AdUser", "Error Admin", "Error Admin")).body(null);
			} else if (name.get().isLocked()) {
				return ResponseEntity.badRequest()
						.headers(HeaderUtil.createFailureAlert("AdUser", "Account is Locked", "Account is Locked"))
						.body(null);
			} else {
				return ResponseEntity.ok().headers(HeaderUtil.createAlert("Lock success", "Lock success"))
						.body(service.lockAccount(adUserVM, id));
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("AdUser", "Error", "Error"))
					.body(null);
		}
	}

	@PutMapping("/admin/unlock-account/{id}")
	public ResponseEntity<?> unlockAccount(AdUserVM adUserVM, @PathVariable("id") Long id) {
		try {
			Optional<AdUser> name = adUserRepository.findOneById(id);
			if (!name.get().isLocked()) {
				return ResponseEntity.badRequest().headers(
						HeaderUtil.createFailureAlert("AdUser", "Account is not Locked", "Account is not Locked"))
						.body(null);
			}
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Unlock success", "Unlock success"))
					.body(service.unlockAccount(adUserVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("AdUser", "Error", "Error"))
					.body(null);
		}
	}

	/* Authenticate */
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody UsernameAndPasswordVM credential) {
		try {
			if (!credential.getUsername().isEmpty() || credential.getUsername() != null
					|| credential.getPassword().isEmpty() || credential.getPassword() != null)
				authManager.authenticate(
						new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword()));
		} catch (BadCredentialsException be) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert("Login", "Wrong username or password", "Wrong username or password"))
					.body(null);
		} catch (DisabledException de) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Login",
					"Account has not been activated", "Account has not been activated")).body(null);
		} catch (LockedException ef) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Login", "Account is Locked", "Account is Locked"))
					.body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("AdUser", "Account not exist", "Account not exist"))
					.body(null);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(credential.getUsername());
		final String role = userDetails.getAuthorities().toArray()[0].toString();
		final String jwt = JwtUtil.generateToken(userDetails);
		final AuthenticationResponseDTO res = new AuthenticationResponseDTO(role, jwt);
		return ResponseEntity.ok().headers(HeaderUtil.createAlert("Login success", "Login success")).body(res);
	}
	/* Authenticate */

	/* Start Register */
	@PostMapping("/register")
	public ResponseEntity<?> registerAccount(@RequestBody RegisterVM regUser) {
		try {
			AdUser user = service.registerUser(regUser);
			UserProfile userProfile = new UserProfile();
			userProfile.setEmail(regUser.getEmail());
			userProfile.setFirstName(regUser.getFirstName());
			userProfile.setLastName(regUser.getLastName());
			userProfile.setGender(regUser.getGender());
			userProfile.setUserId(user.getId());
			userProfileService.save(userProfile);
			mailService.sendActivationEmail(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("AdUser",
							"Something went wrong! Please double check your information",
							"Something went wrong! Please double check your information"))
					.body(null);
		}
		return ResponseEntity.ok()
				.headers(HeaderUtil.createAlert(
						"Account Created, Please active your account via Email youu just use to register",
						"Create success"))
				.body(null);

	}

	/* End Register */

	/* Start Active Account */
	@GetMapping("/ad-user/activate")
	public ResponseEntity<?> activateAccount(@RequestParam("key") String activeKey) {
		Optional<AdUser> user = service.activateAccount(activeKey);
		if (!user.isPresent()) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("AdUser",
							"Activation Link Expired! Please register a new account",
							"Activation Link Expired! Please register a new account"))
					.body(null);
		} else {
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("Activation success! You can login to your account now",
							"Activation success! You can login to your account now"))
					.body(null);
		}
	}
	/* End Active Account */

	/* Start Password reset Initiation */
	@PostMapping("/ad-user/reset-password/init")
	public ResponseEntity<?> requestPasswordReset(@RequestBody EmailVM mail) {
		Optional<AdUser> user = service.requestPasswordReset(mail.getEmail());
		if (user.isPresent()) {
			mailService.sendPasswordResetMail(user.get());
			return ResponseEntity.ok()
					.headers(HeaderUtil.createAlert("A Reset password Email has been sent to your email adress",
							"A Reset password Email has been sent to your email adress"))
					.body(null);
		} else {
			return ResponseEntity
					.badRequest().headers(HeaderUtil.createFailureAlert("AdUser",
							"No user found with this inputed Email", "No user found with this inputed Email"))
					.body(null);
		}
	}
	/* End Password reset Initiation */

	/* Start Password reset Finish */
	@PostMapping("/ad-user/reset-password/finish")
	public ResponseEntity<?> finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
		Optional<AdUser> user = service.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

		if (!user.isPresent()) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("AdUser", "Reset Link Expired", "Reset Link Expired"))
					.body(null);
		} else {
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Reset success", "Reset success")).body(null);
		}
	}
	/* End Password reset Finish */

	@GetMapping("/get-user/{id}")
	public ResponseEntity<AdUser> getUserId(@PathVariable("id") Long id) {
		Optional<AdUser> name = adUserRepository.findOneById(id);
		Optional<AdUser> obj = service.getUserById(id);
		if (name.get().getRole().equalsIgnoreCase(CommonConstant.Authorities.ROLE_ADMIN)) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("AdUser", "Error Admin", "Error Admin")).body(null);
		}
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("AdUser", "User not found", "User not found")).body(null);
		}

	}

	/* Change password */
	@PostMapping("/change-password")
	public ResponseEntity<?> changeAccountPassword(@RequestBody ChangePasswordVM object) {
		String username = SpringSecurityUtils.getCurrentUserLogin().get();
		Optional<AdUser> account = adUserRepository.findOneByUsernameIgnoreCase(username);
		if (account.isPresent()) {
//			String encodedString = Base64.getEncoder().encodeToString(account.get().getPassword().getBytes());
//			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
//			String decodedString = new String(decodedBytes);
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = object.getOldPassword();
			String encodedPassword = account.get().getPassword();
		    System.out.println(password);
		    System.out.println(encodedPassword);
			if (!passwordEncoder.matches(password, encodedPassword)) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ChangePassword",
						"Old Password incorrect!", "Old Password incorrect!")).body(null);
			}
			if (!object.getNewPassword().equalsIgnoreCase(object.getRePassword())) {
				return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ChangePassword",
						"New Password miss match!", "New Password miss match!")).body(null);
			}
			account.get().setPassword(passwordEncoder.encode(object.getNewPassword()));
			adUserRepository.save(account.get());
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Password Changed!", "Password Changed!"))
					.body(null);
		} else {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ChangePassword",
					"Something went wrong...", "Something went wrong...")).body(null);
		}
	}
	/* Change password */

}
