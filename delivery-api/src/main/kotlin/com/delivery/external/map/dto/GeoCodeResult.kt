package com.delivery.external.map.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GeoCodeResult(
    @JsonProperty("address_components")
    val addressComponents: List<AddressComponent>,
    @JsonProperty("formatted_address")
    val formattedAddress: String,
)
