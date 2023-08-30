package saga.service.reception

import saga.service.reception.ReceptionApiData.{CancelOrderRequest, CompleteOrderRequest, CreateOrderRequest}

import java.util.UUID

object ReceptionApiAsync extends ReceptionApi:
  def createOrder(payload: CreateOrderRequest): Unit = ???
  def cancelOrder(payload: CancelOrderRequest): Unit = ???
  def completeOrder(payload: CompleteOrderRequest): Unit = ???
  

