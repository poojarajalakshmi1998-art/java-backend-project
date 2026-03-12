package com.pooja.order_service.controller;

import com.pooja.order_service.dto.OrderRequest;
import com.pooja.order_service.dto.OrderResponse;
import com.pooja.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

private final OrderService orderservice;


    @PostMapping ("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        OrderResponse res= orderservice.createOrder(orderRequest);
        return ResponseEntity.ok().body(res);

    }
@PutMapping("/orders/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @RequestBody OrderRequest req,   @PathVariable ("id") long id)
{

    OrderResponse Update_res =orderservice.updateOrder(req,id);

    return ResponseEntity.ok().body(Update_res);


}
@PatchMapping("/orders/{id}/cancel")
public ResponseEntity<OrderResponse> cancelOrder(@PathVariable ("id") Long id )
{
   OrderResponse cancelRes = orderservice.cancelOrder(id);
           return ResponseEntity.ok().body(cancelRes);

}

@GetMapping ("/orders")
   /* public ResponseEntity<Page<OrderResponse>> getOrders(@RequestParam(defaultValue = "0")  int page, @RequestParam(defaultValue = "5") int size)
{
    return ResponseEntity.ok().body(orderservice.getorders(page,size));
}*/

public ResponseEntity<Page<OrderResponse>> getOrders(Pageable pageable)
{
    return ResponseEntity.ok().body(orderservice.getorders(pageable));

}
}

