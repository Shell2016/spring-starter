package ru.michaelshell.spring.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.michaelshell.spring.dto.PageResponse;
import ru.michaelshell.spring.dto.UserCreateEditDto;
import ru.michaelshell.spring.dto.UserFilter;
import ru.michaelshell.spring.dto.UserReadDto;
import ru.michaelshell.spring.service.CompanyService;
import ru.michaelshell.spring.service.UserService;
import ru.michaelshell.spring.validation.group.CreateAction;
import ru.michaelshell.spring.validation.group.UpdateAction;

import javax.validation.groups.Default;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final CompanyService companyService;


    @GetMapping
    public PageResponse<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated({Default.class, CreateAction.class}) @RequestBody UserCreateEditDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable Long id,
                         @Validated({Default.class, UpdateAction.class}) @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable("id") Long id) {
//        if (!userService.delete(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

//    @GetMapping(value = "/{id}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public byte[] findAvatar(@PathVariable("id") Long id) {
//        return userService.findAvatar(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @GetMapping(value = "/{id}/avatar")
    public ResponseEntity<byte[]> findAvatar(@PathVariable("id") Long id) {
        return userService.findAvatar(id)
                .map(content -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                        .contentLength(content.length)
                        .body(content))
                .orElseGet(ResponseEntity.notFound()::build);
    }
}
