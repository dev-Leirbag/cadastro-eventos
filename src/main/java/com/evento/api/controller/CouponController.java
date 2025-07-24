package com.evento.api.controller;

import com.evento.api.domain.coupon.Coupon;
import com.evento.api.domain.coupon.CouponRequestDTO;
import com.evento.api.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> createCoupon (@PathVariable UUID eventId, @RequestBody CouponRequestDTO coupon){
        Coupon coupon1 = couponService.createCoupon(eventId,coupon);
        return ResponseEntity.ok(coupon1);
    }
}
