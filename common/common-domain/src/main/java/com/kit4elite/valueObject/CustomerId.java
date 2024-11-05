package com.kit4elite.valueObject;

import java.util.UUID;

public class CustomerId extends BaseId<UUID> {
    public CustomerId(UUID id) {
        super(id);
    }
}
