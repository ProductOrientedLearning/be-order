package pol.ecom.micro.shop.order.mapper.enity;
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
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pol.ecom.micro.shop.lib.constant.MessageCode;
import pol.ecom.micro.shop.lib.dto.response.ProductDto;
import pol.ecom.micro.shop.lib.exception.ShopException;
import pol.ecom.micro.shop.lib.integrate.rest.ProductIntegrate;
import pol.ecom.micro.shop.lib.mapper.EntityMapper;
import pol.ecom.micro.shop.lib.util.MessageUtil;
import pol.ecom.micro.shop.order.dto.request.OrderRequest;
import pol.ecom.micro.shop.order.entity.Order;

@Component
public class OrderMapper implements EntityMapper<OrderRequest, Order> {

 @Autowired
 private ProductIntegrate productIntegrate;
 @Autowired
 private MessageUtil messageUtil;

 @Override
 public Order toEntity(OrderRequest request) {
  ProductDto productDto = productIntegrate.getProductById(request.getIdProduct());
  if(ObjectUtils.isEmpty(productDto)) {
   throw new ShopException(MessageCode.MESSAGE_ERROR_SYSTEM_ERROR.getCode(), messageUtil.getMessage(MessageCode.MESSAGE_ERROR_SYSTEM_ERROR));
  }
  double total = request.getNumProduct() * productDto.getPrice();
  return Order.builder()
          .idProduct(productDto.getId())
          .total(total)
          .build();
 }
}
