import saga.service.reception.ReceptionApi
import saga.service.reception.ReceptionApiData.{CancelOrderRequest, CompleteOrderRequest, CreateOrderRequest}

object ReceptionApiMock extends ReceptionApi:
  def createOrder(payload: CreateOrderRequest): Unit = ()
  def cancelOrder(payload: CancelOrderRequest): Unit = ()
  def completeOrder(payload: CompleteOrderRequest): Unit = ()
  
