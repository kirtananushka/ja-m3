package com.tananushka.javaadvanced.rest.controller;

import com.tananushka.javaadvanced.dto.UserRequestDto;
import com.tananushka.javaadvanced.dto.UserResponseDto;
import com.tananushka.javaadvanced.serviceapi.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Tag(name = "User", description = "User management operations")
public class UserController {

    private final UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new user",
            description = "Creates a new user and returns the created user details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRequestDto.class),
                    examples = {
                            @ExampleObject(name = "Create user",
                                    value = "{\"name\": \"James\", \"surname\": \"Smith\", \"birthday\": \"2000-01-31\"}")
                    })),
            responses = {
                    @ApiResponse(description = "User created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(description = "Invalid input", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserResponseDto> createUser(@org.springframework.web.bind.annotation.RequestBody UserRequestDto request) {
        UserResponseDto response = userService.createUser(request);
        addHypermediaLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Patch a user",
            description = "Patch a user and returns the updated user details.",
            requestBody = @RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRequestDto.class),
                    examples = {
                            @ExampleObject(name = "Update user's name",
                                    value = "{\"name\": \"Jack\"}"),
                            @ExampleObject(name = "Update User's surname",
                                    value = "{\"surname\": \"Daniel\"}"),
                            @ExampleObject(name = "Update User's birthday",
                                    value = "{\"birthday\": \"1990-05-15\"}")
                    })),
            responses = {
                    @ApiResponse(description = "User updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(description = "User not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserResponseDto> patchUser(@Parameter(description = "The ID of the user to patch", required = true, example = "1") @PathVariable("id") Long id, @org.springframework.web.bind.annotation.RequestBody UserRequestDto request) {
        UserResponseDto response = userService.updateUser(id, request);
        addHypermediaLinks(response);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a user",
            description = "Updates a user's details and returns the updated user details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserRequestDto.class),
                    examples = {
                            @ExampleObject(name = "Update user",
                                    value = "{\"name\": \"Jack\", \"surname\": \"Daniel\", \"birthday\": \"1990-05-15\"}")
                    })),
            responses = {
                    @ApiResponse(description = "User updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(description = "User not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserResponseDto> updateUser(@Parameter(description = "The ID of the user to update", required = true, example = "1") @PathVariable("id") Long id, @org.springframework.web.bind.annotation.RequestBody UserRequestDto request) {
        UserResponseDto response = userService.updateUser(id, request);
        addHypermediaLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a user by ID",
            description = "Returns a single user's details by ID.",
            responses = {
                    @ApiResponse(description = "User found", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(description = "User not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<UserResponseDto> getUser(@Parameter(description = "ID of the user to be retrieved", required = true, example = "1") @PathVariable("id") Long id) {
        UserResponseDto response = userService.getUser(id);
        addHypermediaLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all users",
            description = "Returns a list of all users.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserResponseDto[].class)))
            })
    public ResponseEntity<CollectionModel<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> responses = userService.getAllUsers().stream()
                .peek(this::addHypermediaLinks)
                .toList();
        Link allUsersLink = linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel();
        CollectionModel<UserResponseDto> collectionModel = CollectionModel.of(responses, allUsersLink);
        return ResponseEntity.ok(collectionModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user",
            description = "Deletes a user by their ID.",
            responses = {
                    @ApiResponse(description = "User deleted", responseCode = "204", content = @Content),
                    @ApiResponse(description = "User not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> deleteUser(@Parameter(description = "ID of the user to be deleted", required = true, example = "1") @PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private void addHypermediaLinks(UserResponseDto response) {
        Long id = response.getId();
        response.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        response.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("deleteUser"));
        response.add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("patchUser"));
        response.add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("updateUser"));
        response.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("getAllUsers"));
    }
}
