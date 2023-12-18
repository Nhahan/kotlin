package com.delivery.controller.display.sdp

import com.delivery.controller.catalog.menu.dto.MenuDTO
import com.delivery.controller.display.sdp.dto.StoreDetailPageResponse
import com.delivery.exception.NotFoundStoreException
import com.delivery.service.menu.MenuService
import com.delivery.service.store.StoreService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "StoreDetailPageController", description = "상점 상세 페이지 컨트롤러")
@RestController
class StoreDetailPageController(
    val storeService: StoreService,
    val menuService: MenuService,
) {

    @GetMapping("/apis/display/stores/{storeId}")
    fun list(@PathVariable storeId: Long): StoreDetailPageResponse {

        val storeOptional = storeService.findByStoreId(storeId)
        if (storeOptional.isEmpty) {
            throw NotFoundStoreException("상점 정보를 찾을 수 없습니다. $storeId")
        }
        val store = storeOptional.get()

        val menus = menuService.findByStoreId(storeId)
        val menuDTOs = menus.map { MenuDTO.from(it) }

        return StoreDetailPageResponse(
            storeId = storeId,
            storeName = store.storeName,
            phone = store.storePhone,
            address = store.address,
            storeMainImageUrl = store.storeMainImageUrl,
            description = store.description,
            deliveryFee = store.deliveryFee,
            deliveryTime = store.deliveryTime,
            reviewGrade = store.reviewGrade,
            minimumOrderPrice = store.minimumOrderPrice,
            menus = menuDTOs,
        )
    }
}


