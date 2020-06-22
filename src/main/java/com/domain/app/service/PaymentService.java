package com.domain.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.domain.app.entity.Customer;
import com.domain.app.entity.OrderDetail;
import com.domain.app.entity.PaymentDetail;
import com.domain.app.repository.OrderRepository;
import com.domain.app.repository.PaymentDetailRepository;
import com.domain.app.vo.PaymentDetailsRequestVo;
import com.domain.app.vo.PaymentDetailsVo;

import ma.glasnost.orika.MapperFacade;

@Service
public class PaymentService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentDetailRepository paymentDetailRepository;
	
	@Autowired
	private MapperFacade mapperFacade;

	public PaymentDetailsVo createPayment(PaymentDetailsRequestVo paymentRequestVo) {
		Optional<OrderDetail> orderOptional = orderRepository.findById(paymentRequestVo.getOrderId());
		PaymentDetail newEntity = null;
		if (orderOptional.isPresent()) {
			OrderDetail orderEntity = orderOptional.get();
			PaymentDetailsVo vo = paymentRequestVo.getPaymentDetails();
			if (paymentRequestVo.getPaymentDetails() != null) {
				// Check if PaymentDetails exist for that customer
				List<PaymentDetail> existingList = orderEntity.getCustomer().getPaymentDetails();
				if (!CollectionUtils.isEmpty(existingList)) {
					for (PaymentDetail detail : existingList) {
						if (!detail.getCardNumber().equals(vo.getCardNumber())) {
							newEntity = createNewPayment(vo, orderEntity.getCustomer());
						} else {
							orderEntity.setPaymentDetail(detail);
							orderEntity.setOrderStatus("INITIATED");
							orderRepository.save(orderEntity);
							return mapperFacade.map(detail, PaymentDetailsVo.class);
						}
					}
				} else {
					newEntity = createNewPayment(vo, orderEntity.getCustomer());
				}
				orderEntity.setPaymentDetail(newEntity);
				orderEntity.setOrderStatus("INITIATED");
				orderRepository.save(orderEntity);
				return mapperFacade.map(newEntity, PaymentDetailsVo.class);
			}
		}
		return null;
	}

	private PaymentDetail createNewPayment(PaymentDetailsVo vo, Customer customer) {
		PaymentDetail paymentDetailEntity = new PaymentDetail();
		paymentDetailEntity.setFirstName(vo.getFirstName());
		paymentDetailEntity.setLastName(vo.getLastName());
		paymentDetailEntity.setCardNumber(vo.getCardNumber());
		paymentDetailEntity.setCardExpiryDate(vo.getCardExpiryDate());
		paymentDetailEntity.setPaymentType(vo.getPaymentType());
		paymentDetailEntity.setCreatedOn(new Date());
		paymentDetailEntity.setCustomer(customer);
		return paymentDetailRepository.save(paymentDetailEntity);
	}
}
