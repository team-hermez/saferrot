package com.hermez.saferrot.shipping.controller;

import com.hermez.saferrot.shipping.dto.response.ShippingViewResponse;
import com.hermez.saferrot.shipping.service.ShippingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/shipping")
@Controller
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/track-shipment")
    public String trackShipment(@RequestParam String merchantUid, Model model) {
        ShippingViewResponse response = shippingService.showShippingView(merchantUid);
        model.addAttribute("response", response);
        return "redirect-to-tracker";
    }
}
