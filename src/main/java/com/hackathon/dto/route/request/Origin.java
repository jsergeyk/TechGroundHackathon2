package com.hackathon.dto.route.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Origin {
    private LocationWrapper location;
}
