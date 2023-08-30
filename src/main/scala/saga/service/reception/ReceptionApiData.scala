package saga.service.reception

import saga.service.ApiData.{ApiRequest, ApiResponse}

import java.util.UUID

object ReceptionApiData:
  sealed trait ReceptionApiData
  sealed trait ReceptionApiRequest extends ReceptionApiData, ApiRequest
  sealed trait ReceptionApiResponse extends ReceptionApiData, ApiResponse
  
  case class CreateOrderRequest(orderId: UUID) extends ReceptionApiRequest
  case class OrderCreatedResponse(oderId: UUID) extends ReceptionApiResponse
  case class CompleteOrderRequest(orderId: UUID) extends ReceptionApiRequest
  case class CancelOrderRequest(orderId: UUID) extends ReceptionApiResponse
  case class OrderCanceledResponse(orderId: UUID) extends ReceptionApiResponse
