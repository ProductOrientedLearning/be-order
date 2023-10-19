package pol.ecom.micro.shop.order.service.impl;
/*
 * This is course Microservice Product Oriented
 * MIT No Attribution

 * Copyright (c) 2023 <Dr.JohnLe & Mr.HaNguyen>

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pol.ecom.micro.shop.order.dto.request.OrderRequest;
import pol.ecom.micro.shop.order.dto.response.OrderResponse;
import pol.ecom.micro.shop.order.mapper.dto.OrderDtoMapper;
import pol.ecom.micro.shop.order.mapper.enity.OrderMapper;
import pol.ecom.micro.shop.order.repository.OrderRepository;
import pol.ecom.micro.shop.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDtoMapper orderDtoMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public OrderResponse crateOrder(OrderRequest request) {
        return orderDtoMapper.toDto(orderRepository.save(orderMapper.toEntity(request)));
    }
}
