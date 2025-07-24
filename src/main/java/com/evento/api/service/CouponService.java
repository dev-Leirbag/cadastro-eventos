package com.evento.api.service;

import com.evento.api.domain.coupon.Coupon;
import com.evento.api.domain.coupon.CouponRequestDTO;
import com.evento.api.domain.event.Event;
import com.evento.api.repositories.CouponRepository;
import com.evento.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CouponRepository couponRepository;

    public Coupon createCoupon (UUID eventId,CouponRequestDTO coupon){
        Event event = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalArgumentException("Evento Não encontrado! " + eventId));

        Coupon newCoupon = new Coupon();
        newCoupon.setCode(coupon.code());
        newCoupon.setDiscount(coupon.discount());
        newCoupon.setValid(new Date(coupon.valid()));
        newCoupon.setEvent(event);

        return couponRepository.saveAndFlush(newCoupon);
    }

    public List<Coupon> consultCoupons(UUID eventId, Date currentDate){
        return couponRepository.findByEventIdAndValidAfter(eventId, currentDate);
    }

}
