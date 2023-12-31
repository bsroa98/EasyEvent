package com.ucatolica.easyevent.easyevent.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.ucatolica.easyevent.easyevent.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ucatolica.easyevent.easyevent.entities.Cliente;
import com.ucatolica.easyevent.easyevent.entities.Rol;
import com.ucatolica.easyevent.easyevent.entities.Erol;
import com.ucatolica.easyevent.easyevent.security.payload.request.LoginRequest;
import com.ucatolica.easyevent.easyevent.security.payload.request.SignupRequest;
import com.ucatolica.easyevent.easyevent.security.payload.response.UserInfoResponse;
import com.ucatolica.easyevent.easyevent.security.payload.response.MessageResponse;
import com.ucatolica.easyevent.easyevent.repositories.RolRepository;
import com.ucatolica.easyevent.easyevent.repositories.ClienteRepository;
import com.ucatolica.easyevent.easyevent.security.jwt.JwtUtils;
import com.ucatolica.easyevent.easyevent.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    EmailService emailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("password:"+loginRequest.getPassword());
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws MessagingException {
        if (clienteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (clienteRepository.existsByCorreo(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Cliente cliente = new Cliente(signUpRequest.getNombre(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getNumeroIdentificacion(),signUpRequest.getDireccion(),
                signUpRequest.getUsername());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Rol> roles = new HashSet<>();

        if (strRoles == null) {
            Rol userRole = rolRepository.findByName(Erol.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Rol adminRole = rolRepository.findByName(Erol.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Rol modRole = rolRepository.findByName(Erol.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Rol userRole = rolRepository.findByName(Erol.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        cliente.setRoles(roles);
        clienteRepository.save(cliente);
        if (cliente.getCorreo() != null) {
            emailService.sendTextEmail(cliente.getCorreo(), "Email verificacion", "Su enlace de verificacion es el siguiente: \n\n"+"localhost/api/auth/verify/" + cliente.getId());
        }
        else if (cliente.getCorreo()==null){
            return ResponseEntity.badRequest().body(new MessageResponse("Error email not found"));
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @PostMapping("/verify/{id}")
    public ResponseEntity<?> verifyUser(@PathVariable long id) {
        Optional<Cliente> clienteTemp = clienteRepository.findById(id);
        if (clienteTemp.isPresent()) {
            Cliente cliente = clienteTemp.get();
            if(!cliente.isVerificado()){
                cliente.setVerificado();
                clienteRepository.save(cliente);
                return ResponseEntity.status(HttpStatus.CREATED).body("User verify");
            }
            else{
                return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User already verify");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
    }

    @GetMapping("/getrol/{id}")
    public Set<?> getRol(@PathVariable long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Cliente cliente = clienteOptional.get();
        return cliente.getRoles();
    }
}
