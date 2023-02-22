package com.co.andes.management.domain.service;

import com.co.andes.management.domain.repository.OrderRepository;
import com.co.andes.management.domain.repository.model.database.ClientEntity;
import com.co.andes.management.domain.repository.model.database.OrderPurchaseEntity;

import com.co.andes.management.domain.repository.model.database.UserEntity;
import com.co.andes.management.domain.repository.model.database.enums.FinanceEnum;
import com.co.andes.management.domain.service.model.request.OrdersRequestDTO;
import com.co.andes.management.domain.service.model.response.DataResponseDTO;
import com.co.andes.management.domain.service.model.response.order.DetailOrderResponseDTO;
import com.co.andes.management.domain.service.model.response.order.OrderResponseDTO;

import com.co.andes.management.utils.exception.AndesException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {

    final static Logger logger = Logger.getLogger(OrderService.class);
    private OrderRepository orderRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public DataResponseDTO executeGetOrders(String token, OrdersRequestDTO ordersRequestDT) throws AndesException{
        /** if (!token.startsWith("Bearer ") || JwtUtils.decodeJWTAnExtend(token.substring(7)).isEmpty()) {
            throw new AndesException(AndesErrorEnum.GENERIC_ERROR.getCode());
        }**/
        List<OrderPurchaseEntity> stores = orderRepository.getAllOrders();
        List<OrderResponseDTO> orderResponseDTO = new ArrayList<>();
        for(OrderPurchaseEntity it : stores){
           // if(it.getUserEntity().getEmail().equals(ordersRequestDT.getEmail())){
                DetailOrderResponseDTO det = new DetailOrderResponseDTO(it.getId(), it.getStore().getProduct().getName(), it.getStore().getAmount());
                OrderResponseDTO or = new OrderResponseDTO(it.getId(), it.getClient().getNames(), it.getClient().getAddress(), it.getClient().getPhone(), it.getState().getRol(), it.getAmount(),  det);
                orderResponseDTO.add(or);
           // }

        }

        return new DataResponseDTO(orderResponseDTO);
    }
}