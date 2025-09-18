package com.online_ticket_booking_system.ticket_booking.controller;

import com.online_ticket_booking_system.ticket_booking.entity.Route;
import com.online_ticket_booking_system.ticket_booking.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String listRoutes(Model model) {
        model.addAttribute("routes", routeService.getAllRoutes());
        return "routes";
    }

    @GetMapping("/add")
    public String addRouteForm(Model model) {
        model.addAttribute("route", new Route());
        return "add-route";
    }

    @PostMapping("/add")
    public String saveRoute(@ModelAttribute Route route) {
        routeService.saveRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("/edit/{id}")
    public String editRouteForm(@PathVariable Long id, Model model) {
        model.addAttribute("route", routeService.getRouteById(id));
        return "edit-route";
    }

    @PostMapping("/edit/{id}")
    public String updateRoute(@PathVariable Long id, @ModelAttribute Route route) {
        routeService.updateRoute(id, route);
        return "redirect:/routes";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/routes";
    }
}
