package br.com.devcia.backendtests;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.boot.actuate.health.Status;

@JsonSerialize
record Health(Status status) {
}
