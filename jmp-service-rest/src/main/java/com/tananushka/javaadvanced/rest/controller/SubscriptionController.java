package com.tananushka.javaadvanced.rest.controller;

import com.tananushka.javaadvanced.dto.SubscriptionRequestDto;
import com.tananushka.javaadvanced.dto.SubscriptionResponseDto;
import com.tananushka.javaadvanced.serviceapi.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/subscriptions")
@AllArgsConstructor
@Tag(name = "Subscription", description = "Subscription management operations")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @Operation(summary = "Create a new subscription",
            description = "Creates a new subscription and returns the created subscription details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SubscriptionRequestDto.class),
                    examples = {
                            @ExampleObject(name = "Create subscription",
                                    value = "{\"userId\": \"1\"}")
                    })),
            responses = {
                    @ApiResponse(description = "Subscription created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = SubscriptionResponseDto.class))),
                    @ApiResponse(description = "Invalid input", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<SubscriptionResponseDto> createSubscription(@org.springframework.web.bind.annotation.RequestBody SubscriptionRequestDto request) {
        SubscriptionResponseDto response = subscriptionService.createSubscription(request);
        addHypermediaLinks(response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    @Operation(summary = "Update a subscription",
            description = "Updates a subscription's details and returns the updated subscription details.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SubscriptionRequestDto.class),
                    examples = {
                            @ExampleObject(name = "Update subscription",
                                    value = "{\"userId\": \"1\"}")
                    })),
            responses = {
                    @ApiResponse(description = "Subscription updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SubscriptionResponseDto.class))),
                    @ApiResponse(description = "Subscription not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<SubscriptionResponseDto> updateSubscription(@Parameter(description = "The ID of the subscription to update", required = true, example = "1") @PathVariable("id") Long id, @org.springframework.web.bind.annotation.RequestBody SubscriptionRequestDto request) {
        SubscriptionResponseDto response = subscriptionService.updateSubscription(id, request);
        addHypermediaLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Get a subscription by ID",
            description = "Returns a single subscription's details by ID.",
            responses = {
                    @ApiResponse(description = "Subscription found", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SubscriptionResponseDto.class))),
                    @ApiResponse(description = "Subscription not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<SubscriptionResponseDto> getSubscription(@Parameter(description = "ID of the subscription to be retrieved", required = true, example = "1") @PathVariable("id") Long id) {
        SubscriptionResponseDto response = subscriptionService.getSubscription(id);
        addHypermediaLinks(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get all subscriptions",
            description = "Returns a list of all subscriptions.",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SubscriptionResponseDto[].class)))
            })
    public ResponseEntity<CollectionModel<SubscriptionResponseDto>> getAllSubscriptions() {
        List<SubscriptionResponseDto> responses = subscriptionService.getAllSubscriptions().stream()
                .peek(this::addHypermediaLinks)
                .toList();
        Link allSubscriptionsLink = linkTo(methodOn(SubscriptionController.class).getAllSubscriptions()).withSelfRel();
        CollectionModel<SubscriptionResponseDto> collectionModel = CollectionModel.of(responses, allSubscriptionsLink);
        return ResponseEntity.ok(collectionModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subscription",
            description = "Deletes a subscription by their ID.",
            responses = {
                    @ApiResponse(description = "Subscription deleted", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Subscription not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Void> deleteSubscription(@Parameter(description = "ID of the subscription to be deleted", required = true, example = "1") @PathVariable("id") Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }

    private void addHypermediaLinks(SubscriptionResponseDto response) {
        Long id = response.getId();
        response.add(linkTo(methodOn(SubscriptionController.class).getSubscription(id)).withSelfRel());
        response.add(linkTo(methodOn(SubscriptionController.class).deleteSubscription(id)).withRel("deleteSubscription"));
        response.add(linkTo(methodOn(SubscriptionController.class).updateSubscription(id, null)).withRel("updateSubscription"));
        response.add(linkTo(methodOn(SubscriptionController.class).getAllSubscriptions()).withRel("getAllSubscriptions"));
    }
}
